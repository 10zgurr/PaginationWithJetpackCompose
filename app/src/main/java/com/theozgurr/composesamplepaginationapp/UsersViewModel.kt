package com.theozgurr.composesamplepaginationapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.theozgurr.composesamplepaginationapp.UsersPagingSource.Companion.PAGE_SIZE

class UsersViewModel(
    private val repository: UserRemoteRepository
) : ViewModel() {

    val users = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            UsersPagingSource(
                repository = repository
            )
        }
    )
        .flow
        .cachedIn(scope = viewModelScope)
}