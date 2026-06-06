package com.muflihun.gamecatalogue.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlin.getValue

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val gamesViewModel: GamesViewModel by viewModels()

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
                val gameAdapter = GameAdapter()
                gameAdapter.onItemClick = { selectedData ->
                    val intent = Intent(activity, DetailGameActivity::class.java)
                    intent.putExtra(DetailGameActivity.EXTRA_DATA, selectedData)
                    startActivity(intent)
                }

                gamesViewModel.getGames(
                    1, 20, "-rating", BuildConfig.RAWG_API_KEY
                ).observe(viewLifecycleOwner) { game ->
                    if (game != null) {
                        when (game) {
                            is Resource.Loading -> progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                progressBar.visibility = View.GONE
                                gameAdapter.submitList(game.data)
                            }

                            is Resource.Error -> {
                                progressBar.visibility = View.GONE
                                viewError.root.visibility = View.VISIBLE
                                viewError.tvError.text =
                                    game.message ?: getString(R.string.something_wrong)
                            }
                        }
                    }
                }

                with(rvGame) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = gameAdapter
                }
            }
        }
    }
}
