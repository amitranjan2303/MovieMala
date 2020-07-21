package com.amit.ranjan.moviemala.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager(private val mContext: Context) {

    // shared pref mode
    var PRIVATE_MODE: Int = 0

    // Shared preferences file name
    private val PREF_NAME = "moviemala-app"
    private val CURRENT_PAGE = "current_page"
    private val TOTALPAGE = "total_page"

    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    init {
        pref = mContext?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref?.edit()
    }


    fun getCurrentPage(): Int? {
        return pref?.getInt(CURRENT_PAGE, 0)
    }

    fun setCurrentPage(currentPage: Int) {
        editor?.putInt(CURRENT_PAGE, currentPage)
        editor?.commit()
    }

    fun getTotalPage(): Int? {
        return pref?.getInt(TOTALPAGE, 0)
    }

    fun setTotalPage(totalPage: Int) {
        editor?.putInt(TOTALPAGE, totalPage)
        editor?.commit()
    }


}