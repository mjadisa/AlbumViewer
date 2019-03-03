package com.example.albumviewer.common

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

interface Utils {
    fun isOnline(): Boolean
}

class UtilsImpl(private val application: Application) : Utils {
    override fun isOnline(): Boolean {
        val connectionManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectionManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}