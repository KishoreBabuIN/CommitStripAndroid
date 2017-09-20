package com.kishorebabu.android.commitstrip.data

import com.kishorebabu.android.commitstrip.data.model.Pokemon
import com.kishorebabu.android.commitstrip.data.remote.MvpStarterService
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.models.Tweet
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(private val mMvpStarterService: MvpStarterService) {

    fun getPokemonList(limit: Int): Single<List<String>> {
        return mMvpStarterService.getPokemonList(limit)
                .toObservable()
                .flatMapIterable { (results) -> results }
                .map { (name) -> name }
                .toList()
    }

    fun getPokemon(name: String): Single<Pokemon> {
        return mMvpStarterService.getPokemon(name)
    }

    fun getTweets(tweetCount: Int, callback: Callback<List<Tweet>>) {
        val twitterApiClient = TwitterCore.getInstance().apiClient
        val statusesService = twitterApiClient.statusesService
        val call = statusesService.userTimeline(null, "commitstrip", tweetCount, null, null, true, true, null, false)
        call.enqueue(callback)

    }

}