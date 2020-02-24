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
package com.quanticheart.gallery.view.images.view.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.quanticheart.gallery.R
import com.quanticheart.gallery.view.images.view.list.adapter.PictureAdapter.PicHolder
import com.quanticheart.gallery.view.images.model.ImageData
import kotlinx.android.synthetic.main.item_picture.view.*

class PictureAdapter(recyclerView: RecyclerView) : RecyclerView.Adapter<PicHolder>() {

    private val database = ArrayList<ImageData>()

    init {
        recyclerView.apply {
            adapter = this@PictureAdapter
        }
    }

    override fun onCreateViewHolder(container: ViewGroup, position: Int): PicHolder = PicHolder(
        LayoutInflater.from(container.context).inflate(
            R.layout.item_picture,
            container,
            false
        )
    )

    override fun onBindViewHolder(holder: PicHolder, position: Int) {
        holder.bind(database[position])
    }

    override fun getItemCount(): Int = database.size

    inner class PicHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(image: ImageData) {
            Glide.with(itemView.context)
                .load(image.path)
                .apply(RequestOptions().centerCrop())
                .into(itemView.image)
            ViewCompat.setTransitionName(itemView.image, adapterPosition.toString() + "_image")
            itemView.image.setOnClickListener {

            }
        }
    }

    fun addData(list: ArrayList<ImageData>) {
        if (list.isNotEmpty()) {
            database.clear()
            database.addAll(list)
            notifyDataSetChanged()
        }
    }
}