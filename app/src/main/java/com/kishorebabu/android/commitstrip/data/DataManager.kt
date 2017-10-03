package com.kishorebabu.android.commitstrip.data

import com.kishorebabu.android.commitstrip.data.model.Comic
import com.kishorebabu.android.commitstrip.data.model.ComicDao
import com.kishorebabu.android.commitstrip.data.remote.MvpStarterService
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.models.Tweet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(private val mMvpStarterService: MvpStarterService, private val mComicDao: ComicDao) {

//    fun getPokemonList(limit: Int): Single<List<String>> {
//        return mMvpStarterService.getPokemonList(limit)
//                .toObservable()
//                .flatMapIterable { (results) -> results }
//                .map { (name) -> name }
//                .toList()
//    }
//
//    fun getPokemon(name: String): Single<Pokemon> {
//        return mMvpStarterService.getPokemon(name)
//    }

    fun getTweets(callback: Callback<List<Tweet>>) {
        val twitterApiClient = TwitterCore.getInstance().apiClient
        val statusesService = twitterApiClient.statusesService
        val call = statusesService.userTimeline(null, "commitstrip", 999, null, null, true, true, null, false)
        call.enqueue(callback)
    }

    fun saveComic(id: Long, timestamp: Long, comicTitle: String, imageUrl: String) {
        val comic = Comic(id, timestamp, comicTitle, imageUrl, false)
        mComicDao.insertComic(comic)
    }

    fun getLastKnownComic(): Comic? {
        return mComicDao.getLatestComic()
    }

    fun getTweetsSinceId(sinceTweetId: Long, callback: Callback<List<Tweet>>) {
        val twitterApiClient = TwitterCore.getInstance().apiClient
        val statusesService = twitterApiClient.statusesService
        val call = statusesService.userTimeline(null, "commitstrip", 999, sinceTweetId, null, true, true, null, false)
        call.enqueue(callback)
    }


}