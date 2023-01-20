package com.theozgurr.composesamplepaginationapp

import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun getUsers(
        @Query("since") since: Int?,
        @Query("per_page") perPage: Int?
    ): List<UserDto>
}