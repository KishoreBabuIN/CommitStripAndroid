package com.kishorebabu.android.commitstrip.injection.module

import android.app.Application
import android.content.Context
import com.kishorebabu.android.commitstrip.data.remote.MvpStarterService
import com.kishorebabu.android.commitstrip.data.remote.MvpStarterServiceFactory
import com.kishorebabu.android.commitstrip.injection.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    @Provides
    @Singleton
    internal fun provideMvpStarterService(): MvpStarterService {
        return MvpStarterServiceFactory.makeStarterService()
    }
}
