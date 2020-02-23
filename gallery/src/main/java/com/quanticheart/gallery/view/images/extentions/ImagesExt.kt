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
 *  * Copyright(c) Developed by John Alves at 2020/2/23 at 0:41:45 for quantic heart studios
 *
 */

package com.quanticheart.gallery.view.images.extentions

import android.app.Activity
import android.net.Uri
import android.provider.MediaStore
import com.quanticheart.gallery.extentions.getStringOrEmpty
import com.quanticheart.gallery.view.images.model.ImageData

fun Activity.getAllImagesByFolder(path: String): ArrayList<ImageData> {

    val images: ArrayList<ImageData> = ArrayList()

    val allImagesUri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

    val projection = getDatabaseKeys()

    val cursor = contentResolver.query(
        allImagesUri,
        projection,
        MediaStore.Images.Media.DATA + " like ? ",
        arrayOf("%$path%"),
        null
    )

    cursor?.let {
        try {
            while (cursor.moveToNext()) {
                images.add(
                    ImageData(
                        cursor.getStringOrEmpty(projection[1]),
                        cursor.getStringOrEmpty(projection[0]),
                        cursor.getStringOrEmpty(projection[2])
                    )
                )
            }
            images.reverse()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    cursor?.close()
    return images
}

private fun getDatabaseKeys(): Array<String> {
    return arrayOf(
        MediaStore.Images.ImageColumns.DATA,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.SIZE
    )
}