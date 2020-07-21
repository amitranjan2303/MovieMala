package com.amit.ranjan.moviemala.viewholders

import android.os.Build
import android.text.Html
import android.text.TextUtils
import android.view.View
import com.amit.ranjan.moviemala.MovieMalaApplication
import com.amit.ranjan.moviemala.R
import com.amit.ranjan.moviemala.callback.Navigator
import com.amit.ranjan.moviemala.databinding.ItemMoveViewBinding
import com.amit.ranjan.moviemala.model.MovieItem
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso


class MovieViewHolder :
    BaseViewHolder {
    private var itemViewBinding: ItemMoveViewBinding
    private var navigator: Navigator
    private lateinit var item: MovieItem
    private lateinit var cache: LruCache

    constructor(
        itemViewBinding: ItemMoveViewBinding,
        navigator: Navigator
    ) : super(itemViewBinding.root) {
        this.itemViewBinding = itemViewBinding
        this.navigator = navigator
        itemViewBinding.rootConatiner.setOnClickListener(this)
        itemViewBinding.rootConatiner.setOnLongClickListener(this)
        cache = LruCache(MovieMalaApplication.getAppContext()!!);
    }

    override fun onBind(it: Any) {

        if (it is MovieItem) {
            item = it
            if (!TextUtils.isEmpty(item.title)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    itemViewBinding.tvTitle.text =
                        Html.fromHtml(item.title, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    itemViewBinding.tvTitle.text = Html.fromHtml(item.title)
                }
                itemViewBinding.frTitle.visibility = View.VISIBLE
            } else {
                itemViewBinding.frTitle.visibility = View.GONE
            }

            if (item.isFavourate) {
                itemViewBinding.frFavourate.visibility = View.VISIBLE
            } else {
                itemViewBinding.frFavourate.visibility = View.GONE
            }

            itemViewBinding.ratingBar.numStars = 5
            itemViewBinding.ratingBar.visibility = View.GONE
            itemViewBinding.ratingBar.rating = item.voteAverage.toFloat()

            var baseImagePath = "https://image.tmdb.org/t/p/original"
            if (!TextUtils.isEmpty(item?.posterPath)) {
                baseImagePath = baseImagePath + item.posterPath
                Picasso.get().load(baseImagePath)
                    .placeholder(R.drawable.ic_loading)
                    .resize(100, 160)
                    .into(itemViewBinding.ivIcon)
            } else {
                Picasso.get().load(R.drawable.ic_loading).into(itemViewBinding.ivIcon)
            }
        }
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        if (navigator != null) {
            navigator.moveOnAction(adapterPosition, item)
        }
    }

    override fun onLongClick(v: View?): Boolean {
        if (navigator != null) {
            navigator.moveOnLongClickAction(adapterPosition, item)
        }
        return super.onLongClick(v)
    }
}