package com.vimalnaina.mynote.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.vimalnaina.mynote.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT : Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        },SPLASH_TIME_OUT)
    }
}