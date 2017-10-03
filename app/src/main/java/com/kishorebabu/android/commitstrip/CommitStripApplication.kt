package com.kishorebabu.android.commitstrip

import android.content.Context
import android.support.multidex.MultiDexApplication
import android.util.Log
import com.facebook.stetho.Stetho
import com.kishorebabu.android.commitstrip.injection.component.ApplicationComponent
import com.kishorebabu.android.commitstrip.injection.component.DaggerApplicationComponent
import com.kishorebabu.android.commitstrip.injection.module.ApplicationModule
import com.squareup.leakcanary.LeakCanary
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import timber.log.Timber

class CommitStripApplication : MultiDexApplication() {

    internal var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        val config = TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(TwitterAuthConfig("NpfUInjEcQc22BXioCkOGpiGI", "nLcc78pjnqsIZwsYH0ySfDrEjYGCaNWr9Fm5BrZeZqRLnlZHic"))
                .debug(true)
                .build()
        Twitter.initialize(config)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
            LeakCanary.install(this)
        }
    }

    // Needed to replace the component with a test specific one
    var component: ApplicationComponent
        get() {
            if (mApplicationComponent == null) {
                mApplicationComponent = DaggerApplicationComponent.builder()
                        .applicationModule(ApplicationModule(this))
                        .build()
            }
            return mApplicationComponent as ApplicationComponent
        }
        set(applicationComponent) {
            mApplicationComponent = applicationComponent
        }

    companion object {

        operator fun get(context: Context): CommitStripApplication {
            return context.applicationContext as CommitStripApplication
        }
    }
}
