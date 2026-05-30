package com.muflihun.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.muflihun.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game where isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: List<GameEntity>)

    @Update
    fun updateFavoriteGame(game: GameEntity)
}
