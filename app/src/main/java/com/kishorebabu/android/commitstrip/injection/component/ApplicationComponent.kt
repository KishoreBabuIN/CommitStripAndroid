package com.kishorebabu.android.commitstrip.injection.component

import android.app.Application
import android.content.Context
import com.kishorebabu.android.commitstrip.data.DataManager
import com.kishorebabu.android.commitstrip.data.remote.MvpStarterService
import com.kishorebabu.android.commitstrip.injection.ApplicationContext
import com.kishorebabu.android.commitstrip.injection.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun mvpBoilerplateService(): MvpStarterService
}
