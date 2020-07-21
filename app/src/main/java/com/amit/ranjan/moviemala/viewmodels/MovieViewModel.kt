package com.amit.ranjan.moviemala.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.amit.ranjan.moviemala.MovieMalaApplication
import com.amit.ranjan.moviemala.appdb.MovieMalaDB
import com.amit.ranjan.moviemala.callback.ResponseListener
import com.amit.ranjan.moviemala.viewModel.BaseViewModel
import com.amit.ranjan.moviemala.model.DataItemModel
import com.amit.ranjan.moviemala.model.MovieItem
import com.amit.ranjan.moviemala.repo.MovieRepo
import com.amit.ranjan.moviemala.utils.AppUtility
import com.amit.ranjan.moviemala.utils.PrefManager
import kotlinx.coroutines.Delay
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MovieViewModel : BaseViewModel() {

    private var itemData: MutableLiveData<DataItemModel>
    private var mCurrentPage = 0

    /*------------------------------*/
    private var appDb: MovieMalaDB
    private lateinit var listMovies: LiveData<List<MovieItem>>
    /*---------------------------------*/


    init {
        itemData = MutableLiveData<DataItemModel>()
        appDb = MovieMalaDB.getDataBase(MovieMalaApplication.getAppContext()!!)

    }

    fun getListMovies(): LiveData<List<MovieItem>> {
//        GlobalScope.async {
//            listMovies = appDb.getMovieDao().getAllMovies()
//        }
        listMovies = appDb.getMovieDao().getAllMovies()
        return listMovies
    }

    fun storeMovies(list: ArrayList<MovieItem>) {
        GlobalScope.async {
            appDb.getMovieDao().insert(list)
        }

    }

//    suspend fun storeMoviesAsync(list: List<MovieItem>) = GlobalScope.async {
//        appDb.getMovieDao().insert(list)
//    }

    fun getItemData(): MutableLiveData<DataItemModel> {
        return itemData
    }

    fun setItemData(item: DataItemModel) {
        itemData.value = item
    }

    fun getAllMovie() {

        if (mCurrentPage == 0) {
            mCurrentPage = mCurrentPage + 1
        } else {
            var itemD: DataItemModel = itemData.value as DataItemModel
            if (mCurrentPage < itemD?.totalPage?.toInt()!!) {
                mCurrentPage = mCurrentPage + 1
            }
            Log.d("appLog ", "TotallPage : " + itemD.totalPage)
        }

        Log.d("appLog ", "Page : " + mCurrentPage)
        if (AppUtility.isNetworkAvailable(MovieMalaApplication.getAppContext()!!)) {
            setProgress(true)
            setFailure(false)


            MovieRepo.getMovies(mCurrentPage, object : ResponseListener {
                override fun onSuccess(instance: Any) {
                    setProgress(false)
                    itemData.value = instance as DataItemModel
                }

                override fun onFailure(t: Any) {
                    setFailure(true)
                    setProgress(false)
                }
            })
        }
//        else {
//
//            listMovies = getListMovies()
//            var dataItem = DataItemModel();
//            dataItem.currentPage = 0
//            dataItem.totalPage = 500
////            dataItem.results = ArrayList(listMovies.value)
//            itemData.value = dataItem
//        }
    }

    fun update(isFav: Boolean?, movieId: Int) {
        GlobalScope.async {
            appDb.getMovieDao().update(isFav, movieId)
        }

    }
}