package com.example.albumviewer.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.example.albumviewer.common.TABLE_NAME

@Entity(tableName = TABLE_NAME, indices = [Index("title", unique = false)])
data class Album(@PrimaryKey(autoGenerate = true) val dbId: Int? = null,
                 val userID: Int,
                 val id: Int,
                 val title: String)