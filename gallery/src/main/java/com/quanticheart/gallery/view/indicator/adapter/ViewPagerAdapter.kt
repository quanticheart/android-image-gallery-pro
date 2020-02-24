/*
 *
 *  *                                     /@
 *  *                      __        __   /\/
 *  *                     /==\      /  \_/\/
 *  *                   /======\    \/\__ \__
 *  *                 /==/\  /\==\    /\_|__ \
 *  *              /==/    ||    \=\ / / / /_/
 *  *            /=/    /\ || /\   \=\/ /
 *  *         /===/   /   \||/   \   \===\
 *  *       /===/   /_________________ \===\
 *  *    /====/   / |                /  \====\
 *  *  /====/   /   |  _________    /      \===\
 *  *  /==/   /     | /   /  \ / / /         /===/
 *  * |===| /       |/   /____/ / /         /===/
 *  *  \==\             /\   / / /          /===/
 *  *  \===\__    \    /  \ / / /   /      /===/   ____                    __  _         __  __                __
 *  *    \==\ \    \\ /____/   /_\ //     /===/   / __ \__  ______  ____ _/ /_(_)____   / / / /__  ____ ______/ /_
 *  *    \===\ \   \\\\\\\/   ///////     /===/  / / / / / / / __ \/ __ `/ __/ / ___/  / /_/ / _ \/ __ `/ ___/ __/
 *  *      \==\/     \\\\/ / //////       /==/  / /_/ / /_/ / / / / /_/ / /_/ / /__   / __  /  __/ /_/ / /  / /_
 *  *      \==\     _ \\/ / /////        |==/   \___\_\__,_/_/ /_/\__,_/\__/_/\___/  /_/ /_/\___/\__,_/_/   \__/
 *  *        \==\  / \ / / ///          /===/
 *  *        \==\ /   / / /________/    /==/
 *  *          \==\  /               | /==/
 *  *          \=\  /________________|/=/
 *  *            \==\     _____     /==/
 *  *           / \===\   \   /   /===/
 *  *          / / /\===\  \_/  /===/
 *  *         / / /   \====\ /====/
 *  *        / / /      \===|===/
 *  *        |/_/         \===/
 *  *                       =
 *  *
 *  * Copyright(c) Developed by John Alves at 2020/2/23 at 1:56:43 for quantic heart studios
 *
 */

package com.quanticheart.gallery.view.indicator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.quanticheart.gallery.R
import com.quanticheart.gallery.imageExtentions.model.ImageData
import kotlinx.android.synthetic.main.item_image_indicator_viewpager.view.*

class ViewPagerAdapter(private val viewPager: ViewPager, callback: (Int) -> Unit) :
    PagerAdapter() {

    private val database = ArrayList<ImageData>()

    init {
        viewPager.apply {
            adapter = this@ViewPagerAdapter
            offscreenPageLimit = 3
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int, positionOffset: Float, positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    callback(position)
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })
            setOnTouchListener { _, _ ->
                false
            }
        }
    }

    override fun instantiateItem(containerCollection: ViewGroup, position: Int): Any {

        val view: View = LayoutInflater.from(containerCollection.context)
            .inflate(R.layout.item_image_indicator_viewpager, containerCollection, false)

        ViewCompat.setTransitionName(view.image, position.toString() + "picture")

        val pic = database[position]

        Glide.with(containerCollection.context)
            .load(pic.path)
            .apply(RequestOptions().fitCenter())
            .into(view.image)
        view.image.setOnClickListener {

        }

        (containerCollection as ViewPager).addView(view)

        return view
    }

    fun toPosition(position: Int) {
        viewPager.currentItem = position
        notifyDataSetChanged()
    }

    fun addData(list: ArrayList<ImageData>) {
        if (list.isNotEmpty()) {
            database.clear()
            database.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun getCount(): Int = database.size

    override fun destroyItem(
        containerCollection: ViewGroup, position: Int, view: Any
    ) {
        (containerCollection as ViewPager).removeView(view as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === (`object` as View)
}