package com.amit.ranjan.moviemala.appdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amit.ranjan.moviemala.model.MovieItem

/**
 * Created by Amit Ranjan on 21/07/2020.
 */
@Database(entities = [(MovieItem::class)], version = 1, exportSchema = false)
abstract class MovieMalaDB : RoomDatabase() {
    companion object {
        private var INSTANCE: MovieMalaDB? = null
        fun getDataBase(context: Context): MovieMalaDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MovieMalaDB::class.java,
                    "moviemala-db"
                )
                    .allowMainThreadQueries().build()
            }
            return INSTANCE as MovieMalaDB
        }
    }

    abstract fun getMovieDao(): MovieDao
}