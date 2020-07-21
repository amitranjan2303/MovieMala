package com.amit.ranjan.moviemala.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MovieItem")
class MovieItem(

    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "type")
    @SerializedName("type")
    var type: String? = "movieItem",

    @SerializedName("isFavourate")
    @ColumnInfo(name = "isFavourate")
    var isFavourate: Boolean = false,

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    var popularity: Double,

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    var voteCount: Int,

    @SerializedName("video")
    @ColumnInfo(name = "video")
    var video: Boolean,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    var adult: Boolean,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    var originalTitle: String? = null,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String? = null

) : BaseModel() {
}