package com.muflihun.gamecatalogue.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.muflihun.core.data.Resource
import com.muflihun.core.ui.GameAdapter
import com.muflihun.gamecatalogue.BuildConfig
import com.muflihun.gamecatalogue.R
import com.muflihun.gamecatalogue.databinding.FragmentHomeBinding
import com.muflihun.gamecatalogue.detail.DetailGameActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val gamesViewModel: GamesViewModel by viewModels()
    private lateinit var gameAdapter: GameAdapter
    private lateinit var popupMenu: PopupMenu

    private var currentSorting = "-rating"

    private var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            with(binding) {
                popupMenu = PopupMenu(requireContext(), sortingChip)
                popupMenu.menuInflater.inflate(R.menu.menu_sorting, popupMenu.menu)

                gameAdapter = GameAdapter()
                gameAdapter.onItemClick = { selectedData ->
                    val intent = Intent(activity, DetailGameActivity::class.java)
                    intent.putExtra(DetailGameActivity.EXTRA_DATA, selectedData)
                    startActivity(intent)
                }

                with(rvGame) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = gameAdapter
                }

                sortingChip.setOnClickListener {
                    setupSortingChip()
                }

                setupBtnPageNavigation()
            }

            getGames()
        }
    }

    private fun setupSortingChip() {
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_release -> {
                    binding.sortingChip.text = getString(R.string.sorting_by_released)
                    currentSorting = "-released"
                    currentPage = 1
                    getGames()
                    true
                }

                R.id.menu_rating -> {
                    binding.sortingChip.text = getString(R.string.sorting_by_rating)
                    currentSorting = "-rating"
                    currentPage = 1
                    getGames()
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }

    private fun setupBtnPageNavigation() {
        with(binding) {
            tvPage.text = currentPage.toString()
            btnPrevPage.setOnClickListener {
                currentPage--
                getGames()
            }
            btnNextPage.setOnClickListener {
                currentPage++
                getGames()
            }
        }
    }

    private fun getGames() {
        gamesViewModel.getGames(
            currentPage, 20, currentSorting, BuildConfig.RAWG_API_KEY
        ).observe(viewLifecycleOwner) { game ->
            if (game != null) {
                with(binding) {
                    when (game) {
                        is Resource.Loading -> {
                            progressBar.visibility = View.VISIBLE
                            viewError.root.visibility = View.GONE
                            setEnabledNextButton(false)
                            setEnabledPrevButton(false)
                        }

                        is Resource.Success -> {
                            progressBar.visibility = View.GONE
                            gameAdapter.submitList(game.data)
                            setEnabledNextButton(game.data?.isNotEmpty() == true)
                            setEnabledPrevButton(currentPage > 1)
                            tvPage.text = currentPage.toString()
                        }

                        is Resource.Error -> {
                            progressBar.visibility = View.GONE
                            viewError.root.visibility = View.VISIBLE
                            viewError.tvError.text =
                                game.message ?: getString(R.string.something_wrong)
                            setEnabledNextButton(game.data?.isNotEmpty() == true)
                            setEnabledPrevButton(currentPage > 1)
                            tvPage.text = currentPage.toString()
                        }
                    }
                }
            }
        }
    }

    private fun setEnabledNextButton(enable: Boolean) {
        binding.btnNextPage.isEnabled = enable
        binding.btnNextPage.setImageResource(if (enable) R.drawable.navigation_arrow_forward else R.drawable.navigation_arrow_forward_disabled)
    }

    private fun setEnabledPrevButton(enable: Boolean) {
        binding.btnPrevPage.isEnabled = enable
        binding.btnPrevPage.setImageResource(if (enable) R.drawable.navigation_arrow_back else R.drawable.navigation_arrow_back_disabled)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
