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
 *  * Copyright(c) Developed by John Alves at 2020/2/23 at 0:58:54 for quantic heart studios
 *
 */
package com.quanticheart.gallery.view.list.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.quanticheart.gallery.R
import com.quanticheart.gallery.base.BaseRecyclerViewAdapter
import com.quanticheart.gallery.base.BaseRecyclerViewHolder
import com.quanticheart.gallery.imageExtentions.model.ImageData
import com.quanticheart.gallery.view.list.adapter.PictureAdapter.ImageViewHolder
import kotlinx.android.synthetic.main.item_image_simple.view.*

class PictureAdapter(recyclerView: RecyclerView) :
    BaseRecyclerViewAdapter<ImageData, ImageViewHolder>(recyclerView) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(parent.getView(R.layout.item_image_simple))

    inner class ImageViewHolder(itemView: View) :
        BaseRecyclerViewHolder<ImageData>(itemView) {
        override fun bind(item: ImageData, position: Int) {
            Glide.with(itemView.context)
                .load(item.path)
                .apply(RequestOptions().centerCrop())
                .into(itemView.image)
            ViewCompat.setTransitionName(itemView.image, adapterPosition.toString() + "_image")
            itemView.image.setOnClickListener {

            }
        }
    }
}