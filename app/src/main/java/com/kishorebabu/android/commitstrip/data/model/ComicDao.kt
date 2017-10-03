package com.kishorebabu.android.commitstrip.data.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

/**
 * Created by kishore on 20/09/17.
 */
@Dao
interface ComicDao {
    @Query("SELECT * from comic")
    fun getAllComics(): List<Comic>

    @Insert(onConflict = REPLACE)
    fun insertComic(comic: Comic)

    @Query("SELECT * from COMIC ORDER BY date DESC LIMIT 1")
    fun getLatestComic(): Comic?
}