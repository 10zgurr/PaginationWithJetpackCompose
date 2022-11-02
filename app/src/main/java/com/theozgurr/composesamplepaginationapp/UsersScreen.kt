package com.theozgurr.composesamplepaginationapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.theozgurr.composesamplepaginationapp.ui.theme.Purple700
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideRegionRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API_ADDRESS)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttpClientBuilder())
        .build()
}

private fun provideOkHttpClientBuilder(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    httpClient.addInterceptor(logging)
    return httpClient.build()
}

class MyViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private val userApi = provideRegionRetrofit().create(UserApi::class.java)
    private val userRemoteRepository = UserRemoteRepositoryImpl(userApi)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersViewModel(userRemoteRepository) as T
    }
}

@Composable
fun UsersScreen() {

    val viewModel: UsersViewModel = viewModel(factory = MyViewModelFactory())

    val users = viewModel.users.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        when (users.loadState.prepend) {
            LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp),
                            color = Purple700,
                            strokeWidth = 4.dp
                        )
                    }
                }
            }
        }
        items(items = users) { user ->
            Text(text = user?.nickname ?: "", modifier = Modifier.padding(all = 32.dp))
        }
    }
}