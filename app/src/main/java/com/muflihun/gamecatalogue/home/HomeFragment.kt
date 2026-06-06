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
            }

            getGames("-rating")
        }
    }

    private fun setupSortingChip() {
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_release -> {
                    binding.sortingChip.text = getString(R.string.sorting_by_released)
                    getGames("-released")
                    true
                }

                R.id.menu_rating -> {
                    binding.sortingChip.text = getString(R.string.sorting_by_rating)
                    getGames("-rating")
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }

    private fun getGames(ordering: String) {
        gamesViewModel.getGames(
            1, 20, ordering, BuildConfig.RAWG_API_KEY
        ).observe(viewLifecycleOwner) { game ->
            if (game != null) {
                when (game) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.viewError.root.visibility = View.GONE
                    }

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        gameAdapter.submitList(game.data)
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            game.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
