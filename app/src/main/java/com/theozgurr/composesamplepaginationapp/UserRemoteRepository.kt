package com.theozgurr.composesamplepaginationapp

interface UserRemoteRepository {

    suspend fun getUsers(
        since: Int,
        perPage: Int
    ): List<UserDto>
}