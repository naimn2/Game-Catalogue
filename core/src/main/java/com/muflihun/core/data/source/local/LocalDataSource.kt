package com.muflihun.core.data.source.local

import com.muflihun.core.data.source.local.entity.GameEntity
import com.muflihun.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val gameDao: GameDao) {

    fun getAllGame(): Flow<List<GameEntity>> = gameDao.getAllGames()

    fun getFavoriteGame(): Flow<List<GameEntity>> = gameDao.getFavoriteGames()

    suspend fun insertGame(gameList: List<GameEntity>) = gameDao.insertGame(gameList)

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }
}