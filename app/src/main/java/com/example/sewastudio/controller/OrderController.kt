package com.example.sewastudio.controller

import com.example.sewastudio.model.ApiResponse
import com.example.sewastudio.model.Order
import com.example.sewastudio.service.OrderBody
import com.example.sewastudio.service.OrderData
import com.example.sewastudio.service.OrderService
import com.example.sewastudio.service.UpdateOrderBody
import com.example.sewastudio.service.UpdateOrderData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderController {
    companion object {
        fun insertOrder(jwt: String, ordername: String, ownerId: Int, callback: (Order?) -> Unit) {
            var OrderService : OrderService = ClientController.getAuthService(OrderService::class.java, jwt)
            val OrderData = OrderData(
                OrderBody(name = ordername, ownerId = ownerId)
            )
            OrderService.insert(OrderData).enqueue(object : Callback<Order> {
                override fun onResponse(call: Call<Order>, response: Response<Order>): Unit =
                    if (response.isSuccessful) {
//                        println(response.body())
                        callback(response.body())
                    } else {
//                        println("Empty")
                        callback(null)
                    }

                override fun onFailure(call: Call<Order>, t: Throwable) {
//                    println(t)
                    callback(null)
                }
            })
        }
        fun getOrders(jwt: String, callback: (ApiResponse<List<Order>>?) -> Unit){
            var OrderService : OrderService = ClientController.getAuthService(OrderService::class.java, jwt)
            OrderService.getall("*").enqueue(object : Callback<ApiResponse<List<Order>>> {
                override fun onResponse(call: Call<ApiResponse<List<Order>>>, response: Response<ApiResponse<List<Order>>>): Unit =
                    if (response.isSuccessful) {
                        var body = response.body()?.data
                        callback(response.body())
                    } else {
                        println("Empty")
                        callback(null)
                    }

                override fun onFailure(call: Call<ApiResponse<List<Order>>>, t: Throwable) {
//                    println(t)
                    callback(null)
                }
            })
        }

        fun editOrder(jwt: String, orderID : Int, ordername: String){
            var OrderService : OrderService = ClientController.getAuthService(OrderService::class.java, jwt)
            val OrderData = UpdateOrderData(
                UpdateOrderBody(name = ordername)
            )
            OrderService.edit(orderID,OrderData).enqueue(object : Callback<ApiResponse<Order>>{
                override fun onResponse(
                    call: Call<ApiResponse<Order>>,
                    response: Response<ApiResponse<Order>>): Unit =
                    if (response.isSuccessful) {
                        println(response.body())
                    } else {
//                        println("Empty")
                    }

                override fun onFailure(
                    call: Call<ApiResponse<Order>>,
                    t: Throwable
                ) {
                    print(t.message)
                }
            })
        }
        fun deleteOrder(jwt: String, orderID : Int){
            var OrderService : OrderService = ClientController.getAuthService(OrderService::class.java, jwt)
            OrderService.delete(orderID).enqueue(object : Callback<ApiResponse<Order>>{
                override fun onResponse(
                    call: Call<ApiResponse<Order>>,
                    response: Response<ApiResponse<Order>>): Unit =
                    if (response.isSuccessful) {
                        println(response.body())
                    } else {
//                        println("Empty")
                    }

                override fun onFailure(
                    call: Call<ApiResponse<Order>>,
                    t: Throwable
                ) {
                    print(t.message)
                }
            })
        }

        fun searchOrder(jwt: String, search: String, callback: (ApiResponse<List<Order?>>)-> Unit){
            
        }
    }
}