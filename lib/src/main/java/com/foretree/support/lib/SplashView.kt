package com.foretree.support.lib

import android.content.Context
import android.view.View

/**
 * Created by silen on 2018/8/22 22:51
 * Copyright (c) 2018 in FORETREE
 */
class SplashView(private val context: Context): Splash{
    private var mView: View? = null

    override fun prepare(filePath: String) {
        mView = when(Extension.valueOf(FilenameUtils.getExtension(filePath))) {
            Extension.mp4,Extension.mov -> {
                SplashVideoView(filePath, context)
            }
            Extension.png,
            Extension.jpg,
            Extension.jpeg -> {
                SplashImageView(filePath, context)
            }
            Extension.gif -> {
                SplashGifView(filePath, context)
            }
        }
    }

    override fun getView(): View? {
        return mView
    }
}