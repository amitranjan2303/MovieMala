package com.amit.ranjan.moviemala.views.fragments

import android.content.Context
import android.view.View
import androidx.fragment.app.DialogFragment
import com.amit.sampleapp.views.activities.BaseActivity

open class BaseFragment : DialogFragment(), View.OnClickListener, View.OnLongClickListener {

    protected fun addFragment(fragment: BaseFragment) {
        (activity as BaseActivity).addFragment(fragment)
    }

    protected fun customSnackBar(containerLayout: View, context: Context, message: String?) {
        (activity as BaseActivity).customSnackBar(containerLayout, context, message)
    }

    override fun onClick(v: View?) {
    }

    override fun onLongClick(v: View?): Boolean {
        return false
    }
}