package com.kishorebabu.android.commitstrip.injection.component

import com.kishorebabu.android.commitstrip.injection.PerFragment
import com.kishorebabu.android.commitstrip.injection.module.FragmentModule
import dagger.Subcomponent

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = arrayOf(FragmentModule::class))
interface FragmentComponent