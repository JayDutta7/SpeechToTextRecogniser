package com.jdhome.mvvmkotlin.viewmodel.splash

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit


class SplashViewModel : ViewModel() {

    val liveData: LiveData<SplashState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()

    private var compositeDisposable: CompositeDisposable? = null

    init {
        compositeDisposable = CompositeDisposable()


    }

    @SuppressLint("CheckResult")
    fun delayIntent() {
        Completable.complete()
            .delay(3, TimeUnit.SECONDS)
            .doOnError {
                Timber.e(it)
            }.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : CompletableObserver {
                override fun onComplete() {
                    mutableLiveData.postValue(SplashState.HomeActivity)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable?.add(d)
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.clear()
    }

    sealed class SplashState {
        object HomeActivity : SplashState()
    }

}