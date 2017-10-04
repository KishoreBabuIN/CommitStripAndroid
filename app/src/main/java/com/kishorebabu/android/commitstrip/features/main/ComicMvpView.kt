package com.kishorebabu.android.commitstrip.features.main

import com.kishorebabu.android.commitstrip.data.model.Comic
import com.kishorebabu.android.commitstrip.features.base.MvpView

/**
 * Created by kishore on 04/10/17.
 */
interface ComicMvpView : MvpView {
    fun showComic(comic: Comic)
}