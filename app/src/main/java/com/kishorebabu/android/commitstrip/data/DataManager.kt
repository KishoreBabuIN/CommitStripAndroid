package com.kishorebabu.android.commitstrip.data

import com.kishorebabu.android.commitstrip.data.model.Comic
import com.kishorebabu.android.commitstrip.data.model.ComicDao
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.core.services.StatusesService
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataManager @Inject
constructor(private val twitterStatusesService: StatusesService,
            private val mComicDao: ComicDao) {

    fun getTweets(callback: Callback<List<Tweet>>) {
        val call = twitterStatusesService.userTimeline(null, "commitstrip", 999, null, null, true, true, null, false)
        call.enqueue(callback)
    }

    fun saveComic(id: Long, timestamp: Long, comicTitle: String, imageUrl: String): Single<Unit> {
        val comic = Comic(id, timestamp, comicTitle, imageUrl, false)
        return Single.fromCallable {
            mComicDao.insertComic(comic)
        }

    }

    fun getLastKnownComic(): Maybe<Comic> {
        return mComicDao.getLatestComic()
    }

    fun getTweetsSinceId(sinceTweetId: Long, callback: Callback<List<Tweet>>) {
        val call = twitterStatusesService.userTimeline(null, "commitstrip", 999, sinceTweetId, null, true, true, null, false)
        call.enqueue(callback)
    }

    fun saveComics(comics: ArrayList<Comic>): Single<Unit> {
        return Single.fromCallable {
            mComicDao.insertComics(comics)
        }

    }

    fun getComicAtPosition(position: Int): Single<Comic> {
        return mComicDao.getComicAtPosition(position)
    }

    fun getTotalComicsCount(): Single<Int> {
        return mComicDao.getTotalComicsCount()
    }


}