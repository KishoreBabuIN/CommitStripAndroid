package com.kishorebabu.android.commitstrip.features.main

import com.kishorebabu.android.commitstrip.features.base.MvpView

interface MainMvpView : MvpView {

    fun showPokemon(pokemon: List<String>)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}