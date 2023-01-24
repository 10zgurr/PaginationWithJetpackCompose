package com.theozgurr.composesamplepaginationapp

class FakeUserRemoteRepositorySuccessImpl : UserRemoteRepository {

    override suspend fun getUsers(since: Int, perPage: Int): List<UserDto> =
        listOf(
            UserDto(id = 0),
            UserDto(id = 1),
            UserDto(id = 2)
        )
}