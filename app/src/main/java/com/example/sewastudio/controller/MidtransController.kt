package com.example.sewastudio.controller

import com.example.sewastudio.model.Order
import com.example.sewastudio.service.OrderBody
import com.example.sewastudio.service.OrderData
import com.example.sewastudio.service.OrderService
import com.example.sewastudio.service.SnapData
import com.example.sewastudio.service.SnapService
import com.example.sewastudio.service.SnapToken
import com.example.sewastudio.service.StudioScheduleData
import com.example.sewastudio.service.TransactionData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MidtransController {
    companion object {
        fun getSnapToken(jwt: String, userID: String, username: String, callback: (SnapToken?) -> Unit) {
            var snapService : SnapService = ClientController.getAuthService(SnapService::class.java, jwt)
            val transactionData : TransactionData = TransactionData(
                item_details = listOf(
                    StudioScheduleData(),
                    StudioScheduleData(),
                    StudioScheduleData()
                ),
                customer_details = com.midtrans.sdk.uikit.api.model.CustomerDetails(
                    userID,
                    username
                )
            )
            snapService.getSnapToken(SnapData(transactionData)).enqueue(object : Callback<SnapToken> {
                override fun onResponse(call: Call<SnapToken>, response: Response<SnapToken>): Unit =
                    if (response.isSuccessful) {
                        //println(response.body()!!.data)
                        callback(response.body())
                    } else {
                        callback(null)
                    }

                override fun onFailure(call: Call<SnapToken>, t: Throwable) {
                    callback(null)
                }
            })
        }
        fun insertOrder(jwt: String, ordername: String, ownerId: Int, callback: (Order?) -> Unit) {
            var orderService : OrderService = ClientController.getAuthService(OrderService::class.java, jwt)
            val orderData = OrderData(
                OrderBody(name = ordername, ownerId = ownerId)
            )
            orderService.insert(orderData).enqueue(object : Callback<Order> {
                override fun onResponse(call: Call<Order>, response: Response<Order>): Unit =
                    if (response.isSuccessful) {
                        callback(response.body())
                    } else {
                        callback(null)
                    }

                override fun onFailure(call: Call<Order>, t: Throwable) {
                    callback(null)
                }
            })
        }
    }
}