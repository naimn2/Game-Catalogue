package com.muflihun.core.domain.usecase

import com.muflihun.core.domain.model.Game
import com.muflihun.core.domain.repository.IGameRepository
import javax.inject.Inject

class GameInteractor @Inject constructor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getAllGames(
        page: Int?,
        pageSize: Int?,
        ordering: String?,
        key: String,
    ) = gameRepository.getAllGames(page, pageSize, ordering, key)

    override fun getFavoriteGames() = gameRepository.getFavoriteGames()

    override fun setFavoriteGames(
        game: Game,
        state: Boolean
    ) = gameRepository.setFavoriteGame(game, state)
}