package com.foretree.support.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.foretree.support.lib.ResSplashFactory
import com.foretree.support.lib.SplashHelper
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by silen on 2018/8/23 20:06
 * Copyright (c) 2018 in FORETREE
 */
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        SplashHelper.bind(root, ResSplashFactory()).res(R.drawable.splash).show()
        root.postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 3000)
    }
}