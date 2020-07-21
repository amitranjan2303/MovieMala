package com.amit.ranjan.moviemala.callback

interface ResponseListener {
    fun onSuccess(instance: Any)
    fun onFailure(t: Any)
}