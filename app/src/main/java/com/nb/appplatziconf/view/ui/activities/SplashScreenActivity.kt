package com.nb.appplatziconf.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.nb.appplatziconf.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val animacion = AnimationUtils.loadAnimation(this, R.anim.animacion)
        val ivLogoPlatziConf = findViewById<ImageView>(R.id.ivLogoPlatziConf)
        ivLogoPlatziConf.startAnimation(animacion)
        val intent = Intent(this, MainActivity::class.java)
        animacion.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity(intent)
                finish()
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
    }
}