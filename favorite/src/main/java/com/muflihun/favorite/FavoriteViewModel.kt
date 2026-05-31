package com.muflihun.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.muflihun.core.domain.usecase.GameUseCase

class FavoriteViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val favoriteGames = gameUseCase.getFavoriteGames().asLiveData()
}