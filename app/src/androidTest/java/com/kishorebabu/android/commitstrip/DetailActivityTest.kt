package com.kishorebabu.android.commitstrip

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.kishorebabu.android.commitstrip.common.TestComponentRule
import com.kishorebabu.android.commitstrip.features.detail.DetailActivity
import org.junit.Rule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    val component = TestComponentRule(InstrumentationRegistry.getTargetContext())
    val main = ActivityTestRule(DetailActivity::class.java, false, false)

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    @JvmField
    var chain: TestRule = RuleChain.outerRule(component).around(main)

//    @Test
//    fun checkPokemonDisplays() {
//        val pokemon = TestDataFactory.makePokemon("id")
//        stubDataManagerGetPokemon(Single.just(pokemon))
//        main.launchActivity(
//                DetailActivity.getStartIntent(InstrumentationRegistry.getContext(), pokemon.name))
//
//        for (stat in pokemon.stats) {
//            onView(withText(stat.stat?.name))
//                    .check(matches(isDisplayed()))
//        }
//    }
//
//    @Test
//    fun checkErrorViewDisplays() {
//        stubDataManagerGetPokemon(Single.error<Pokemon>(RuntimeException()))
//        val pokemon = TestDataFactory.makePokemon("id")
//        main.launchActivity(
//                DetailActivity.getStartIntent(InstrumentationRegistry.getContext(), pokemon.name))
//        ErrorTestUtil.checkErrorViewsDisplay()
//    }

//    fun stubDataManagerGetPokemon(single: Single<Pokemon>) {
//        `when`(component.mockDataManager.getPokemon(ArgumentMatchers.anyString()))
//                .thenReturn(single)
//    }

}