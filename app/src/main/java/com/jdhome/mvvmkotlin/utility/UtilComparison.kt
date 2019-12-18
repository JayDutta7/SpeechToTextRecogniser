package com.jdhome.mvvmkotlin.utility

object UtilComparison {

    fun compareString(value_A: String?, value_B: String?): Boolean {
        return value_A?.toLowerCase()!!.trim() == value_B?.toLowerCase()!!.trim()
    }
}