package com.muflihun.gamecatalogue.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.muflihun.core.data.Resource
import com.muflihun.core.domain.model.Game
import com.muflihun.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {

    private val _currentPage = MutableLiveData(1)
    private val _ordering = MutableLiveData("-rating")
    private val _apiKey = MutableLiveData<String>()

    val games: LiveData<Resource<List<Game>>> = _apiKey.switchMap { key ->
        _currentPage.switchMap { page ->
            gameUseCase.getAllGames(
                page ?: 1,
                20,
                getOrdering().value,
                key
            ).asLiveData()
        }
    }

    fun setApiKey(key: String) {
        if (_apiKey.value != key) {
            _apiKey.value = key
        }
    }

    fun getCurrentPage(): LiveData<Int> = _currentPage

    fun getCurrentPageValue(): Int = _currentPage.value ?: 1

    fun getOrdering(): LiveData<String> = _ordering

    fun nextPage() {
        _currentPage.value = (_currentPage.value ?: 1) + 1
    }

    fun prevPage() {
        val current = _currentPage.value ?: 1
        if (current > 1) {
            _currentPage.value = current - 1
        }
    }

    fun setOrdering(ordering: String) {
        if (_ordering.value != ordering) {
            _ordering.value = ordering
            _resetPage()
        }
    }

    private fun _resetPage() {
        _currentPage.value = 1
    }
}
