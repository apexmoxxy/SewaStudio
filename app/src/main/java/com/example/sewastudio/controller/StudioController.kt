package com.example.sewastudio.controller

import com.example.sewastudio.model.ApiResponse
import com.example.sewastudio.model.Studio
import com.example.sewastudio.model.StudioResponse
import com.example.sewastudio.service.StudioBody
import com.example.sewastudio.service.StudioData
import com.example.sewastudio.service.StudioService
import com.example.sewastudio.service.UpdateStudioBody
import com.example.sewastudio.service.UpdateStudioData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudioController {
    companion object {
        fun insertStudio(jwt: String, studioname: String, ownerId: Int, callback: (Studio?) -> Unit) {
            var studioService : StudioService = ClientController.getAuthService(StudioService::class.java, jwt)
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
        fun getStudios(jwt: String, userID: Int, callback: (ApiResponse<List<Studio>>?) -> Unit){
            println(jwt)
            var studioService : StudioService = ClientController.getAuthService(StudioService::class.java, jwt)
            studioService.getall(userID.toString(),"*").enqueue(object : Callback<ApiResponse<List<Studio>>> {
                override fun onResponse(call: Call<ApiResponse<List<Studio>>>, response: Response<ApiResponse<List<Studio>>>): Unit =
                    if (response.isSuccessful) {
                        println(response.body())
                        callback(response.body())
                    } else {
//                        println("Empty")
                        callback(null)
                    }

                override fun onFailure(call: Call<ApiResponse<List<Studio>>>, t: Throwable) {
//                    println(t)
                    callback(null)
                }
            })
        }
        fun getStudios(jwt: String, callback: (ApiResponse<List<Studio>>?) -> Unit){
            println(jwt)
            var studioService : StudioService = ClientController.getAuthService(StudioService::class.java, jwt)
            studioService.getstudios().enqueue(object : Callback<ApiResponse<List<Studio>>> {
                override fun onResponse(call: Call<ApiResponse<List<Studio>>>, response: Response<ApiResponse<List<Studio>>>): Unit =
                    if (response.isSuccessful) {
                        println(response.body())
                        callback(response.body())
                    } else {
//                        println("Empty")
                        callback(null)
                    }

                override fun onFailure(call: Call<ApiResponse<List<Studio>>>, t: Throwable) {
//                    println(t)
                    callback(null)
                }
            })
        }
        fun editStudio(jwt: String, studioID : Int, studioname: String){
            var studioService : StudioService = ClientController.getAuthService(StudioService::class.java, jwt)
            val studioData = UpdateStudioData(
                UpdateStudioBody(name = studioname)
            )
            studioService.edit(studioID,studioData).enqueue(object : Callback<ApiResponse<Studio>>{
                override fun onResponse(
                    call: Call<ApiResponse<Studio>>,
                    response: Response<ApiResponse<Studio>>): Unit =
                    if (response.isSuccessful) {
                        println(response.body())
                    } else {
//                        println("Empty")
                    }

                override fun onFailure(
                    call: Call<ApiResponse<Studio>>,
                    t: Throwable
                ) {
                    print(t.message)
                }
            })
        }
        fun deleteStudio(jwt: String, studioID : Int){
            var studioService : StudioService = ClientController.getAuthService(StudioService::class.java, jwt)
            studioService.delete(studioID).enqueue(object : Callback<ApiResponse<Studio>>{
                override fun onResponse(
                    call: Call<ApiResponse<Studio>>,
                    response: Response<ApiResponse<Studio>>): Unit =
                    if (response.isSuccessful) {
                        println(response.body())
                    } else {
//                        println("Empty")
                    }

                override fun onFailure(
                    call: Call<ApiResponse<Studio>>,
                    t: Throwable
                ) {
                    print(t.message)
                }
            })
        }

        fun searchStudio(jwt: String, search: String, callback: (ApiResponse<List<Studio?>>)-> Unit){
            
        }
    }
}