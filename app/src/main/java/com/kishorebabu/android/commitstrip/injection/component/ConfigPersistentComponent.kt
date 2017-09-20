package com.kishorebabu.android.commitstrip.injection.component

import com.kishorebabu.android.commitstrip.features.base.BaseActivity
import com.kishorebabu.android.commitstrip.features.base.BaseFragment
import com.kishorebabu.android.commitstrip.injection.ConfigPersistent
import com.kishorebabu.android.commitstrip.injection.module.ActivityModule
import com.kishorebabu.android.commitstrip.injection.module.FragmentModule
import dagger.Component

/**
 * A dagger component that will live during the lifecycle of an Activity or Fragment but it won't
 * be destroy during configuration changes. Check [BaseActivity] and [BaseFragment] to
 * see how this components survives configuration changes.
 * Use the [ConfigPersistent] scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface ConfigPersistentComponent {

    fun activityComponent(activityModule: ActivityModule): ActivityComponent

    fun fragmentComponent(fragmentModule: FragmentModule): FragmentComponent

}
