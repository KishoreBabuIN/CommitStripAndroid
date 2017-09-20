package com.kishorebabu.android.commitstrip.common.injection.component

import com.kishorebabu.android.commitstrip.common.injection.module.ApplicationTestModule
import com.kishorebabu.android.commitstrip.injection.component.ApplicationComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationTestModule::class))
interface TestComponent : ApplicationComponent