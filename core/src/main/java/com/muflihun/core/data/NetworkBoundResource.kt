package com.muflihun.core.data

import com.muflihun.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                saveCallResult(apiResponse.data)
                emitAll(loadFromDB().map { Resource.Success(it) })
            }
            is ApiResponse.Empty -> {
                emitAll(loadFromDB().map { Resource.Success(it) })
            }
            is ApiResponse.Error -> {
                if (isDBEmpty(dbSource)) {
                    emit(Resource.Error(apiResponse.errorMessage))
                } else {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
            }
        }
    }

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun isDBEmpty(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}