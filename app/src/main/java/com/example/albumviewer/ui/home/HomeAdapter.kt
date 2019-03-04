package com.example.albumviewer.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.albumviewer.data.Album
import com.example.albumviewer.databinding.ItemAlbumBinding
import kotlinx.android.extensions.LayoutContainer

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.AlbumViewHolder>() {

    private val data = mutableListOf<Album>()


    fun setData(data: List<Album>?) {
        data?.let {
            this.data.clear()
            this.data.addAll(data)
            notifyDataSetChanged()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(ItemAlbumBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(viewHolder: AlbumViewHolder, position: Int) {
        viewHolder.bind(data[position])
    }

    inner class AlbumViewHolder(private val itemBinding: ItemAlbumBinding) : RecyclerView.ViewHolder(itemBinding.root),
        LayoutContainer {
        override val containerView: View?
            get() = itemBinding.root

        fun bind(album: Album) {
            itemBinding.album = album
            itemBinding.executePendingBindings()
        }
    }
}