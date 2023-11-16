package com.example.sewastudio.service

import com.example.sewastudio.model.User
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("users")
    fun getUsers() : Call<List<User>>
}