package com.example.albumviewer.ui.home

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.albumviewer.R
import com.example.albumviewer.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    private val countingIdlingResource: CountingIdlingResource = CountingIdlingResource(this.javaClass.simpleName)

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

        countingIdlingResource.increment()

        homeViewModel.getData()


        homeViewModel.getAlbumsObservable().observe(this, Observer {
            homeAdapter.setData(it)
            countingIdlingResource.decrement() })

        homeViewModel.getErrorObservable().observe(this,
            Observer { Toast.makeText(this, it, Toast.LENGTH_LONG).show() })



    }

    fun getIdlingResource() = countingIdlingResource
}
