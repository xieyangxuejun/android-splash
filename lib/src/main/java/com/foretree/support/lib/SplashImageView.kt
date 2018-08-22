package com.foretree.support.lib

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.text.TextUtils
import android.widget.ImageView


/**
 * Created by silen on 2018/8/22 23:32
 * Copyright (c) 2018 in FORETREE
 */

@SuppressLint("ViewConstructor")
class SplashImageView(filePath: String, context: Context) : ImageView(context), Control {
    override fun getDuration(): Int = defaultDuration

    init {
        try {
            if (!TextUtils.isEmpty(filePath)) {
                val bitmap = BitmapFactory.decodeFile(filePath)
                setImageBitmap(bitmap)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onStart() {

    }

    override fun onPause() {
        //回收imageView
        if (null != drawable && drawable is BitmapDrawable) {
            val bitmap = (drawable as BitmapDrawable).bitmap
            if (bitmap.isRecycled)
                bitmap.recycle()
        }
    }

}