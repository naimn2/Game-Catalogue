package com.muflihun.core.data.source

import com.muflihun.core.data.NetworkBoundResource
import com.muflihun.core.data.Resource
import com.muflihun.core.data.source.local.LocalDataSource
import com.muflihun.core.data.source.remote.RemoteDataSource
import com.muflihun.core.data.source.remote.network.ApiResponse
import com.muflihun.core.data.source.remote.response.ResultsItem
import com.muflihun.core.domain.model.Game
import com.muflihun.core.domain.repository.IGameRepository
import com.muflihun.core.utils.AppExecutors
import com.muflihun.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {

    override fun getAllGames(
        page: Int?,
        pageSize: Int?,
        ordering: String?,
        key: String,
    ): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGame().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllGames(page, pageSize, ordering, key)

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(gameList)
            }
        }.asFlow()

    override fun getFavoriteGames(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
    }
}