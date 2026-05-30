package com.muflihun.gamecatalogue

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.muflihun.core.data.Resource
import com.muflihun.gamecatalogue.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val gamesViewModel: GamesViewModel by viewModels()

    private val TAG = "MainActivity";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gamesViewModel.getGames(
            1,
            20,
            "-rating",
            "772b302f010d4c4ab00afca96715979a"
        ).observe(this) { games ->
            if (games != null) {
                when (games) {
                    is Resource.Loading -> Log.d(
                        TAG,
                        "onCreate: Loading"
                    ) // binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
//                        binding.progressBar.visibility = View.GONE
//                        gamesAdapter.setData(games.data)
                        Log.d(TAG, "onCreate: " + games.data.toString())
                    }

                    is Resource.Error -> {
//                        binding.progressBar.visibility = View.GONE
//                        binding.viewError.root.visibility = View.VISIBLE
//                        binding.viewError.tvError.text =
//                            games.message ?: getString(R.string.something_wrong)
                        Log.d(TAG, "onCreate: " + games.message)
                    }
                }
            }
        }
    }
}