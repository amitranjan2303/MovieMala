package com.vasitum.manager

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.amit.ranjan.moviemala.R
import com.amit.ranjan.moviemala.MovieMalaApplication
import com.amit.ranjan.moviemala.callback.ResponseListener
import com.amit.ranjan.moviemala.model.BaseModel


class NetWorkErrorManager(var responseListener: ResponseListener?) {

    fun showErrorDialog(dialogIcon: Int, title: String, message: String?) {

        val appContext = MovieMalaApplication.Companion.getAppContext()
        val dialogBuilder = AlertDialog.Builder(appContext!!)
        val inflater =
            appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.network_error_dialog_view, null)
        val iconImageView = view.findViewById(R.id.icon_error) as ImageView
        val titleTextView = view.findViewById(R.id.title_error) as TextView
        val mesaageTextView = view.findViewById(R.id.tv_error_mgs) as TextView
        titleTextView.visibility = View.GONE
        if (!TextUtils.isEmpty(title)) {
            titleTextView.setText(title)
            titleTextView.visibility = View.VISIBLE
        }
        if (!TextUtils.isEmpty(message)) {
            mesaageTextView?.setText(message)
        }
        dialogBuilder.setView(view)
        val alertDialog: AlertDialog = dialogBuilder.create()
        alertDialog.setCancelable(false)
        val btnOk: Button = view.findViewById(R.id.buttonOk)
        btnOk.setOnClickListener(View.OnClickListener {
            alertDialog?.dismiss()
            responseListener?.onFailure(Exception(message))
        })
        alertDialog.show()

    }
}