package com.theozgurr.composesamplepaginationapp

interface UserRemoteRepository {

    suspend fun getUsers(
        startId: Int?,
        perPage: Int?
    ): List<UserDto>
}