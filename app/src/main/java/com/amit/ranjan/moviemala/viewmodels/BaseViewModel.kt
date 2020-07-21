package com.amit.ranjan.moviemala.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel() : ViewModel() {

    var isProgressActive: ObservableField<Boolean>
    var isFailure: ObservableField<Boolean>

    init {
        isProgressActive = ObservableField()
        isFailure = ObservableField(false)
    }

    fun setProgress(isActive: Boolean) {
        isProgressActive.set(isActive)
    }

    fun setFailure(failure: Boolean) {
        isFailure.set(failure)
    }

}