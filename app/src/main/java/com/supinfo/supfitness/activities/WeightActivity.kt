package com.supinfo.supfitness.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.supinfo.supfitness.R
import kotlinx.android.synthetic.main.tab_layout.*

class WeightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TabLayout Navigation
        val buttonWeight: Button = findViewById<Button>(R.id.buttonWeight)
        val buttonTrack: Button = findViewById<Button>(R.id.buttonTrack)
        val buttonChart: Button = findViewById<Button>(R.id.buttonChart)
        buttonWeight.setBackgroundColor(Color.parseColor("#FF018786"))

        buttonWeight.setOnClickListener(){
            startActivity(Intent(this, WeightActivity::class.java))
        }
        buttonTrack.setOnClickListener(){
            startActivity(Intent(this, TrackActivity::class.java))
        }
        buttonChart.setOnClickListener(){
            startActivity(Intent(this, ChartActivity::class.java))
        }

    }
}