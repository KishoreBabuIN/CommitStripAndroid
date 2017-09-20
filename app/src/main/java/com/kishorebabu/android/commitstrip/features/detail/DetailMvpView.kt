package com.kishorebabu.android.commitstrip.features.detail

import com.kishorebabu.android.commitstrip.data.model.Pokemon
import com.kishorebabu.android.commitstrip.data.model.Statistic
import com.kishorebabu.android.commitstrip.features.base.MvpView

interface DetailMvpView : MvpView {

    fun showPokemon(pokemon: Pokemon)

    fun showStat(statistic: Statistic)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}