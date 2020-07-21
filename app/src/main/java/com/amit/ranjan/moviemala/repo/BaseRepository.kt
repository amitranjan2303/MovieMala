package com.amit.ranjan.moviemala.repo

import com.amit.ranjan.moviemala.model.BaseModel
import com.amit.ranjan.moviemala.model.DataItemModel
import com.amit.ranjan.moviemala.network.ApiService
import com.amit.ranjan.moviemala.network.AppRetrofitClient
import retrofit2.Response

open class BaseRepository {

    var mApiService: ApiService

    init {
        mApiService = AppRetrofitClient.buildService(ApiService::class.java)
    }

    fun checkResponse(response: Response<DataItemModel>): Boolean {
        var isValidResponse: Boolean = false
        if (response != null && response.isSuccessful) {
            isValidResponse = true
        }
        return isValidResponse
    }

    fun getError(response: BaseModel) {
//        response.errorMessage="something went wrong"
    }
}