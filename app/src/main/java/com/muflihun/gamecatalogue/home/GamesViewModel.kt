package com.muflihun.gamecatalogue.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.muflihun.core.data.Resource
import com.muflihun.core.domain.model.Game
import com.muflihun.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor (private val gameUseCase: GameUseCase) : ViewModel() {
    private var _game: LiveData<Resource<List<Game>>> = MutableLiveData()

    fun getGames(
        page: Int?,
        pageSize: Int?,
        ordering: String?,
        key: String,
    ): LiveData<Resource<List<Game>>> {
        _game = gameUseCase.getAllGames(page, pageSize, ordering, key).asLiveData()
        return _game
    }
}