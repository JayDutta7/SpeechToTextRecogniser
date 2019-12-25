package com.jdhome.mvvmkotlin.utility

import com.jdhome.mvvmkotlin.networking.model.ResponseData

object UtilComparison {

    fun compareString(value_A: String?, value_B: String?): Boolean {
        return value_A?.toLowerCase()!!.trim() == value_B?.toLowerCase()!!.trim()
    }

    fun binarySearch(arr: MutableList<String>, x: String): Int {
        var l = 0
        var r = arr.size - 1
        while (l <= r) {
            val m = l + (r - l) / 2
            val res = x.compareTo(arr[m])
            // Check if x is present at mid
            if (res == 0) return m
            // If x greater, ignore left half
            if (res > 0) l = m + 1 else r = m - 1
        }
        return -1
    }
}