package com.foretree.support.lib

import android.content.Context

/**
 * Created by silen on 2018/8/22 8:30
 * Copyright (c) 2018 in FORETREE
 */
interface SplashFactory {
    fun create(context: Context): Splash
    fun getDuration(): Int
}

class SimpleSplashFactory : SplashFactory {

    override fun getDuration(): Int = defaultDuration

    override fun create(context: Context): Splash = SplashView(context)
}