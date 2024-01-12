package com.example.sewastudio.controller

import com.example.sewastudio.PreferencesManager
import com.example.sewastudio.service.ItemData
import com.example.sewastudio.service.SnapData
import com.example.sewastudio.service.SnapService
import com.example.sewastudio.service.SnapToken
import com.example.sewastudio.service.TransactionData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MidtransController {
    companion object {
        fun getSnapToken(jwt: String, userID: String, username: String, item: ItemData, prefMan: PreferencesManager, callback: (SnapToken?) -> Unit) {
            var snapService : SnapService = ClientController.getAuthService(SnapService::class.java, jwt)
            val transactionData : TransactionData = TransactionData(
                item_details = listOf(item),
                customer_details = com.midtrans.sdk.uikit.api.model.CustomerDetails(
                    customerIdentifier = userID,
                    firstName = username,
                    phone = "08123456789",
                )
            )
            StudioScheduleController.insertStudioSchedule(jwt, studioid = item.id, bookdate = item.date, price = item.price, start_time = item.startTime, end_time = item.endTime, status = "pending", userid = userID.toString(), prefMan) {it

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
        }
    }
}