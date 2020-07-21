package com.amit.ranjan.moviemala

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDexApplication

class MovieMalaApplication : MultiDexApplication() {
    companion object {
        private var appContext: Context? = null

        fun setAppContext(context: Context?) {
            appContext = context
        }

        fun getAppContext(): Context? {
            return appContext
        }
    }

    override fun onCreate() {
        super.onCreate()

    }
}