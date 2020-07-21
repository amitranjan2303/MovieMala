package com.amit.ranjan.moviemala.model

import com.google.gson.annotations.SerializedName

data class DataItemModel(

    @SerializedName("page")
    var currentPage: Int? = 0,

    @SerializedName("total_results")
    var totalResult: Int? = 0,

    @SerializedName("total_pages")
    var totalPage: Int? = 0,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("results")
    var results: ArrayList<MovieItem>? = null

) : BaseModel() {


//    @JsonIgnore
//     var additionalProperties: Map<String, Any> =
//        HashMap()

}