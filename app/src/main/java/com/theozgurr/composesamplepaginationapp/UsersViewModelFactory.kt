package com.theozgurr.composesamplepaginationapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

class UsersViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private val userApi = provideRegionRetrofit().create(UserApi::class.java)
    private val userRemoteRepository = UserRemoteRepositoryImpl(userApi)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersViewModel(userRemoteRepository) as T
    }
}