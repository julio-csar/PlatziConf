package com.julio.platziconf.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.julio.platziconf.R
import kotlinx.android.synthetic.main.activity_splashscreen.*

class SplashscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val intent = Intent(this,MainActivity::class.java)

        val animation = AnimationUtils.loadAnimation(this,R.anim.animation_splashscreen)
        ivSplashLogo.startAnimation(animation)

        animation.setAnimationListener(object:Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                startActivity(intent)
                finish()
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })
    }
}
