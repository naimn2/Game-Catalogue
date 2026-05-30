package com.muflihun.gamecatalogue.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import com.bumptech.glide.Glide
import com.muflihun.core.domain.model.Game
import com.muflihun.gamecatalogue.R
import com.muflihun.gamecatalogue.databinding.ActivityDetailGameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailGameBinding

    private val viewModel: DetailGameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.empty)

        val game = getParcelableExtra(intent,
            EXTRA_DATA, Game::class.java)
        showDetailGame(game)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        game?.let {
            val currentStatusFavorite = game.isFavorite
            setStatusFavoriteIcon(currentStatusFavorite)
        }

        binding.fab.setOnClickListener {
            game?.let {
                val newStatus = !game.isFavorite
                viewModel.setFavoriteGame(game, newStatus)
                setStatusFavoriteIcon(newStatus)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun showDetailGame(game: Game?) {
        game?.let {
            binding.tvDetailTitle.text = game.name
            binding.tvDetailRating.text = game.rating.toString()
            binding.tvDetailRelease.text = game.genres
            binding.tvDetailDescription.text = game.platforms
            Glide.with(this@DetailGameActivity)
                .load(game.backgroundImage)
                .into(binding.ivDetailImage)
        }
    }

    private fun setStatusFavoriteIcon(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
