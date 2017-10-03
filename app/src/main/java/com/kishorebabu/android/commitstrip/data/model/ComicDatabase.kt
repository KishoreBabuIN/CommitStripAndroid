package com.kishorebabu.android.commitstrip.data.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by kishore on 20/09/17.
 */
@Database(entities = arrayOf(Comic::class), version = 1, exportSchema = false)
abstract class ComicDatabase : RoomDatabase() {
    abstract fun comicDao(): ComicDao
}