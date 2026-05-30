package com.muflihun.gamecatalogue.detail

import androidx.lifecycle.ViewModel
import com.muflihun.core.domain.model.Game
import com.muflihun.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailGameViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {
    fun setFavoriteGame(game: Game, newStatus:Boolean) =
        gameUseCase.setFavoriteGames(game, newStatus)
}