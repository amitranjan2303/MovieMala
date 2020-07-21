package com.amit.ranjan.moviemala.utils

import android.content.Context
import android.net.ConnectivityManager

class AppUtility {
    companion object {
        val BASE_URL = "https://api.themoviedb.org"
        const val BASE_PATH = "/3/movie/popular"
        const val token = "your bearar token"
        const val apiKey = "your api key"

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}