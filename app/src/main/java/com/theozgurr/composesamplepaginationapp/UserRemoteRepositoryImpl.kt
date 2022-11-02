package com.theozgurr.composesamplepaginationapp

class UserRemoteRepositoryImpl(
    private val userApi: UserApi
) : UserRemoteRepository {

    override suspend fun getUsers(startId: Int?, perPage: Int?): List<UserDto> =
        userApi.getUsers(
            startId = startId,
            perPage = perPage
        )
}