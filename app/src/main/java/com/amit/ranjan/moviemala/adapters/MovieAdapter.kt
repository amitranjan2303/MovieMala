package com.amit.ranjan.moviemala.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amit.ranjan.moviemala.R
import com.amit.ranjan.moviemala.callback.Navigator
import com.amit.ranjan.moviemala.databinding.ItemMoveViewBinding
import com.amit.ranjan.moviemala.model.MovieItem
import com.amit.ranjan.moviemala.viewholders.BaseViewHolder
import com.amit.ranjan.moviemala.viewholders.MovieViewHolder

open class MovieAdapter(var itemList: ArrayList<Any>?, var navigatorCallBack: Navigator) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        var viewBinding: ItemMoveViewBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        var viewHolder: BaseViewHolder = MovieViewHolder(viewBinding, navigatorCallBack)
        return viewHolder
    }

    override fun getItemCount(): Int {
        var count: Int = 0
        if (!itemList.isNullOrEmpty()) {
            count = itemList?.size!!
        }
        return count
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            holder.onBind(itemList?.get(position)!!)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_move_view
    }

    override fun getItemId(position: Int): Long {
        return (itemList?.get(position) as MovieItem).id?.hashCode().toLong()
    }

    fun updateList(list: ArrayList<Any>?) {
        var oldCount: Int = itemList?.size!!
        itemList = list
        this.notifyItemRangeInserted(oldCount, itemList?.size!!);
    }

    fun updateList(position: Int, itemCount: Int) {
        this.notifyItemRangeChanged(position, itemCount)
    }

}
