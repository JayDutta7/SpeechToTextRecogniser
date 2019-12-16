package com.jdhome.mvvmkotlin.database.sharedPreference

import android.content.Context
import android.content.SharedPreferences
import com.jdhome.mvvmkotlin.database.statickt.StaticVarVal.Companion.applicationName


class SharedPreferenceKt(context: Context){

    private val sharedPref: SharedPreferences = context.getSharedPreferences(applicationName,
        Context.MODE_PRIVATE)
    //Getting SharedPreference
    fun providePreferences(): SharedPreferences {
        return sharedPref
    }
}
