package com.muflihun.gamecatalogue.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
                    setupSortingChipMenu()
                }

                setupBtnPageNavigation()
                setupSorting()
            }
        }
    }

    private fun setupSortingChipMenu() {
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_release -> {
                    gamesViewModel.setOrdering("-released")
                    true
                }
                R.id.menu_rating -> {
                    gamesViewModel.setOrdering("-rating")
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun setupSorting() {
        gamesViewModel.getOrdering().observe(viewLifecycleOwner) { ordering ->
            if (ordering != null) {
                when (ordering) {
                    "-rating" -> {
                        binding.sortingChip.text = getString(R.string.sorting_by_rating)
                    }
                    "-released" -> {
                        binding.sortingChip.text = getString(R.string.sorting_by_released)
                    }
                }
            }
        }
    }

    private fun setupBtnPageNavigation() {
        with(binding) {
            tvPage.text = gamesViewModel.getCurrentPageValue().toString()
            btnPrevPage.setOnClickListener {
                gamesViewModel.prevPage()
            }
            btnNextPage.setOnClickListener {
                gamesViewModel.nextPage()
            }
        }
        gamesViewModel.getCurrentPage().observe(viewLifecycleOwner) { currentPage ->
            if (currentPage != null) {
                binding.tvPage.text = currentPage.toString()
                setEnabledPrevButton(currentPage > 1)
                getGames()
            }
        }
    }

    private fun getGames() {
        gamesViewModel.getGames(BuildConfig.RAWG_API_KEY).observe(viewLifecycleOwner) { game ->
            if (game != null) {
                with(binding) {
                    val currentPage = gamesViewModel.getCurrentPageValue()
                    when (game) {
                        is Resource.Loading -> {
                            viewError.root.visibility = View.GONE
                            setEnabledNextButton(false)
                            setEnabledPrevButton(false)
                            showLoading(true)
                        }

                        is Resource.Success -> {
                            gameAdapter.submitList(game.data)
                            setEnabledNextButton(game.data?.isNotEmpty() == true)
                            setEnabledPrevButton(currentPage > 1)
                            tvPage.text = currentPage.toString()
                            showLoading(false)
                        }

                        is Resource.Error -> {
                            viewError.root.visibility = View.VISIBLE
                            viewError.tvError.text =
                                game.message ?: getString(R.string.something_wrong)
                            setEnabledNextButton(game.data?.isNotEmpty() == true)
                            setEnabledPrevButton(currentPage > 1)
                            tvPage.text = currentPage.toString()
                            showLoading(false)
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

    private fun showLoading(visible: Boolean) {
        binding.progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
