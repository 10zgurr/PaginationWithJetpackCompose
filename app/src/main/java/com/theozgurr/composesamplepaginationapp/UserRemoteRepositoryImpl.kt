package com.theozgurr.composesamplepaginationapp

class UserRemoteRepositoryImpl(
    private val userApi: UserApi
) : UserRemoteRepository {

    override suspend fun getUsers(since: Int, perPage: Int): List<UserDto> =
        userApi.getUsers(
            since = since,
            perPage = perPage
        )
}