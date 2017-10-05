package com.kishorebabu.android.commitstrip.util

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber


/**
 * Created by kishore on 05/10/17.
 */
class CrashReportingTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            return
        }

        if (t == null)
            Crashlytics.log(priority, tag, message)
        else
            Crashlytics.logException(t)

    }
}