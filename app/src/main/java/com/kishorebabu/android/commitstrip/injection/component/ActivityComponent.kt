package com.kishorebabu.android.commitstrip.injection.component

import com.kishorebabu.android.commitstrip.features.base.BaseActivity
import com.kishorebabu.android.commitstrip.features.main.MainActivity
import com.kishorebabu.android.commitstrip.features.splash.SplashActivity
import com.kishorebabu.android.commitstrip.injection.PerActivity
import com.kishorebabu.android.commitstrip.injection.module.ActivityModule
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(splashActivity: SplashActivity)
}
