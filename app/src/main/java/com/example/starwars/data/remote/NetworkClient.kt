package com.example.starwars.data.remote

import com.example.starwars.data.extension.createAnApi
import com.example.starwars.data.remote.apiservices.StarshipsApi
import javax.inject.Inject

class NetworkClient @Inject constructor(
    retrofitClient: NetworkFastBuilder,
    okHttp: OkHttp
) {
    private val retrofit = retrofitClient.provideRetrofit(okHttp.provideOkHttpClient())
    fun provideStarshipsApiService(): StarshipsApi = retrofit.createAnApi()

}