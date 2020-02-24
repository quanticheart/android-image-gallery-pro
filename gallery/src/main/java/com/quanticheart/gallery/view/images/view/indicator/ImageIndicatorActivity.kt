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
 *  * Copyright(c) Developed by John Alves at 2020/2/23 at 1:33:27 for quantic heart studios
 *
 */
package com.quanticheart.gallery.view.images.view.indicator

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quanticheart.gallery.R
import com.quanticheart.gallery.extentions.getSerializableExtra
import com.quanticheart.gallery.view.folder.constants.FolderConstants
import com.quanticheart.gallery.view.folder.model.ImageFolderData
import com.quanticheart.gallery.view.images.extentions.getAllImagesByFolder
import com.quanticheart.gallery.view.images.view.indicator.adapter.ImageIndicatorPagerAdapter
import com.quanticheart.gallery.view.images.view.indicator.adapter.ImagesPagerAdapter
import kotlinx.android.synthetic.main.fragment_picture_browser.*
import permissions.dispatcher.*

@RuntimePermissions
class ImageIndicatorActivity : AppCompatActivity() {

    private lateinit var viewPagerAdapter: ImagesPagerAdapter
    private lateinit var recyclerAdapter: ImageIndicatorPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_picture_browser)
        showImagesWithPermissionCheck()
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    fun showImages() {
        getSerializableExtra<ImageFolderData>(FolderConstants.FolderDataKey)?.let { data ->
            val list = getAllImagesByFolder(data.path)
            list.first().selected = true

            viewPagerAdapter = ImagesPagerAdapter(imagePager) { position ->
                recyclerAdapter.toPosition(position)
            }
            viewPagerAdapter.addData(list)

            recyclerAdapter = ImageIndicatorPagerAdapter(indicatorRecycler) { positionSelected ->
                viewPagerAdapter.toPosition(positionSelected)
            }
            recyclerAdapter.addData(list)

        } ?: finish()
    }

    @OnShowRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
    fun showRationaleForCamera() {
        finish()
    }

    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
    fun onCameraDenied() {
        finish()
    }

    @OnNeverAskAgain(Manifest.permission.READ_EXTERNAL_STORAGE)
    fun onCameraNeverAskAgain() {
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }
}