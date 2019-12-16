package com.jdhome.mvvmkotlin

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDex
import com.jdhome.mvvmkotlin.database.localDatabase.MyAppDataBase
import com.jdhome.mvvmkotlin.database.sharedPreference.SharedPreferenceImpl
import timber.log.Timber
import com.jdhome.mvvmkotlin.utility.ReleaseApkTreeLog

class MyApplication:Application() {

    /**
     * SharedPreference*/
    private lateinit var sharedPreference: SharedPreferenceImpl

    /**
     * Room Database*/
    private lateinit var ticketDatabase: MyAppDataBase

    /**
     * set instance*/
    init {
        instance = this
    }


    /**
     * Static Field*/
    companion object {

        private lateinit var instance: MyApplication

        /**
         * getting context from application class*/
        fun getApplicationContext(): Context {
            //Timber.e("Context--ApplicationContext")
            return instance.applicationContext
        }


        /**
         * getting  SharedPref through application class*/
        fun getPref(): SharedPreferenceImpl {
            //Timber.e( "LocalDatabase--SharedPref")
            return instance.sharedPreference
        }

        /**
         *getting RoomDatabase through application class*/
        fun getRoomDatabase(): MyAppDataBase {
            //Timber.e( "Room--LocalDatabase")
            return instance.ticketDatabase
        }
    }


    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
        /**initialize multiDex for over 65k methods in application class*/
        MultiDex.install(context)
    }


    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(myApplication)

        /**Initialize Timber-Log as Per Build Wise**/
        when {
            BuildConfig.DEBUG -> {
                Timber.plant(Timber.DebugTree())
            }
            else -> {
                Timber.plant(ReleaseApkTreeLog())
            }
        }
    }

    private val myApplication = object : ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Timber.e("onActivityCreated--${activity.componentName.className}")
        }

        override fun onActivityStarted(activity: Activity) {
            Timber.e("onActivityStarted--${activity.componentName.className}")
        }

        override fun onActivityResumed(activity: Activity) {
            Timber.e("onActivityResumed--${activity.componentName.className}")
        }

        override fun onActivityPaused(activity: Activity) {
            Timber.e("onActivityPaused--${activity.componentName.className}")
        }

        override fun onActivityStopped(activity: Activity) {
            Timber.e("onActivityStopped--${activity.componentName.className}")
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            Timber.e("""onActivitySaveInstanceState--${activity.componentName.className}""")
        }

        override fun onActivityDestroyed(activity: Activity) {
            Timber.e("onActivityDestroyed--${activity.componentName.className}")
        }


    }
}