package com.kishorebabu.android.commitstrip.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by kishore on 20/09/17.
 */
@Entity(tableName = "comic")
data class Comic(
        @PrimaryKey(autoGenerate = false) var id: Long,
        @ColumnInfo(name = "date") var date: Long,
        @ColumnInfo(name = "title") var title: String,
        @ColumnInfo(name = "image_url") var imageUrl: String,
        @ColumnInfo(name = "is_favorite") var isFavorite: Boolean = false

)