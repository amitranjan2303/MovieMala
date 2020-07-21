package com.amit.ranjan.moviemala.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.amit.ranjan.moviemala.callback.ResponseListener
import com.amit.ranjan.moviemala.viewModel.BaseViewModel
import com.amit.ranjan.moviemala.model.DataItemModel
import com.amit.ranjan.moviemala.model.MovieItem
import com.amit.ranjan.moviemala.repo.MovieRepo

class MovieDetailsViewModel : BaseViewModel() {

    private var itemData: MutableLiveData<MovieItem>
    private var isFavBol: ObservableField<Boolean>

    init {
        itemData = MutableLiveData<MovieItem>()
        isFavBol = ObservableField<Boolean>()
    }

    fun getItemData(): MutableLiveData<MovieItem> {
        return itemData
    }

    fun getFav(): ObservableField<Boolean> {
        return isFavBol
    }

    fun setItemData(item: MovieItem) {
        itemData.value = item
        isFavBol.set(item.isFavourate)
    }

    fun setFavourate() {

        var item = itemData.value

        if (item?.isFavourate!!) {
            item?.isFavourate = false
        } else {
            item?.isFavourate = true
        }
        setItemData(item)
    }
}