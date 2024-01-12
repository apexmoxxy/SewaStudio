package com.example.sewastudio

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.ui.AppBarConfiguration
import com.example.sewastudio.controller.StudioScheduleController
import com.example.sewastudio.databinding.ActivityMain2Binding
import com.midtrans.sdk.uikit.api.model.CustomColorTheme
import com.midtrans.sdk.uikit.api.model.TransactionResult
import com.midtrans.sdk.uikit.external.UiKitApi
import com.midtrans.sdk.uikit.internal.util.UiKitConstants

class MidtransActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val snapToken = intent.getStringExtra("snapToken")
        val studioschedule = intent.getStringExtra("studioschedule")
        val jwt = intent.getStringExtra("jwt")
        if (jwt == null || snapToken == null) {
            Intent(this, MainActivity::class.java).also {
                ContextCompat.startActivity(this, it, null)
            }
        }

        UiKitApi.Builder()
            .withMerchantClientKey("SB-Mid-client-cI9_9BqCmPeevfpp") // client_key is mandatory
            .withContext(applicationContext) // context is mandatory
            .withMerchantUrl("https://strapi.romiteam.my.id/api/midtrans/success/") // set transaction finish callback (sdk callback)
            .enableLog(true) // enable sdk log (optional)
            .withColorTheme(CustomColorTheme("#FFE51255", "#B61548", "#FFE51255"))
            .build()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result?.resultCode == RESULT_OK) {
                result.data?.let {
                    val transactionResult = it.getParcelableExtra<TransactionResult>(UiKitConstants.KEY_TRANSACTION_RESULT)
                    Toast.makeText(this,"${transactionResult?.transactionId}", Toast.LENGTH_LONG).show()
                }
            }
        }

        UiKitApi.getDefaultInstance().startPaymentUiFlow(
            this@MidtransActivity, // Activity
            launcher, // ActivityResultLauncher
            snapToken// Snap Token
        )
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            val transactionResult = data?.getParcelableExtra<TransactionResult>(
                UiKitConstants.KEY_TRANSACTION_RESULT
            )
            if (transactionResult != null) {
                when (transactionResult.status) {
                    UiKitConstants.STATUS_SUCCESS -> {
                        val jwt = intent.getStringExtra("jwt")
                        val studioschedule = intent.getStringExtra("studioschedule")
                        StudioScheduleController.editStudioSchedule(jwt = jwt!!, studioschedule = studioschedule!!.toInt(), status = "accepted")
                        Intent(this, MainActivity::class.java).also {
                            ContextCompat.startActivity(this, it, null)
                        }
                    }

                    UiKitConstants.STATUS_PENDING -> {
                        Intent(this, MainActivity::class.java).also {
                            ContextCompat.startActivity(this, it, null)
                        }
                        Toast.makeText(this, "Transaction Pending. ID: " + transactionResult.transactionId, Toast.LENGTH_LONG).show()
                    }

                    UiKitConstants.STATUS_FAILED -> {
                        Intent(this, MainActivity::class.java).also {
                            ContextCompat.startActivity(this, it, null)
                        }
                        Toast.makeText(this, "Transaction Failed. ID: " + transactionResult.transactionId, Toast.LENGTH_LONG).show()
                    }

                    UiKitConstants.STATUS_CANCELED -> {
                        Intent(this, MainActivity::class.java).also {
                            ContextCompat.startActivity(this, it, null)
                        }
                        Toast.makeText(this, "Transaction Cancelled", Toast.LENGTH_LONG).show()
                    }

                    UiKitConstants.STATUS_INVALID -> {
                        Intent(this, MainActivity::class.java).also {
                            ContextCompat.startActivity(this, it, null)
                        }
                        Toast.makeText(this, "Transaction Invalid. ID: " + transactionResult.transactionId, Toast.LENGTH_LONG).show()
                    }

                    else -> {
                        Intent(this, MainActivity::class.java).also {
                            ContextCompat.startActivity(this, it, null)
                        }
                        Toast.makeText(this, "Transaction ID: " + transactionResult.transactionId + ". Message: " + transactionResult.status, Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Intent(this, MainActivity::class.java).also {
                    ContextCompat.startActivity(this, it, null)
                }
                Toast.makeText(this, "Transaction Invalid", Toast.LENGTH_LONG).show()
            }
            println(transactionResult!!.status)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}