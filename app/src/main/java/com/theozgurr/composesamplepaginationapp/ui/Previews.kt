package com.theozgurr.composesamplepaginationapp.ui

import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.theozgurr.composesamplepaginationapp.UserDto
import com.theozgurr.composesamplepaginationapp.ui.theme.ComposeSamplePaginationAppTheme
import kotlinx.coroutines.flow.flowOf

@Composable
@PreviewAllScreens
fun PreviewUsersScreen() {
    ComposeSamplePaginationAppTheme {

        val usersDtos = listOf(
            UserDto(nickname = "nickname1"),
            UserDto(nickname = "nickname2"),
            UserDto(nickname = "nickname3")
        )

        val users = flowOf(value = PagingData.from(data = usersDtos))
            .collectAsLazyPagingItems()

        UsersScreen(users = users)
    }
}

@Composable
@PreviewAllScreens
fun PreviewEmptyUsersScreen() {
    ComposeSamplePaginationAppTheme {

        val users = flowOf(value = PagingData.empty<UserDto>())
            .collectAsLazyPagingItems()

        UsersScreen(users = users)
    }
}

@Composable
@PreviewWithBackground
fun PreviewPaginationLoadingItem() {
    ComposeSamplePaginationAppTheme {
        PaginationLoadingItem()
    }
}

@Composable
@PreviewWithBackground
fun PreviewPaginationErrorItem() {
    ComposeSamplePaginationAppTheme {
        PaginationErrorItem {}
    }
}

@Composable
@PreviewWithBackground
fun PreviewEmptyItem() {
    ComposeSamplePaginationAppTheme {
        EmptyItem()
    }
}

@Composable
@PreviewWithBackground
fun PreviewPaginationRetryItem() {
    ComposeSamplePaginationAppTheme {
        PaginationRetryItem {}
    }
}