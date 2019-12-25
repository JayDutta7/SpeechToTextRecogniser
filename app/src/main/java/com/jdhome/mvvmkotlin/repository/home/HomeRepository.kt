package com.jdhome.mvvmkotlin.repository.home

import com.jdhome.mvvmkotlin.database.statickt.StaticVarVal
import com.jdhome.mvvmkotlin.networking.ApiInterface
import com.jdhome.mvvmkotlin.networking.model.ApiResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class HomeRepository {

    private var homeRepoApiService: ApiInterface? = null

    init {
        homeRepoApiService = ApiInterface.CreateRetrofit().apiService(StaticVarVal.baseUrl)
    }


    fun getAllImages(values: HashMap<Int, String>): Single<ApiResponse>? {
        return homeRepoApiService?.getDicResult(
            values[0].toString()
        )?.doOnError {
            Timber.e(it)
        }?.subscribeOn(Schedulers.io())
         ?.observeOn(AndroidSchedulers.mainThread())
    }


}