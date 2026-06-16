package com.muflihun.gamecatalogue.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.recyclerview.widget.LinearLayoutManager
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

        val game = getParcelableExtra(
            intent,
            EXTRA_DATA, Game::class.java
        )
        title = game?.name ?: getString(R.string.empty)
        showDetailGame(game)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val screenshotsAdapter = DetailScreenshotsAdapter()
        val tagsAdapter = DetailTagsAdapter()

        game?.let {
            var currentStatusFavorite = game.isFavorite
            setStatusFavoriteIcon(currentStatusFavorite)
            binding.fab.setOnClickListener {
                game.let {
                    currentStatusFavorite = !currentStatusFavorite
                    viewModel.setFavoriteGame(game, currentStatusFavorite)
                    setStatusFavoriteIcon(currentStatusFavorite)
                }
            }

            tagsAdapter.tags = game.tags?.split(", ") ?: emptyList()
            with(binding.rvTags) {
                layoutManager = LinearLayoutManager(this@DetailGameActivity, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = tagsAdapter
            }

            screenshotsAdapter.screenshots =
                game.shortScreenshots?.split(", ")?.filter { it != game.backgroundImage }
                    ?: emptyList()
            with(binding.rvDetailScreenshots) {
                layoutManager = LinearLayoutManager(this@DetailGameActivity)
                setHasFixedSize(true)
                adapter = screenshotsAdapter
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun showDetailGame(game: Game?) {
        game?.let {
            with(binding) {
                tvDetailTitle.text = game.name
                tvDetailRating.text = game.rating.toString()
                tvDetailGenre.text = game.genres
                tvDetailPlatform.text = game.platforms
                Glide.with(this@DetailGameActivity)
                    .load(game.backgroundImage)
                    .into(ivDetailImage)
            }
        }
    }

    private fun setStatusFavoriteIcon(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
