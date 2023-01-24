package com.theozgurr.composesamplepaginationapp

import androidx.paging.PagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UsersPagingSourceTest {

    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(testScope.testScheduler)

    private val userDtos = listOf(
        UserDto(id = 0),
        UserDto(id = 1),
        UserDto(id = 2)
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when users are given then users paging source returns success load result`() =
        testScope.runTest {
            // given
            val repository = FakeUserRemoteRepositorySuccessImpl()
            val usersPagingSource = UsersPagingSource(
                repository = repository
            )

            val params = PagingSource
                .LoadParams
                .Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )

            val expected = PagingSource
                .LoadResult
                .Page(
                    data = userDtos,
                    prevKey = null,
                    nextKey = 20
                )

            // when
            val actual = usersPagingSource.load(params = params)

            // then
            assertEquals(expected, actual)
        }

    @Test
    fun `when users are given then users paging source returns error load result`() =
        testScope.runTest {
            // given
            val repository = FakeUserRemoteRepositoryErrorImpl()
            val usersPagingSource = UsersPagingSource(
                repository = repository
            )

            val params = PagingSource
                .LoadParams
                .Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )

            val expected = PagingSource
                .LoadResult
                .Error<Int, UserDto>(
                    throwable = RuntimeException("no data for you")
                )::class.java

            // when
            val actual = usersPagingSource.load(params = params)::class.java

            // then
            assertEquals(expected, actual)
        }

    @Test
    fun `when users are given for next pages then users paging source returns success append load result`() =
        testScope.runTest {
            // given
            val repository = FakeUserRemoteRepositorySuccessImpl()
            val usersPagingSource = UsersPagingSource(
                repository = repository
            )

            val params = PagingSource
                .LoadParams
                .Append(
                    key = 20,
                    loadSize = 1,
                    placeholdersEnabled = false
                )

            val expected = PagingSource
                .LoadResult
                .Page(
                    data = userDtos,
                    prevKey = null,
                    nextKey = 40
                )

            // when
            val actual = usersPagingSource.load(params = params)

            // then
            assertEquals(expected, actual)
        }
}