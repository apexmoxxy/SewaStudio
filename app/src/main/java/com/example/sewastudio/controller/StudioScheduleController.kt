package com.example.sewastudio.controller

import com.example.sewastudio.model.ApiResponse
import com.example.sewastudio.model.StudioSchedule
import com.example.sewastudio.service.StudioScheduleBody
import com.example.sewastudio.service.StudioScheduleData
import com.example.sewastudio.service.StudioScheduleService
import com.example.sewastudio.service.UpdateStudioScheduleBody
import com.example.sewastudio.service.UpdateStudioScheduleData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class StudioScheduleController {
    companion object {
        fun insertStudioSchedule(jwt: String, studioname: String, selectedImageFile : File, ownerId: Int, callback: (ApiResponse<StudioSchedule>?) -> Unit) {
            var studioScheduleService : StudioScheduleService = ClientController.getAuthService(StudioScheduleService::class.java, jwt)
            val studioScheduleData = StudioScheduleData(
                StudioScheduleBody(name = studioname, ownerId = ownerId)
            )
            studioScheduleService.insert(studioScheduleData).enqueue(object : Callback<ApiResponse<StudioSchedule>> {
                override fun onResponse(call: Call<ApiResponse<StudioSchedule>>, response: Response<ApiResponse<StudioSchedule>>): Unit =
                    if (response.isSuccessful) {
                        callback(response.body())
                    } else {
                        callback(null)
                    }

                override fun onFailure(call: Call<ApiResponse<StudioSchedule>>, t: Throwable) {
//                    println(t)
                    callback(null)
                }
            })
        }
        fun getStudioSchedule(jwt: String, userID: Int?, callback: (ApiResponse<List<StudioSchedule>>?) -> Unit){
            println(jwt)
            var studioScheduleService : StudioScheduleService = ClientController.getAuthService(StudioScheduleService::class.java, jwt)
            studioScheduleService.getall(userID.toString(),"*").enqueue(object : Callback<ApiResponse<List<StudioSchedule>>> {
                override fun onResponse(call: Call<ApiResponse<List<StudioSchedule>>>, response: Response<ApiResponse<List<StudioSchedule>>>): Unit =
                    if (response.isSuccessful) {
                        println(response.body())
                        callback(response.body())
                    } else {
                        callback(null)
                    }

                override fun onFailure(call: Call<ApiResponse<List<StudioSchedule>>>, t: Throwable) {
                    callback(null)
                }
            })
        }
        fun editStudioSchedule(jwt: String, studioID : Int, studioname: String){
            var studioScheduleService : StudioScheduleService = ClientController.getAuthService(StudioScheduleService::class.java, jwt)
            val studioScheduleData = UpdateStudioScheduleData(
                UpdateStudioScheduleBody(name = studioname)
            )
            studioScheduleService.edit(studioID,studioScheduleData).enqueue(object : Callback<ApiResponse<StudioSchedule>>{
                override fun onResponse(
                    call: Call<ApiResponse<StudioSchedule>>,
                    response: Response<ApiResponse<StudioSchedule>>): Unit =
                    if (response.isSuccessful) {
                        println(response.body())
                    } else {

                    }

                override fun onFailure(
                    call: Call<ApiResponse<StudioSchedule>>,
                    t: Throwable
                ) {
                    print(t.message)
                }
            })
        }
        fun deleteStudioSchedule(jwt: String, studioID : Int){
            var studioScheduleService : StudioScheduleService = ClientController.getAuthService(StudioScheduleService::class.java, jwt)
            studioScheduleService.delete(studioID).enqueue(object : Callback<ApiResponse<StudioSchedule>>{
                override fun onResponse(
                    call: Call<ApiResponse<StudioSchedule>>,
                    response: Response<ApiResponse<StudioSchedule>>): Unit =
                    if (response.isSuccessful) {
                        println(response.body())
                    } else {
                    }

                override fun onFailure(
                    call: Call<ApiResponse<StudioSchedule>>,
                    t: Throwable
                ) {
                    print(t.message)
                }
            })
        }

        fun searchStudio(jwt: String, search: String, callback: (ApiResponse<List<StudioSchedule?>>)-> Unit){
            
        }
    }
}