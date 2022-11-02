package com.theozgurr.composesamplepaginationapp

import androidx.paging.PagingSource
import androidx.paging.PagingState

class UsersPagingSource(
    private val repository: UserRemoteRepository
) : PagingSource<Int, UserDto>() {

    companion object {
        const val DEFAULT_PAGE_INDEX = 20
        private const val PER_PAGE_INDEX = 20
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDto> =
        try {
            val page = params.key ?: DEFAULT_PAGE_INDEX
            val startId = page - PER_PAGE_INDEX
            val users = repository.getUsers(startId = startId, perPage = page)
            LoadResult.Page(
                data = users,
                prevKey = if (users.isEmpty()) null else page + PER_PAGE_INDEX,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, UserDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(PER_PAGE_INDEX) ?: anchorPage?.nextKey?.minus(PER_PAGE_INDEX)
        }
    }
}