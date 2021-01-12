package com.github.coutinhonobre.githubapi.model

import com.google.gson.annotations.SerializedName

data class Repository(
    val id: Long,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val owner: Owner,
    val description: String,
    @SerializedName("forks_count")
    val forkCount: Long
)