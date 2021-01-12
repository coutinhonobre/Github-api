package com.github.coutinhonobre.githubapi.api

import com.github.coutinhonobre.githubapi.model.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface GeneralApi {
    @GET("search/repositories?q=android")
    fun getRepositories(@Header("Authorization") token: String): Call<Item>
}