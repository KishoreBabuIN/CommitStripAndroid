package com.kishorebabu.android.commitstrip

import com.kishorebabu.android.commitstrip.data.DataManager
import com.kishorebabu.android.commitstrip.data.model.ComicDao
import com.kishorebabu.android.commitstrip.data.remote.MvpStarterService
import com.kishorebabu.android.commitstrip.util.RxSchedulersOverrideRule
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataManagerTest {

    @Rule
    @JvmField
    val mOverrideSchedulersRule = RxSchedulersOverrideRule()
    @Mock lateinit var mMockMvpStarterService: MvpStarterService
    @Mock lateinit var mComicDao: ComicDao

    private var mDataManager: DataManager? = null

    @Before
    fun setUp() {
        mDataManager = DataManager(mMockMvpStarterService, mComicDao)
    }
//
//    @Test
//    fun getPokemonListCompletesAndEmitsPokemonList() {
//        val namedResourceList = TestDataFactory.makeNamedResourceList(5)
//        val pokemonListResponse = PokemonListResponse(namedResourceList)
//
//        `when`(mMockMvpStarterService.getPokemonList(anyInt()))
//                .thenReturn(Single.just(pokemonListResponse))
//
//        mDataManager?.getPokemonList(10)
//                ?.test()
//                ?.assertComplete()
//                ?.assertValue(TestDataFactory.makePokemonNameList(namedResourceList))
//    }
//
//    @Test
//    fun getPokemonCompletesAndEmitsPokemon() {
//        val name = "charmander"
//        val pokemon = TestDataFactory.makePokemon(name)
//        `when`(mMockMvpStarterService.getPokemon(anyString()))
//                .thenReturn(Single.just(pokemon))
//
//        mDataManager?.getPokemon(name)
//                ?.test()
//                ?.assertComplete()
//                ?.assertValue(pokemon)
//    }
}
