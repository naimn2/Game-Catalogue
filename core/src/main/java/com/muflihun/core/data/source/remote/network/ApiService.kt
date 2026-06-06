package com.muflihun.core.data.source.remote.network

import com.muflihun.core.data.source.remote.response.ListGame
import retrofit2.http.GET
import retrofit2.http.Query

// docs: https://api.rawg.io/docs/
// https://api.rawg.io/api/games?page=2&page_size=20&ordering=-rating&key=API_KEY

interface ApiService {
    @GET("api/games")
    suspend fun getGames(
        @Query("page") page: Int?,
        @Query("page_size") pageSize: Int?,
        @Query("ordering") ordering: String?,
        @Query("key") key: String,
    ): ListGame
}