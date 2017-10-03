package com.kishorebabu.android.commitstrip

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.kishorebabu.android.commitstrip.common.TestComponentRule
import com.kishorebabu.android.commitstrip.features.main.MainActivity
import org.junit.Rule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val mComponent = TestComponentRule(InstrumentationRegistry.getTargetContext())
    private val mMain = ActivityTestRule(MainActivity::class.java, false, false)

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    @JvmField
    var chain: TestRule = RuleChain.outerRule(mComponent).around(mMain)

//    @Test
//    fun checkPokemonsDisplay() {
//        val namedResourceList = TestDataFactory.makeNamedResourceList(5)
//        val pokemonList = TestDataFactory.makePokemonNameList(namedResourceList)
//        stubDataManagerGetPokemonList(Single.just(pokemonList))
//        mMain.launchActivity(null)
//
//        for (pokemonName in namedResourceList) {
//            onView(withText(pokemonName.name))
//                    .check(matches(isDisplayed()))
//        }
//    }

//    @Test
//    fun clickingPokemonLaunchesDetailActivity() {
//        val namedResourceList = TestDataFactory.makeNamedResourceList(5)
//        val pokemonList = TestDataFactory.makePokemonNameList(namedResourceList)
//        stubDataManagerGetPokemonList(Single.just(pokemonList))
//        stubDataManagerGetPokemon(Single.just(TestDataFactory.makePokemon("id")))
//        mMain.launchActivity(null)
//
//        onView(withText(pokemonList[0]))
//                .perform(click())
//
//        onView(withId(R.id.image_pokemon))
//                .check(matches(isDisplayed()))
//    }

//    @Test
//    fun checkErrorViewDisplays() {
//        stubDataManagerGetPokemonList(Single.error<List<String>>(RuntimeException()))
//        mMain.launchActivity(null)
//        ErrorTestUtil.checkErrorViewsDisplay()
//    }

//    fun stubDataManagerGetPokemonList(single: Single<List<String>>) {
//        `when`(mComponent.mockDataManager.getPokemonList(ArgumentMatchers.anyInt()))
//                .thenReturn(single)
//    }
//
//    fun stubDataManagerGetPokemon(single: Single<Pokemon>) {
//        `when`(mComponent.mockDataManager.getPokemon(ArgumentMatchers.anyString()))
//                .thenReturn(single)
//    }

}