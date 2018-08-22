package com.foretree.support.lib

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.ViewGroup
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView
import java.io.IOException



/**
 * Created by silen on 2018/8/22 23:45
 * Copyright (c) 2018 in FORETREE
 */
@SuppressLint("ViewConstructor")
class SplashGifView(filePath: String, context: Context): GifImageView(context), Control {
    private lateinit var gifDrawable: GifDrawable

    init {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        scaleType = ScaleType.CENTER_CROP
        try {
            if (!TextUtils.isEmpty(filePath)) {
                gifDrawable = GifDrawable(filePath)
            }
            setImageDrawable(gifDrawable)
            gifDrawable.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    override fun getDuration(): Int {
        return gifDrawable.duration
    }


    override fun onStart() {
        if (!gifDrawable.isPlaying) gifDrawable.start()

    }

    override fun onPause() {
        if (gifDrawable.isPlaying) gifDrawable.stop()
        if (null != drawable && drawable is GifDrawable) {
            val d = drawable as GifDrawable
            if (d.isRecycled) {
                d.recycle()
            }
        }
    }

}