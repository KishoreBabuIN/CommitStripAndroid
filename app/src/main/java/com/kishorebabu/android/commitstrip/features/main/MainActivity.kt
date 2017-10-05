package com.kishorebabu.android.commitstrip.features.main

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.app.ListFragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kishorebabu.android.commitstrip.R
import com.kishorebabu.android.commitstrip.data.model.Comic
import com.kishorebabu.android.commitstrip.features.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_pager_image.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainMvpView, onComicActionListener {

    @Inject lateinit var mainPresenter: MainPresenter

    private lateinit var comicAdapter: ComicAdapter

    private lateinit var decorView: View
    private var izImmersive: Boolean = false
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mainPresenter.attachView(this)

        setSupportActionBar(toolbar)

        decorView = window.decorView
        hideUI()
        handler = Handler()
        decorView.setOnSystemUiVisibilityChangeListener({ visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                izImmersive = false
                fadeInView(app_bar, 400)
                handler.postDelayed(autoHideRunner, 3000)
            } else {
                izImmersive = true
            }
        })

        mainPresenter.onViewReady()

    }

    private fun showUI() {
        izImmersive = false
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
    }

    private fun hideUI() {
        izImmersive = true
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE
        fadeOutView(app_bar, 400)
    }

    override fun onComicClicked() {
        if (izImmersive) {
            showUI()
        } else {
            hideUI()
        }
    }

    private fun fadeInView(view: View, durationInMs: Long) {
        val visibility: ValueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f)
        visibility.apply {
            duration = durationInMs
            addUpdateListener { animator ->
                view.alpha = animator?.animatedValue as Float
            }
        }

        visibility.start()
    }

    private fun fadeOutView(view: View, durationInMs: Long) {
        val visibility: ValueAnimator = ValueAnimator.ofFloat(1.0f, 0.0f)
        visibility.apply {
            duration = durationInMs
            addUpdateListener { animator ->
                view.alpha = animator?.animatedValue as Float
            }
        }

        visibility.start()
    }

    private val autoHideRunner = Runnable { hideUI() }

    override fun showComics(count: Int) {
        comicAdapter = ComicAdapter(this, count, supportFragmentManager)

        view_pager.offscreenPageLimit = 2
        view_pager.adapter = comicAdapter
        view_pager.currentItem = 0
        mainPresenter.onComicPositionSelected(0)

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                mainPresenter.onComicPositionSelected(position)
            }
        })

    }

    override fun showComicTitleAndDate(comic: Comic) {

        val dateFormat = "EEE MMM dd, yyyy"
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.ENGLISH)

        toolbar.title = comic.title
        toolbar.subtitle = simpleDateFormat.format(Date(comic.date))
    }

    override val layout: Int
        get() = R.layout.activity_main

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }


    companion object

    class ComicAdapter(private val onComicActionListener: onComicActionListener,
                       private val count: Int,
                       fm: FragmentManager) : FragmentStatePagerAdapter(fm) {


        override fun getCount(): Int {
            return count
        }

        override fun getItem(position: Int): Fragment {
            return ComicFragment.newInstance(onComicActionListener, position)
        }
    }

    class ComicFragment : ListFragment(), ComicMvpView {
        private lateinit var onComicActionListener: onComicActionListener

        private fun setOnComicClickListener(onComicActionListener: onComicActionListener) {
            this.onComicActionListener = onComicActionListener
        }

        override fun showComic(comic: Comic) {
            Glide.with(this)
                    .load(comic.imageUrl)
                    .into(comic_photoview)
        }

        private fun formatDate(timestamp: Long): String {
            val dateFormat = "EEE MMM dd, yyyy"
            val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.ENGLISH)

            return simpleDateFormat.format(Date(timestamp))
        }

        private var position: Int = 0

        @Inject lateinit var comicPresenter: ComicPresenter

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            (activity as BaseActivity).activityComponent().inject(this)

            position = if (arguments != null) arguments.getInt("position") else 1


        }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val v = inflater!!.inflate(R.layout.item_pager_image, container, false)
            comicPresenter.attachView(this)
            return v
        }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            comicPresenter.onViewReadyReady(position)
            comic_photoview.setOnClickListener {
                onComicActionListener.onComicClicked()
            }
        }

        companion object {


            /**
             * Create a new instance of CountingFragment, providing "position"
             * as an argument.
             */
            internal fun newInstance(onComicActionListener: onComicActionListener, position: Int): ComicFragment {
                val f = ComicFragment()
                f.setOnComicClickListener(onComicActionListener)

                // Supply position input as an argument.
                val args = Bundle()
                args.putInt("position", position)
                f.arguments = args

                return f
            }
        }


    }
}