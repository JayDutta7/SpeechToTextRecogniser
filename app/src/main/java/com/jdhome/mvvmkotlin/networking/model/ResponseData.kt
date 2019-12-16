package com.jdhome.mvvmkotlin.networking.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ResponseData(
    @SerializedName("word")
    @Expose
    var word: String?,
    @SerializedName("frequency")
    @Expose
    val frequency: Int
) : Parcelable