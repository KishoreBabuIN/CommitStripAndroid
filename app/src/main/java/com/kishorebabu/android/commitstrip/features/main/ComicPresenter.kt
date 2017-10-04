package com.kishorebabu.android.commitstrip.features.main

import com.kishorebabu.android.commitstrip.data.DataManager
import com.kishorebabu.android.commitstrip.features.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by kishore on 04/10/17.
 */
class ComicPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<ComicMvpView>() {
    fun onViewReadyReady(position: Int) {
        dataManager.getComicAtPosition(position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { comic ->
                            Timber.v("Comic #$position, $comic")
                            checkViewAttached()
                            mvpView?.showComic(comic)
                        },
                        { throwable ->
                            Timber.e(throwable, "Failed to get comic at position: $position")
                        }
                )
    }
}