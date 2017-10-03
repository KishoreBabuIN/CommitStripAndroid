package com.kishorebabu.android.commitstrip.features.main

import com.kishorebabu.android.commitstrip.data.DataManager
import com.kishorebabu.android.commitstrip.features.base.BasePresenter
import com.kishorebabu.android.commitstrip.injection.ConfigPersistent
import javax.inject.Inject

@ConfigPersistent
class MainPresenter @Inject
constructor(private val mDataManager: DataManager) : BasePresenter<MainMvpView>() {

    fun getPokemon(limit: Int) {
        checkViewAttached()
        mvpView?.showProgress(true)
//        mDataManager.getPokemonList(limit)
//                .compose(SchedulerUtils.ioToMain<List<String>>())
//                .subscribe({ pokemons ->
//                    mvpView?.showProgress(false)
//                    mvpView?.showPokemon(pokemons)
//                }) { throwable ->
//                    mvpView?.showProgress(false)
//                    mvpView?.showError(throwable)
//                }
    }

}