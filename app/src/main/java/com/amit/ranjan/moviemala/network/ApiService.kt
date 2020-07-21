package com.amit.ranjan.moviemala.network

import com.amit.ranjan.moviemala.model.DataItemModel
import com.amit.ranjan.moviemala.utils.AppUtility
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(AppUtility.BASE_PATH)
    fun getAllMovies(
        @Query("page") currentPage: Int?,
        @Query("api_key") apiKey: String?,
        @Header("authorization") token: String?
    ): Call<DataItemModel>
}