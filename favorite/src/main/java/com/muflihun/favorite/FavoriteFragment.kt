package com.muflihun.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.muflihun.core.ui.GameAdapter
import com.muflihun.favorite.databinding.FragmentFavoriteBinding
import com.muflihun.gamecatalogue.detail.DetailGameActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue
import kotlin.jvm.java

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            val gameAdapter = GameAdapter()
            gameAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailGameActivity::class.java)
                intent.putExtra(DetailGameActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoriteGames.observe(viewLifecycleOwner) { game ->
                gameAdapter.submitList(game)
                binding.viewEmpty.visibility =
                    if (game.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvGame) {
                layoutManager = LinearLayoutManager(activity?.applicationContext)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }
    }
}