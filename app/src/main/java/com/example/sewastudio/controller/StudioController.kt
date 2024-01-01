package com.example.sewastudio.controller

import com.example.sewastudio.model.Studio
import com.example.sewastudio.model.StudioResponse
import com.example.sewastudio.service.AuthService
import com.example.sewastudio.service.StudioBody
import com.example.sewastudio.service.StudioData
import com.example.sewastudio.service.StudioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudioController {
    companion object {
        private var studioService : StudioService = ClientController.getService(StudioService::class.java)

        fun insertStudio(studioname: String, ownerId: Int, callback: (Studio?) -> Unit) {
            val studioData = StudioData(
                StudioBody(name = studioname, ownerId = ownerId)
            )
            studioService.insert(studioData).enqueue(object : Callback<Studio> {
                override fun onResponse(call: Call<Studio>, response: Response<Studio>): Unit =
                    if (response.isSuccessful) {
//                        println(response.body())
                        callback(response.body())
                    } else {
//                        println("Empty")
                        callback(null)
                    }

                override fun onFailure(call: Call<Studio>, t: Throwable) {
//                    println(t)
                    callback(null)
                }
            })
        }
        fun getStudios(callback: (StudioResponse<List<Studio>>?) -> Unit){
            studioService.getall().enqueue(object : Callback<StudioResponse<List<Studio>>> {
                override fun onResponse(call: Call<StudioResponse<List<Studio>>>, response: Response<StudioResponse<List<Studio>>>): Unit =
                    if (response.isSuccessful) {
//                        println(response.body())
                        callback(response.body())
                    } else {
//                        println("Empty")
                        callback(null)
                    }

                override fun onFailure(call: Call<StudioResponse<List<Studio>>>, t: Throwable) {
//                    println(t)
                    callback(null)
                }
            })
        }
    }
}