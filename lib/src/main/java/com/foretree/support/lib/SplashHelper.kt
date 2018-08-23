package com.foretree.support.lib

import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes

/**
 * Created by silen on 2018/8/22 8:20
 * Copyright (c) 2018 in FORETREE
 */
class SplashHelper private constructor(
        private val mParent: ViewGroup,
        private val mFactory: SplashFactory
) {
    private var mView: View? = null
    private var mDuration: Int = 0
    private var mListener: View.OnClickListener? = null

    companion object {
        private val splashMap = hashMapOf<View, SplashHelper>()

        fun bind(parent: ViewGroup, factory: SplashFactory): SplashHelper {
            return SplashHelper(parent, factory).apply {
                splashMap[parent] = this
            }
        }

        fun bind(parent: ViewGroup): SplashHelper {
            return SplashHelper(parent, SimpleSplashFactory()).apply {
                splashMap[parent] = this
            }
        }

        fun cancel(parent: ViewGroup) {
            if (splashMap.containsKey(parent)) {
                splashMap[parent]?.cancel()
            }
            splashMap.remove(parent)
        }

    }

    fun where(filePath: String) : SplashHelper{
        mView = (mFactory as SimpleSplashFactory).create(mParent.context).apply {
            prepare(filePath)
        }.getView()?.apply {
            setOnClickListener(mListener)
            mDuration = if (mFactory.getDuration() <= 0 && (this is Control)) {
                (this as Control).getDuration()
            } else {
                defaultDuration
            }
        }
        return this
    }

    fun res(@DrawableRes drawableRes: Int): SplashHelper {
        mView = (mFactory as ResSplashFactory).create(mParent.context).apply {
            prepare(drawableRes)
        }.getView()?.apply {
            setOnClickListener(mListener)
            mDuration = if (mFactory.getDuration() <= 0 && (this is Control)) {
                (this as Control).getDuration()
            } else {
                defaultDuration
            }
        }
        return this
    }

    fun show() {
        mView?.run {
            visibility = View.VISIBLE
            if (parent != null) mParent.removeView(this)
            mParent.addView(this)
        }
    }

    fun cancel() {
        if (mView != null) {
            mView?.visibility = View.GONE
            mParent.removeView(mView)
            mView = null
        }
    }

    fun release() {
        if (mView != null && mView is Control) {
            (mView as Control).onPause()
            mParent.removeView(mView)
            mView = null
        }
    }

}