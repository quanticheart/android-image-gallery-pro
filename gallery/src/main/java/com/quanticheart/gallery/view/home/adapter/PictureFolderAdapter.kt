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
 *  * Copyright(c) Developed by John Alves at 2020/2/23 at 0:55:2 for quantic heart studios
 *
 */
package com.quanticheart.gallery.view.home.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.quanticheart.gallery.R
import com.quanticheart.gallery.base.BaseRecyclerViewAdapter
import com.quanticheart.gallery.base.BaseRecyclerViewHolder
import com.quanticheart.gallery.extentions.createSelectDialog
import com.quanticheart.gallery.imageExtentions.model.FolderData
import com.quanticheart.gallery.view.home.adapter.PictureFolderAdapter.FolderHolder
import com.quanticheart.gallery.view.home.constants.FolderConstants
import com.quanticheart.gallery.view.indicator.ImageIndicatorActivity
import com.quanticheart.gallery.view.list.ImageListActivity
import kotlinx.android.synthetic.main.item_folder.view.*

class PictureFolderAdapter(recyclerView: RecyclerView) :
    BaseRecyclerViewAdapter<FolderData, FolderHolder>(recyclerView) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderHolder =
        FolderHolder(parent.getView(R.layout.item_folder))

    inner class FolderHolder(itemView: View) : BaseRecyclerViewHolder<FolderData>(itemView) {
        @SuppressLint("SetTextI18n")
        override fun bind(item: FolderData, position: Int) {
            Glide.with(itemView.context)
                .load(item.firstPic)
                .apply(RequestOptions().centerCrop())
                .into(itemView.folderPic)

            itemView.folderName.text = "(${item.numberOfPics}) ${item.folderName}"
            itemView.folderPic.setOnClickListener {
                itemView.context.createSelectDialog(
                    "Select type view",
                    "Two views avaliable",
                    "simple",
                    {
                        val move = Intent(itemView.context, ImageListActivity::class.java)
                        move.putExtra(FolderConstants.FolderDataKey, item)
                        itemView.context.startActivity(move)
                    },
                    "indicator",
                    {
                        val move = Intent(itemView.context, ImageIndicatorActivity::class.java)
                        move.putExtra(FolderConstants.FolderDataKey, item)
                        itemView.context.startActivity(move)
                    }
                )
            }
        }
    }
}