package com.github.coutinhonobre.githubapi.repository

import com.github.coutinhonobre.githubapi.api.GeneralApi

class ItemRepository(private val api: GeneralApi, private val token: String, private val accept: String) {
    fun getRepositories(query: String = "Android") = api.getRepositories(token = token, accept = accept, query = query)
}