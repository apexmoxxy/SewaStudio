package com.example.sewastudio.controller

import android.webkit.MimeTypeMap
import com.example.sewastudio.service.UploadResponseList
import com.example.sewastudio.service.UploadService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UploadController {
    companion object {
        fun uploadFile(jwt:String, studioId : Int, file: File, callback: (UploadResponseList?) -> Unit) {
            var uploadService: UploadService = ClientController.getUploadService(jwt)

            val mimeType = MimeTypeMap.getSingleton()
                .getMimeTypeFromExtension(file.extension)
            val refRequestBody =
                "api::studio.studio".toRequestBody("multipart/form-data".toMediaTypeOrNull())
            println(studioId)
            val refIdRequestBody = studioId.toString()
                .toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val fieldRequestBody =
                "studioImg".toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val fileRequestBody = MultipartBody.Part.createFormData(
                "files",
                file.name,
                file.asRequestBody(mimeType?.toMediaTypeOrNull())
            )
            uploadService.upload(
                refRequestBody,
                refIdRequestBody,
                fieldRequestBody,
                fileRequestBody
            ).enqueue(object : Callback<UploadResponseList> {
                override fun onResponse(call: Call<UploadResponseList>, response: Response<UploadResponseList>): Unit =
                    if (response.isSuccessful) {
                        callback(response.body())
                    } else {
                        callback(null)
                    }
                override fun onFailure(call: Call<UploadResponseList>, t: Throwable) {
                    callback(null)
                }
            })
        }
    }
    
}