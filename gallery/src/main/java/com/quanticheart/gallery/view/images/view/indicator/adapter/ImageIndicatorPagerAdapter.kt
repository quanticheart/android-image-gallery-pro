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
package com.quanticheart.gallery.view.images.view.indicator.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.quanticheart.gallery.R
import com.quanticheart.gallery.view.images.model.ImageData
import com.quanticheart.gallery.view.images.view.indicator.adapter.ImageIndicatorPagerAdapter.IndicatorHolder
import kotlinx.android.synthetic.main.indicator_holder.view.*

class ImageIndicatorPagerAdapter(
    private val recyclerView: RecyclerView,
    private val callback: (Int) -> Unit
) :
    RecyclerView.Adapter<IndicatorHolder>() {

    private val database = ArrayList<ImageData>()

    init {
        recyclerView.apply {
            adapter = this@ImageIndicatorPagerAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorHolder =
        IndicatorHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.indicator_holder,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: IndicatorHolder, position: Int) {
        holder.bind(database[position], position)
    }

    override fun getItemCount(): Int = database.size

    inner class IndicatorHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(imageData: ImageData, position: Int) {
            itemView.activeImage?.setBackgroundColor(
                if (imageData.selected) Color.parseColor(
                    "#00000000"
                ) else Color.parseColor("#8c000000")
            )
            Glide.with(itemView.context)
                .load(imageData.path)
                .apply(RequestOptions().centerCrop())
                .into(itemView.imageIndicator)
            itemView.imageIndicator.setOnClickListener {
                imageData.selected = true
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

    fun addData(list: ArrayList<ImageData>) {
        if (list.isNotEmpty()) {
            database.clear()
            database.addAll(list)
            notifyDataSetChanged()
        }
    }
}