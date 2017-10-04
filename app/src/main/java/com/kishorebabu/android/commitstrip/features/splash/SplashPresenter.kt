package com.kishorebabu.android.commitstrip.features.splash

import com.kishorebabu.android.commitstrip.data.DataManager
import com.kishorebabu.android.commitstrip.features.base.BasePresenter
import com.kishorebabu.android.commitstrip.injection.ConfigPersistent
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.models.Tweet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by kishore on 20/09/17.
 */
@ConfigPersistent
class SplashPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<SplashMvpView>() {

    fun onViewReady() {
        dataManager.getLastKnownComic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { comic ->
                            Timber.v("Last Known Comic: %s", comic.toString())
                            dataManager.getTweetsSinceId(comic.id, TwitterResponseCallback(dataManager))
                        },
                        { error ->
                            Timber.e(error, "Failed to get last knonw comic")

                        },
                        {
                            Timber.v("No Last Known Comic")
                            dataManager.getTweets(TwitterResponseCallback(dataManager))
                        }
                )
    }

    class TwitterResponseCallback
    constructor(private val dataManager: DataManager) : Callback<List<Tweet>>() {
        override fun success(result: Result<List<Tweet>>) {
            Timber.v("Result Size: ${result.data.size}")
            result.data.forEach { tweet ->
                if (tweet.extendedEntities.media.size > 0) {
                    if (tweet.text.indexOf("https://t.co/") > -1) {
                        try {
                            val comicTitle = tweet.text.subSequence(0, tweet.text.indexOf("https://t.co/")).trim()
                            val imageUrl = tweet.extendedEntities.media[0].mediaUrl
                            Timber.v("Date: ${tweet.createdAt} Title: $comicTitle ImageUrl: $imageUrl")
                            val dateFormat = "EEE MMM dd HH:mm:ss Z yyyy"
                            val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.ENGLISH)
                            val date = simpleDateFormat.parse(tweet.createdAt)

                            dataManager.saveComic(tweet.id, date.time, comicTitle.toString(), imageUrl)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe()

                        } catch (e: Exception) {
                            Timber.e("No Url found. ${tweet.text} ${e.message}")
                        }

                    } else {
                        Timber.e("No Url found. ${tweet.text}")
                    }
                } else {
                    Timber.e("No Url found. ${tweet.text}")
                }
            }
        }

        override fun failure(exception: TwitterException) {
            //Do something on failure
            Timber.e(exception.toString())
        }
    }
}

