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
class GamesViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {
    private var _game: LiveData<Resource<List<Game>>> = MutableLiveData()

    val game get() = _game

    private var _currentPage = MutableLiveData(1)

    private var _ordering = MutableLiveData("-rating")

    fun getCurrentPage(): MutableLiveData<Int?> {
        return _currentPage
    }

    fun getCurrentPageValue(): Int {
        return _currentPage.value ?: 1
    }

    fun getOrdering(): MutableLiveData<String?> {
        return _ordering
    }

    fun getOrderingValue(): String {
        return _ordering.value ?: "-rating"
    }

    fun getGames(
        key: String,
    ): LiveData<Resource<List<Game>>> {
        _game = gameUseCase.getAllGames(
            getCurrentPageValue(),
            20,
            getOrderingValue(),
            key
        ).asLiveData()
        return _game
    }

    fun nextPage() {
        _currentPage.value = _currentPage.value?.plus(1)
    }

    fun prevPage() {
        _currentPage.value = _currentPage.value?.minus(1)
    }

    fun setOrdering(ordering: String) {
        _ordering.value = ordering
        _resetPage()
    }

    private fun _resetPage() {
        _currentPage.value = 1
    }
}