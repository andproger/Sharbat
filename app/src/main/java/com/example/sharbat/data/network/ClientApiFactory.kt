package com.example.sharbat.data.network

import com.example.sharbat.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ClientApiFactory {

    companion object {
        private const val BASE_URL = "http://192.168.43.81:8000"//"https://andryproger.000webhostapp.com"//"https://andryproger.000webhostapp.com"//"http://192.168.43.81:8000"

        fun createRestApi() = createRetrofit(
            createOkHttpClient(createLogging())
        ).create(ClientApi::class.java)

        private fun createRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        private fun createOkHttpClient(logging: HttpLoggingInterceptor) =
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        private fun createLogging() = HttpLoggingInterceptor()
            .setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            )
    }
}