package com.jdhome.mvvmkotlin.networking


import com.jdhome.mvvmkotlin.database.statickt.StaticVarVal
import com.jdhome.mvvmkotlin.networking.model.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url
import timber.log.Timber


interface ApiInterface {


    /**********************************todo Api Service's***********************************/
    //@Field parameters can only be used with form encoding (POST)
    //@Query This annotation represents any query key value pair to be sent along with the network request GET/POST
    //@Path parameter name must match \{([a-zA-Z][a-zA-Z0-9_-]*)\}.


    /*todo------((Api1--Get all images))*/
    @GET
    fun getDicResult(@Url dictionaryUrl: String): Single<ApiResponse>


    /**Create Retrofit Service--By Calling class Create Retrofit**/
    class CreateRetrofit {
        fun apiService(url: String): ApiInterface {
            Timber.e("""RetrofitUrl$url""")
            return when (url) {
                StaticVarVal.baseUrl -> {
                    RetrofitNetworking.getClient(WebService.BaseUrl)!!.create(ApiInterface::class.java)
                }
                else -> {
                    RetrofitNetworking.getClient(url)!!.create(ApiInterface::class.java)
                }
            }
        }
    }


}