package com.kishorebabu.android.commitstrip.features.splash

import android.content.Intent
import android.os.Bundle
import com.kishorebabu.android.commitstrip.R
import com.kishorebabu.android.commitstrip.features.base.BaseActivity
import com.kishorebabu.android.commitstrip.features.main.MainActivity
import javax.inject.Inject

/**
 * Created by kishore on 20/09/17.
 */
class SplashActivity : BaseActivity(), SplashMvpView {
    @Inject lateinit var splashPresenter: SplashPresenter

    override val layout: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        splashPresenter.attachView(this)

        splashPresenter.onViewReady()

    }

    override fun showComicList() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        splashPresenter.detachView()
    }
}

