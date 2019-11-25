package com.ahmedmamdouh13.duration.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.ahmedmamdouh13.duration.R
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        GlobalScope.launch(Dispatchers.Main) {
            delay(2000)
            val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@SplashActivity,
                durationicon_imageview_activitysplash,
                getString(R.string.sharedIconName)
            ).toBundle()
            startActivity(Intent(this@SplashActivity, IntroActivity::class.java), bundle)
            delay(1000)
            this@SplashActivity.finish()
        }
    }
}
