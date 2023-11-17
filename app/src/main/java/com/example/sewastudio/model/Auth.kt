package com.example.sewastudio.model

import com.google.gson.annotations.SerializedName

class Auth {
    @SerializedName("jwt")
    var jwt : String = ""
    var user: User? = null
}