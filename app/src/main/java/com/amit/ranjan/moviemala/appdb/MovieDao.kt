package com.amit.ranjan.moviemala.appdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.amit.ranjan.moviemala.model.MovieItem

@Dao
interface MovieDao {

    /**********Get Data By List*************/
    @Query("select * from MovieItem")
    fun getAllMovies(): LiveData<List<MovieItem>>

    /**********Insert Data By List*************/
    @Insert
    fun insert(movies: List<MovieItem>)

    /**********Updating only favourate By id*************/

    @Query("UPDATE MovieItem SET isFavourate=:isFav WHERE id = :movieId")
    fun update(isFav: Boolean?, movieId: Int)
}