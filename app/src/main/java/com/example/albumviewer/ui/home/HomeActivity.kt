package com.example.albumviewer.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.albumviewer.R
import dagger.android.AndroidInjection

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
