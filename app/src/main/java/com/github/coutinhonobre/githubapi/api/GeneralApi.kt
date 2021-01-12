package com.github.coutinhonobre.githubapi.api

import com.github.coutinhonobre.githubapi.model.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GeneralApi {
    @GET("search/repositories")
    fun getRepositories(
        @Header("Authorization") token: String,
        @Header("Accept") accept: String,
        @Query("q") query: String = ""
    ): Call<Item>
}