package com.jdhome.mvvmkotlin.networking.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("dictionary")
    @Expose
    val responseValue:ArrayList<ResponseData>
)