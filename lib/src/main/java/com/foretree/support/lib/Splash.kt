package com.foretree.support.lib

import android.view.View

/**
 * Created by silen on 2018/8/22 8:11
 * Copyright (c) 2018 in FORETREE
 */
interface Splash {
    fun prepare(filePath: String)
    fun getView(): View?
}