package com.kishorebabu.android

import com.facebook.stetho.Stetho
import com.kishorebabu.android.commitstrip.CommitStripApplication
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

/**
 * Created by kishore on 05/10/17.
 */
class CommitStripDebugApplication : CommitStripApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(this)
        LeakCanary.install(this)
    }
}