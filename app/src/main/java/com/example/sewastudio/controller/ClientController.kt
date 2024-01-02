package com.example.sewastudio.controller

import com.example.sewastudio.service.UploadService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientController {
    companion object {

        private val client : Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:1337/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun <T> getService(serviceClass: Class<T>): T {
            return client.create(serviceClass)
        }

        fun <T> getAuthService(serviceClass: Class<T>, authToken: String): T {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(authToken))
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:1337/api/") // Replace with your base URL
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()

            return retrofit.create(serviceClass)
        }

        fun <T> getUploadService(authToken: String): T {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(authToken))
                .build()

            val retrofit2 = Retrofit.Builder().baseUrl("http://10.0.2.2:1337/api/")
                .addConverterFactory(GsonConverterFactory.create()).client(
                    OkHttpClient.Builder().addInterceptor(AuthInterceptor(authToken)).addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()
                )
                .build()
            return retrofit2.create(UploadService::class.java) as T
        }
    }
}

class AuthInterceptor(private val authToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest: Request = chain.request()

        val newRequest: Request = originalRequest.newBuilder()
            .header("Authorization", "Bearer $authToken")
            .build()

        return chain.proceed(newRequest)
    }
}