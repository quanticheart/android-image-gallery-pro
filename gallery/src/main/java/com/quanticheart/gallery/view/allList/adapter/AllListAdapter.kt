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
 *  * Copyright(c) Developed by John Alves at 2020/2/24 at 0:18:34 for quantic heart studios
 *
 */

package com.quanticheart.gallery.view.allList.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quanticheart.gallery.R
import com.quanticheart.gallery.base.BaseRecyclerViewAdapter
import com.quanticheart.gallery.base.BaseRecyclerViewHolder
import com.quanticheart.gallery.imageExtentions.model.ImageFullData
import kotlinx.android.synthetic.main.item_all_list.view.*

class AllListAdapter(recyclerView: RecyclerView) :
    BaseRecyclerViewAdapter<ImageFullData, AllListAdapter.AllImagesHolder>(recyclerView) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllImagesHolder =
        AllImagesHolder(parent.getView(R.layout.item_all_list))

    class AllImagesHolder(itemView: View) : BaseRecyclerViewHolder<ImageFullData>(itemView) {
        override fun bind(item: ImageFullData, position: Int) {
            Glide.with(itemView.context).load(item.path).into(itemView.img)
        }
    }
}