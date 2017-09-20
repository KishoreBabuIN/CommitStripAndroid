package com.kishorebabu.android.commitstrip.features.splash

import android.util.Log
import com.kishorebabu.android.commitstrip.data.DataManager
import com.kishorebabu.android.commitstrip.features.base.BasePresenter
import com.kishorebabu.android.commitstrip.injection.ConfigPersistent
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.models.Tweet
import javax.inject.Inject

/**
 * Created by kishore on 20/09/17.
 */
@ConfigPersistent
class SplashPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<SplashMvpView>() {
    override fun attachView(mvpView: SplashMvpView) {
        super.attachView(mvpView)
    }

    fun onViewReady() {
        dataManager.getTweets(999, object : Callback<List<Tweet>>() {
            override fun success(result: Result<List<Tweet>>) {
                Log.v("asdf", "Result Size: ${result.data.size}")
                result.data.forEach { tweet ->
                    if (tweet.extendedEntities.media.size > 0) {
                        if (tweet.text.indexOf("https://t.co/") > -1) {
                            try {
                                val comicTitle = tweet.text.subSequence(0, tweet.text.indexOf("https://t.co/")).trim()
                                val imageUrl = tweet.extendedEntities.media[0].mediaUrl
                                Log.v("asdf", "Date: ${tweet.createdAt} Title: $comicTitle ImageUrl: $imageUrl")
                            } catch (e: Exception) {
                                Log.e("asdf", "No Url found. ${tweet.text} ${e.message}")
                            }

                        } else {
                            Log.e("asdf", "No Url found. ${tweet.text}")
                        }
                    } else {
                        Log.e("asdf", "No Url found. ${tweet.text}")
                    }
                }
            }

            override fun failure(exception: TwitterException) {
                //Do something on failure
                Log.e("Asdf", exception.toString())
            }
        })
    }
}