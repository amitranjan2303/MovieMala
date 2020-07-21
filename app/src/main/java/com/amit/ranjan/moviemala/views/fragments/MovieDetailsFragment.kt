package com.amit.ranjan.moviemala.views.fragments

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.amit.ranjan.moviemala.R
import com.amit.ranjan.moviemala.databinding.FragmentMoveDetailsBinding
import com.amit.ranjan.moviemala.model.MovieItem
import com.amit.ranjan.moviemala.viewmodels.MovieDetailsViewModel
import com.squareup.picasso.Picasso

class MovieDetailsFragment : BaseFragment() {


    companion object {
        private val KEY_ITEM: String = "item"
        private val KEY_POSITION: String = "position"

        fun getInstance(position: Int, movieItem: MovieItem): MovieDetailsFragment {
            var bundle: Bundle = Bundle()
            bundle.putSerializable(KEY_ITEM, movieItem)
            bundle.putInt(KEY_POSITION, position)
            var fragment = MovieDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var mBinding: FragmentMoveDetailsBinding
    private lateinit var mViewModel: MovieDetailsViewModel
    private lateinit var mMovieItem: MovieItem
    private var mPosition: Int? = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_move_details,
            container,
            false
        )
        mBinding.viewModel = mViewModel
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            mMovieItem = it.getSerializable(KEY_ITEM) as MovieItem
            mPosition = it.getInt(KEY_POSITION)
        }
        mMovieItem?.let {
            mViewModel.setItemData(it)
        }

        var baseImagePath = "https://image.tmdb.org/t/p/original"

        if (!TextUtils.isEmpty(mMovieItem?.posterPath)) {
            Picasso.get().load(baseImagePath + mMovieItem.posterPath)
                .placeholder(R.drawable.ic_loading)
                .resize(300, 200)
                .into(mBinding.ivIcon)
        } else {
            Picasso.get().load(R.drawable.ic_loading).into(mBinding.ivIcon)
        }

        //setUpListener
        mBinding.root.setOnLongClickListener(this)
        mBinding.ivClose.setOnClickListener(this)
    }

    override fun onLongClick(v: View?): Boolean {
        mViewModel.setFavourate()
        var message = "Favourate Removed"
        if (mMovieItem.isFavourate) {
            message = "Favourate Added"
        }
//        customSnackBar(mBinding.cvCard, context!!, message)
        return super.onLongClick(v)
    }

    override fun getTheme(): Int {
        return R.style.AppDialogTheme
    }

    override fun onResume() {
        super.onResume()
        val window: Window? = dialog!!.window
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        window?.setGravity(Gravity.CENTER);
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when (v?.id) {
            R.id.iv_close -> {
                if (dialog != null) {
                    dialog?.dismiss()
                }
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        senResultBack()
    }

    private fun senResultBack() {
        var resultCode = Activity.RESULT_CANCELED
        if (mMovieItem.isFavourate) {
            resultCode = Activity.RESULT_OK
        }
        var intent = Intent()
        intent.putExtra(KEY_ITEM, mMovieItem)
        intent.putExtra(KEY_POSITION, mPosition)
        targetFragment?.onActivityResult(targetRequestCode, resultCode, intent)
    }

}