package com.foretree.support.lib

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * Created by silen on 2018/8/22 8:30
 * Copyright (c) 2018 in FORETREE
 */
interface SplashFactory {
    fun create(context: Context): Splash<*>
    fun getDuration(): Int
}

class SimpleSplashFactory : SplashFactory {
    override fun getDuration(): Int = defaultDuration
    override fun create(context: Context): Splash<String> = SplashView(context)
}

class ResSplashFactory : SplashFactory {
    override fun create(context: Context): Splash<Int> = object : Splash<Int> {
        private var mView: View? = null

        override fun prepare(res: Int) {
            mView = ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                setBackgroundResource(res)
            }
        }

        override fun getView(): View? = mView

    }

    override fun getDuration(): Int = defaultDuration

}