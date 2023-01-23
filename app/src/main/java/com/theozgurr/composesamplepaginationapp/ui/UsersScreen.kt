package com.theozgurr.composesamplepaginationapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.theozgurr.composesamplepaginationapp.UserDto
import com.theozgurr.composesamplepaginationapp.UsersViewModel
import com.theozgurr.composesamplepaginationapp.UsersViewModelFactory

@Composable
fun UsersRoute(
    modifier: Modifier = Modifier
) {
    val viewModel: UsersViewModel = viewModel(factory = UsersViewModelFactory())
    val users = viewModel.users.collectAsLazyPagingItems()

    UsersScreen(
        modifier = modifier,
        users = users
    )
}

@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    users: LazyPagingItems<UserDto>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = if (users.itemCount < 1)
            Arrangement.Center
        else
            Arrangement.Top
    ) {
        items(items = users) { user ->
            UserItem(user = user)
        }
        when (users.loadState.refresh) {
            LoadState.Loading -> {
                item {
                    PaginationLoadingItem(circularProgressSize = 64.dp)
                }
            }
            is LoadState.Error -> {
                item {
                    PaginationErrorItem {
                        users.refresh()
                    }
                }
            }
            is LoadState.NotLoading -> {
                if (users.itemCount < 1) {
                    item {
                        EmptyItem()
                    }
                }
            }
        }
        when (users.loadState.append) {
            LoadState.Loading -> {
                item {
                    PaginationLoadingItem()
                }
            }
            is LoadState.Error -> {
                item {
                    PaginationRetryItem {
                        users.retry()
                    }
                }
            }
            is LoadState.NotLoading -> Unit
        }
    }
}

@Composable
fun UserItem(user: UserDto?) {
    Text(
        modifier = Modifier.padding(all = 32.dp),
        text = user?.nickname ?: ""
    )
}
