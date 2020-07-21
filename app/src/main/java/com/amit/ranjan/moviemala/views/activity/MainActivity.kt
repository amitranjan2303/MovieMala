package com.amit.sampleapp.views.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.amit.ranjan.moviemala.R
import com.amit.ranjan.moviemala.databinding.ActivityMainBinding
import com.amit.ranjan.moviemala.views.fragments.MovieFragment
import kotlinx.android.synthetic.main.app_tool_bar_view.view.*

class MainActivity : BaseActivity() {

    private lateinit var mBinding: ActivityMainBinding

    //    private lateinit var mViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(mBinding?.includeToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
//        mViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        if (savedInstanceState == null) {
            addFragment(MovieFragment())
        }

    }

    fun setUpToolBar(title: String, isEnable: Boolean) {
        mBinding.includeToolbar.toolbar.tool_title.text = title

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
