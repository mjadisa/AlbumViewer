package com.example.albumviewer.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.example.albumviewer.common.Utils
import com.example.albumviewer.data.Album
import com.example.albumviewer.repo.AlbumsRepository
import com.example.albumviewer.repo.DataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val albumsRepository: DataSource, private val utils: Utils) : ViewModel() {

    private val albumsObservable = MutableLiveData<List<Album>>()
    private val progressObservable = ObservableBoolean(false)
    private val errorObservable = MutableLiveData<String>()

    private val compositeDisposable = CompositeDisposable()


    fun getAlbumsObservable(): LiveData<List<Album>> = albumsObservable

    fun getProgressObservable() = progressObservable

    fun getErrorObservable() = errorObservable

    fun getData() {
        compositeDisposable.add(albumsRepository.getSortedAlbums(true)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressObservable.set(true) }
            .doOnEvent { _, _ -> progressObservable.set(false) }
            .doOnSuccess { handleSubscription(it.isEmpty()) }
            .subscribe({ albumsObservable.value = it }, { errorObservable.value = it.message })
        )
    }

    private fun handleSubscription(isEmpty: Boolean) {
        if (!utils.isOnline() && !isEmpty) {
            errorObservable.value = "Network Connection Not Available, cached values will be shown"
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}