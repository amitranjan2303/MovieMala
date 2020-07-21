package com.amit.ranjan.moviemala.repo

import com.amit.ranjan.moviemala.MovieMalaApplication
import com.amit.ranjan.moviemala.appdb.MovieMalaDB
import com.amit.ranjan.moviemala.callback.ResponseListener
import com.amit.ranjan.moviemala.model.DataItemModel
import com.amit.ranjan.moviemala.utils.AppUtility
import com.vasitum.manager.NetWorkErrorManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MovieRepo : BaseRepository() {

    fun getMovies(currentPage:Int,responseListener: ResponseListener?) {

        mApiService.getAllMovies(currentPage,AppUtility.apiKey, AppUtility.token)
            .enqueue(object : Callback<DataItemModel> {

                override fun onResponse(
                    call: Call<DataItemModel>,
                    response: Response<DataItemModel>
                ) {
                    if (checkResponse(response)) {
                        responseListener?.onSuccess(response.body() as DataItemModel)
                    } else {
                        //Error Handling
                        NetWorkErrorManager(responseListener).showErrorDialog(
                            -1,
                            "Network Issue",
                            "!Opps Something Wrong"
                        )
                    }
                }

                override fun onFailure(call: Call<DataItemModel>, t: Throwable) {
                    //Error Handling
                    NetWorkErrorManager(responseListener).showErrorDialog(
                        -1,
                        "Network Issue",
                        t.message
                    )
                }
            })

    }
}