package com.github.coutinhonobre.githubapi.repository

import com.github.coutinhonobre.githubapi.api.GeneralApi

class ItemRepository(private val api: GeneralApi, private val token: String) {
    fun getRepositories() = api.getRepositories(token)
}