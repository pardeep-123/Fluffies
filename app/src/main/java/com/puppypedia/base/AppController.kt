package com.puppypedia.base

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumConfig
import java.util.*


class AppController : Application(), AppLifecycleHandler.AppLifecycleDelegates {


    private var lifecycleHandler: AppLifecycleHandler? = null

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        Album.initialize(
            AlbumConfig.newBuilder(this)
                .setAlbumLoader(MediaLoader())
                .setLocale(Locale.getDefault())
                .build()
        )

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        lifecycleHandler = AppLifecycleHandler(this)
        registerLifeCycleHandler(lifecycleHandler)
    }

    companion object {
        lateinit var mInstance: AppController

        public fun getInstance(): AppController {
            return mInstance
        }
    }


    fun registerLifeCycleHandler(lifeCycleHandler: AppLifecycleHandler?) {
        registerActivityLifecycleCallbacks(lifeCycleHandler)
        registerComponentCallbacks(lifeCycleHandler)
    }

    override fun onAppBackgrounded() {

    }

    override fun onAppForegrounded() {

    }


}
