package com.foretree.support.lib

/**
 * Created by silen on 2018/8/22 22:47
 * Copyright (c) 2018 in FORETREE
 */
interface Control {
    fun onStart()
    fun onPause()
    fun getDuration(): Int
}