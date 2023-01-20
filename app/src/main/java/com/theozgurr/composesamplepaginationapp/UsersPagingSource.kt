package com.theozgurr.composesamplepaginationapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

class UsersPagingSource(
    private val repository: UserRemoteRepository
) : PagingSource<Int, UserDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDto> =
        try {
            val perPage = params.key ?: PAGE_SIZE
            val since = perPage - PAGE_SIZE
            val users = repository.getUsers(since = since, perPage = perPage)
            delay(timeMillis = 1500)
            LoadResult.Page(
                data = users,
                prevKey = null,
                nextKey = if (users.isEmpty()) null else perPage + PAGE_SIZE
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, UserDto>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(PAGE_SIZE) ?: anchorPage?.nextKey?.minus(PAGE_SIZE)
        }

    companion object {
        const val PAGE_SIZE = 20
    }
}