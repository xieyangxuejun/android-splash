package com.foretree.support.lib

import android.content.Context
import android.view.View

/**
 * Created by silen on 2018/8/22 22:51
 * Copyright (c) 2018 in FORETREE
 */
class SplashView(private val context: Context): Splash<String>{
    private var mView: View? = null

    override fun prepare(res: String) {
        mView = when(Extension.valueOf(FilenameUtils.getExtension(res))) {
            Extension.mp4,Extension.mov -> {
                SplashVideoView(res, context)
            }
            Extension.png,
            Extension.jpg,
            Extension.jpeg -> {
                SplashImageView(res, context)
            }
            Extension.gif -> {
                SplashGifView(res, context)
            }
        }
    }

    override fun getView(): View? {
        return mView
    }
}