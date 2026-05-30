package com.muflihun.core.domain.usecase

import com.muflihun.core.data.Resource
import com.muflihun.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGames(
        page: Int?,
        pageSize: Int?,
        ordering: String?,
        key: String,
    ): Flow<Resource<List<Game>>>
    fun getFavoriteGames(): Flow<List<Game>>
    fun setFavoriteGames(game: Game, state: Boolean)
}