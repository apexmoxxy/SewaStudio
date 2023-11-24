package com.example.sewastudio.controller

import android.util.Log
import com.example.sewastudio.model.*
import com.example.sewastudio.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class UserController {
    companion object {
        private var userService : UserService = ClientController.getService(UserService::class.java)

        fun getUsers(callback: (List<User>?) -> Unit) {
            userService.getUsers().enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>): Unit =
                    if (response.isSuccessful) {
//                        println(response.body())
                        callback(response.body())
                    } else {
//                        println("Empty")
                        callback(null)
                    }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                    println(t)
                    callback(null)
                }
            })
        }
    }
}