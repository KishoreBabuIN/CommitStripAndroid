package com.kishorebabu.android.commitstrip.runner

import android.app.Application
import android.content.Context
import com.kishorebabu.android.commitstrip.CommitStripApplication
import io.appflate.restmock.android.RESTMockTestRunner

/**
 * Created by ravindra on 4/2/17.
 */
class TestRunner : RESTMockTestRunner() {

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, CommitStripApplication::class.java.name, context)
    }

}
