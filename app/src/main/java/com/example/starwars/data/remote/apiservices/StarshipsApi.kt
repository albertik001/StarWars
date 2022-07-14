package com.example.starwars.data.remote.apiservices

import com.example.starwars.data.remote.dtos.Result
import com.example.starwars.data.remote.dtos.StarshipsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarshipsApi {

    @GET("starships/")
    suspend fun fetchStarships(@Query("page") page: Int): StarshipsDto

    @GET("starships/{id}")
    suspend fun fetchStarshipDetail(@Path("id") id: Int): Result

}