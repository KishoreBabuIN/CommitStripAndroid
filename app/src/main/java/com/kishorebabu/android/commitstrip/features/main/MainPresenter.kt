package com.kishorebabu.android.commitstrip.features.main

import com.kishorebabu.android.commitstrip.data.DataManager
import com.kishorebabu.android.commitstrip.features.base.BasePresenter
import com.kishorebabu.android.commitstrip.injection.ConfigPersistent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@ConfigPersistent
class MainPresenter @Inject
constructor(private val mDataManager: DataManager) : BasePresenter<MainMvpView>() {

    fun onViewReady() {
        mDataManager.getTotalComicsCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { count ->
                            Timber.v("Total comics count: $count")
                            checkViewAttached()
                            mvpView?.showComics(count)
                        },
                        { throwable ->
                            Timber.e(throwable, "Failed to get total comics count")
                        }
                )
    }

    fun onComicPositionSelected(position: Int) {
        mDataManager.getComicAtPosition(position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { comic ->
                            checkViewAttached()
                            mvpView?.showComicTitleAndDate(comic)

                        },
                        { throwable ->
                            Timber.e(throwable, "Failed to get comic at position: $position")
                        }
                )
    }

}