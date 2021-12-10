package com.supinfo.supfitness.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.supinfo.supfitness.R
import com.supinfo.supfitness.databinding.ActivitySplashScreenBinding


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val viewRoot = binding.root
        setContentView(viewRoot)

        val logo: ImageView = findViewById(R.id.logoSplashScreen)

        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        logo.startAnimation(slideAnimation)
    }

    private val runnable = Runnable {
        if (!isFinishing) {
            // Starting the Splash Screen
            startActivity(Intent(applicationContext, WeightActivity::class.java))
            // Finishing the Splash Screen
            finish()
        }
    }

    // Function timer (5000ms)
    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 5000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}