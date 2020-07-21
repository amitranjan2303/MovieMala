package com.amit.ranjan.moviemala.callback

interface Navigator {
    fun moveOnAction(position: Int, item: Any?)
    fun moveOnLongClickAction(position: Int, item: Any?)
    fun onLoadMore()
}