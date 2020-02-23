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
 *  * Copyright(c) Developed by John Alves at 2020/2/23 at 0:13:52 for quantic heart studios
 *
 */

package com.quanticheart.gallery.custonUI.folder.extentions

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.quanticheart.gallery.custonUI.folder.model.ImageFolderData
import java.util.*

fun Context.getAllImageFolders(): ArrayList<ImageFolderData> {

    val picFolderData: ArrayList<ImageFolderData> = ArrayList()
    val picPaths = ArrayList<String>()

    val allImagesuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

    val projection = arrayOf(
        MediaStore.Images.ImageColumns.DATA,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
        MediaStore.Images.Media.BUCKET_ID
    )

    val cursor = contentResolver.query(allImagesuri, projection, null, null, null)
    cursor?.let {
        try {
            while (cursor.moveToNext()) {

                val name =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                val folder =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
                val datapath =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                var folderpaths = datapath.substring(0, datapath.lastIndexOf("$folder/"))

                folderpaths = "$folderpaths$folder/"
                Log.w("TESTEs", folderpaths)

                if (!picPaths.contains(folderpaths)) {

                    picPaths.add(folderpaths)

                    val folderData = ImageFolderData(
                        name,
                        folderpaths,
                        folder,
                        datapath
                    )

                    folderData.addpics()

                    picFolderData.add(folderData)

                } else {
                    for (i in picFolderData.indices) {
                        if (picFolderData[i].path == folderpaths) {
                            picFolderData[i].firstPic = datapath
                            picFolderData[i].addpics()
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    cursor?.close()
    for (i in picFolderData.indices) {
        Log.w(
            "picture folders",
            picFolderData[i].folderName + " and path = " + picFolderData[i].path + " " + picFolderData[i].numberOfPics
        )
    }
    return picFolderData
}