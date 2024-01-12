package com.example.sewastudio.controller

import com.example.sewastudio.PreferencesManager
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

class StudioScheduleController {
    companion object {
        fun insertStudioSchedule(jwt: String, studioid: String, bookdate: String, price: Int, start_time: String, end_time: String, status: String, userid: String, prefMan: PreferencesManager, callback: (ApiResponse<StudioSchedule>?) -> Unit) {
            var studioScheduleService : StudioScheduleService = ClientController.getAuthService(StudioScheduleService::class.java, jwt)
            val studioScheduleData = StudioScheduleData(
                StudioScheduleBody(
                    studioid,
                    bookdate.split("-").reversed().joinToString("-"),
                    price,
                    start_time,
                    end_time,
                    status,
                    user_permissions_user = userid
                )
            )
            studioScheduleService.insert(studioScheduleData).enqueue(object : Callback<ApiResponse<StudioSchedule>> {
                override fun onResponse(call: Call<ApiResponse<StudioSchedule>>, response: Response<ApiResponse<StudioSchedule>>): Unit =
                    if (response.isSuccessful) {
                        response.body()?.data?.let {
                            prefMan.saveData("studioschedule", it.id.toString())
                        }
                        callback(response.body())
                    } else {
                        println("failed")
                        callback(null)
                    }

                override fun onFailure(call: Call<ApiResponse<StudioSchedule>>, t: Throwable) {
                    println("failed")
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
        fun editStudioSchedule(jwt: String, studioschedule : Int, status: String){
            var studioScheduleService : StudioScheduleService = ClientController.getAuthService(StudioScheduleService::class.java, jwt)
            val studioScheduleData = UpdateStudioScheduleData(
                UpdateStudioScheduleBody(status = status)
            )
            studioScheduleService.edit(studioschedule,studioScheduleData).enqueue(object : Callback<ApiResponse<StudioSchedule>>{
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