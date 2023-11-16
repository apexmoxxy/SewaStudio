package com.example.sewastudio.controller

import com.example.sewastudio.service.UserService
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
    }

}