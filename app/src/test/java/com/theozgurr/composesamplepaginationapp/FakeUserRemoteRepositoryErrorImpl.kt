package com.theozgurr.composesamplepaginationapp

class FakeUserRemoteRepositoryErrorImpl : UserRemoteRepository {

    override suspend fun getUsers(since: Int, perPage: Int): List<UserDto> =
        throw RuntimeException("no data for you")
}