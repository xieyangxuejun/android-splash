package com.foretree.support.lib

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.view.ViewGroup
import android.widget.VideoView
import java.io.File

/**
 * Created by silen on 2018/8/22 23:03
 * Copyright (c) 2018 in FORETREE
 */

@SuppressLint("ViewConstructor")
class SplashVideoView(filePath: String, context: Context) : VideoView(context), Control {

    private lateinit var mUri: Uri
    init {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        try {
            if (!TextUtils.isEmpty(filePath))
            mUri = Uri.fromFile(File(filePath))
            setVideoURI(mUri)
            setBackgroundColor(Color.WHITE)
            setOnPreparedListener {
                it.run {
                    setVolume(0f,0f)
                    isLooping = true
                }
                setBackgroundColor(Color.TRANSPARENT)

            }
            setOnCompletionListener {
                Log.d("splash", "video complete duration=>${it.duration}")
            }
            setOnErrorListener { _, _, _ -> true }
            start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStart() {
        if (!isPlaying) start()
    }

    override fun onPause() {
        if (isPlaying && canPause()) pause()
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
    }

    override fun getDuration(): Int {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(context, mUri)
        try {
            val d = retriever.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION)//时长(毫秒)
            return Integer.parseInt(d)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            retriever.release()
        }
        return defaultDuration
    }
}