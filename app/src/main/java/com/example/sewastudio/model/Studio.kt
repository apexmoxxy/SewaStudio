package com.example.sewastudio.model

import com.google.gson.annotations.SerializedName


class Studio {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("attributes")
    var attributes: StudioAttributes = StudioAttributes()
}

class StudioAttributes{
    @SerializedName("name")
    var name: String = ""
    @SerializedName("createdAt")
    var createdAt: String = ""
    @SerializedName("updatedAt")
    var updatedAt: String = ""
    @SerializedName("publishedAt")
    var publishedAt: String = ""
    @SerializedName("available")
    var available: Any? = null
    @SerializedName("studioImg")
    var studioImg: StudioImgData = StudioImgData()
}

class StudioImgData{
    @SerializedName("data")
    var data: StudioImg = StudioImg()
}
class StudioImg{
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("attributes")
    var attributes : StudioImgAttributes = StudioImgAttributes()
}

class StudioImgAttributes{
    @SerializedName("name")
    var name: String = ""
    @SerializedName("url")
    var url: String = ""
}
class StudioSchedule {
    @SerializedName("id")
    var id : Int = 0
    @SerializedName("attributes")
    var attributes: StudioScheduleAttributes = StudioScheduleAttributes()
}

class StudioScheduleAttributes {
//    @SerializedName("studioID")
//    var studioID : ApiResponse<Studio> = ApiResponse<Studio>()
//    @SerializedName("date")
//    var date : String = ""
//    @SerializedName("time")
//    var time : String = ""
//    @SerializedName("price")
//    var price : Int = 0
//    @SerializedName("status")
//    var status : String = ""
}
