package com.example.albumviewer.ui.home

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.albumviewer.R
import com.example.albumviewer.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val activityHomeBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        activityHomeBinding.progressVisibility = homeViewModel.getProgressObservable()

        val linearLayoutManager = LinearLayoutManager(this)
        val homeAdapter = HomeAdapter()
        rvData.layoutManager = linearLayoutManager
        rvData.addItemDecoration(DividerItemDecoration(this, linearLayoutManager.orientation))
        rvData.adapter = homeAdapter


        homeViewModel.getAlbumsObservable().observe(this, Observer { homeAdapter.setData(it) })

        homeViewModel.getData()


    }
}
