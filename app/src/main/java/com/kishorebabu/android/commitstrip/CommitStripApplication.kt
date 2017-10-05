package com.kishorebabu.android.commitstrip

import android.content.Context
import android.support.multidex.MultiDexApplication
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.kishorebabu.android.commitstrip.injection.component.ApplicationComponent
import com.kishorebabu.android.commitstrip.injection.component.DaggerApplicationComponent
import com.kishorebabu.android.commitstrip.injection.module.ApplicationModule
import com.kishorebabu.android.commitstrip.util.CrashReportingTree
import com.squareup.leakcanary.LeakCanary
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import io.fabric.sdk.android.Fabric
import timber.log.Timber


class CommitStripApplication : MultiDexApplication() {

    internal var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        val config = TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(TwitterAuthConfig(BuildConfig.TWITTER_CONSUMER_KEY, BuildConfig.TWITTER_CONSUMER_SECRET))
                .debug(true)
                .build()
        Twitter.initialize(config)


        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
            LeakCanary.install(this)
        } else {
            Fabric.with(this, Crashlytics())
            Timber.plant(CrashReportingTree())
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
