package com.github.coutinhonobre.githubapi.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("items")
    val repositories: List<Repository>
)