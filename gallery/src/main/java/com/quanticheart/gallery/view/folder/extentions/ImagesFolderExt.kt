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

package com.quanticheart.gallery.view.folder.extentions

import android.content.Context
import android.provider.MediaStore
import com.quanticheart.gallery.view.folder.model.ImageFolderData
import com.quanticheart.gallery.extentions.getStringOrEmpty

fun Context.getAllImagesFolders(): ArrayList<ImageFolderData> {

    val picFolderData: ArrayList<ImageFolderData> = ArrayList()
    val picPaths = ArrayList<String>()

    val allImagesUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

    val projection = getDatabaseKeys()

    val cursor = contentResolver.query(allImagesUri, projection, null, null, null)
    cursor?.let {
        try {
            while (cursor.moveToNext()) {

                val dataPath = cursor.getStringOrEmpty(projection[0])
                val name = cursor.getStringOrEmpty(projection[1])
                val folder = cursor.getStringOrEmpty(projection[2])

                var folderpaths = dataPath.substring(0, dataPath.lastIndexOf("$folder/"))

                folderpaths = "$folderpaths$folder/"

                if (!picPaths.contains(folderpaths)) {

                    picPaths.add(folderpaths)

                    val folderData = ImageFolderData(
                        name,
                        folderpaths,
                        folder,
                        dataPath
                    )

                    folderData.addpics()

                    picFolderData.add(folderData)

                } else {
                    for (i in picFolderData.indices) {
                        if (picFolderData[i].path == folderpaths) {
                            picFolderData[i].firstPic = dataPath
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
//    for (i in picFolderData.indices) {
//        Log.w(
//            "picture folders",
//            picFolderData[i].folderName + " and path = " + picFolderData[i].path + " " + picFolderData[i].numberOfPics
//        )
//    }
    return picFolderData
}

private fun getDatabaseKeys(): Array<String> {
    return arrayOf(
        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
        MediaStore.Images.Media.BUCKET_ID
    )
}