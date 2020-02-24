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
 *  * Copyright(c) Developed by John Alves at 2020/2/23 at 9:8:46 for quantic heart studios
 *
 */
package com.quanticheart.gallery.view.indicator.adapter

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.quanticheart.gallery.R
import com.quanticheart.gallery.base.BaseRecyclerViewAdapter
import com.quanticheart.gallery.base.BaseRecyclerViewHolder
import com.quanticheart.gallery.imageExtentions.model.ImageData
import kotlinx.android.synthetic.main.item_image_indicator_recycler.view.*

class RecyclerPagerAdapter(
    private val recyclerView: RecyclerView,
    private val callback: (Int) -> Unit
) : BaseRecyclerViewAdapter<ImageData, RecyclerPagerAdapter.IndicatorHolder>(recyclerView) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorHolder =
        IndicatorHolder(parent.getView(R.layout.item_image_indicator_recycler))

    inner class IndicatorHolder(itemView: View) :
        BaseRecyclerViewHolder<ImageData>(itemView) {

        override fun bind(item: ImageData, position: Int) {
            itemView.activeImage?.setBackgroundColor(
                if (item.selected) Color.parseColor(
                    "#00000000"
                ) else Color.parseColor("#8c000000")
            )
            Glide.with(itemView.context)
                .load(item.path)
                .apply(RequestOptions().centerCrop())
                .into(itemView.imageIndicator)
            itemView.imageIndicator.setOnClickListener {
                item.selected = true
                notifyDataSetChanged()
                callback(position)
            }
        }
    }

    private var lstPosition = 0
    fun toPosition(position: Int) {
        database[lstPosition].selected = false
        lstPosition = position
        database[position].selected = true
        recyclerView.scrollToPosition(position)
        notifyDataSetChanged()
    }
}