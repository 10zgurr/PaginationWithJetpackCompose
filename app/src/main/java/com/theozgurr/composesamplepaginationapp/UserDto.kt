package com.theozgurr.composesamplepaginationapp

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("login")
    val nickname: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("repos_url")
    val reposUrl: String? = null,
    @SerializedName("type")
    val type: String? = null
)