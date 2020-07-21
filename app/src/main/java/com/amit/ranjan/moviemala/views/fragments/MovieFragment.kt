package com.amit.ranjan.moviemala.views.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amit.ranjan.moviemala.MovieMalaApplication
import com.amit.ranjan.moviemala.R
import com.amit.ranjan.moviemala.adapters.MovieAdapter
import com.amit.ranjan.moviemala.callback.Navigator
import com.amit.ranjan.moviemala.databinding.FragmentMovieBinding
import com.amit.ranjan.moviemala.databinding.FragmentMovieOptionsBinding
import com.amit.ranjan.moviemala.model.DataItemModel
import com.amit.ranjan.moviemala.model.MovieItem
import com.amit.ranjan.moviemala.utils.AppUtility
import com.amit.ranjan.moviemala.utils.PrefManager
import com.amit.ranjan.moviemala.utils.RecyclerViewLoadMoreScroll
import com.amit.ranjan.moviemala.viewmodels.MovieViewModel
import com.amit.sampleapp.views.activities.MainActivity
import kotlinx.android.synthetic.main.error_view.view.*

class MovieFragment : BaseFragment(), Navigator {

    private lateinit var mBinding: FragmentMovieBinding
    private lateinit var mViewModel: MovieViewModel
    private lateinit var mItemList: ArrayList<Any>
    private lateinit var mAdapter: MovieAdapter
    private lateinit var scrollHandler: RecyclerViewLoadMoreScroll

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie,
            container,
            false
        )
        mBinding.viewModel = mViewModel
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setUpToolBar("Movies", false)
        mViewModel.getAllMovie()
        mItemList = ArrayList()
        mAdapter = MovieAdapter(mItemList, this)
        var layoutManager = GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
        mBinding.rvList.setHasFixedSize(true)
        mBinding.rvList.setItemViewCacheSize(20)
        mBinding.rvList.layoutManager = layoutManager
        mBinding.rvList.adapter = mAdapter
        getObserable()
        //////////////////  setUp Listener ////////////
        mBinding.frErrorView.error_container.tv_retry_btn.setOnClickListener(this)
        scrollHandler = RecyclerViewLoadMoreScroll(layoutManager)
        scrollHandler.setOnLoadMoreListener(this)
        mBinding.rvList.addOnScrollListener(scrollHandler)
    }

    override fun moveOnAction(position: Int, item: Any?) {
        if (item != null && item is MovieItem) {
            var fragment: BaseFragment = MovieDetailsFragment.getInstance(position, item)
            fragment.setTargetFragment(this, 1)
            fragment.show(activity?.supportFragmentManager!!, fragment.javaClass.simpleName)
        }
    }

    override fun moveOnLongClickAction(position: Int, item: Any?) {
        if (item is MovieItem) {
            var message: String = ""
            if (item.isFavourate) {
                item.isFavourate = false
                message = "Favourate Removed"
            } else {
                item.isFavourate = true
                message = "Added to Favourate"
            }
            updateDbAndList(item, position)
//            mItemList.removeAt(position)
//            mItemList.add(position, item)
//            mAdapter.updateList(position, 1)
//            mViewModel.update(item.isFavourate, item.id)
            customSnackBar(mBinding.root, context!!, message)
        }

    }

    override fun onLoadMore() {
        mViewModel.getAllMovie()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && data != null) {
            var position = data.getIntExtra("position", -1)
            var item = data.getSerializableExtra("item") as MovieItem

            if (position != -1 && item != null) {
                updateDbAndList(item, position)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun updateDbAndList(item: MovieItem, position: Int) {
        mItemList.removeAt(position)
        mItemList.add(position, item)
        mAdapter.updateList(position, 1)
        mViewModel.update(item.isFavourate, item.id)
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when (v?.id) {
            R.id.tv_retry_btn -> {
                mViewModel.getAllMovie()
            }
        }
    }

    private fun getObserable() {
        mViewModel.getItemData().observe(this, Observer {

            mItemList.addAll(it.results as ArrayList<Any>)
            mAdapter.updateList(mItemList)
            scrollHandler.setLoaded()

            if (AppUtility.isNetworkAvailable(context!!)) {
                var pref = PrefManager(MovieMalaApplication.getAppContext()!!)
                pref.setCurrentPage(it.currentPage!!)
                pref.setTotalPage(it.totalPage!!)
                mViewModel.storeMovies(it.results!!)
            }
        })

        mViewModel.getListMovies().observe(this, Observer {

            var dataItem = DataItemModel();
            dataItem.results = ArrayList(it)
            /*------------------------------*/
            var pref = PrefManager(MovieMalaApplication.getAppContext()!!)
            dataItem.currentPage = pref.getCurrentPage()
            dataItem.totalPage = pref.getTotalPage()
            /*-------------------------------*/
            mViewModel.setItemData(dataItem)

            Log.d("appLog", "Pref CP" + pref.getCurrentPage())
            Log.d("appLog", "Pref TP" + pref.getTotalPage())


            if (AppUtility.isNetworkAvailable(MovieMalaApplication.getAppContext()!!)) {

            }
        })
    }

}