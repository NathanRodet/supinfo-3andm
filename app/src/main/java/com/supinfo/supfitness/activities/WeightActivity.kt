package com.supinfo.supfitness.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.supinfo.supfitness.R
import com.supinfo.supfitness.database.AppDatabase
import com.supinfo.supfitness.utilities.DialogWeight

class WeightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)

        //Instance Database from companion object
        val database = AppDatabase.getInstance(this)

        // TabLayout Navigation
        val buttonWeight: Button = findViewById<Button>(R.id.buttonWeight)
        val buttonTrack: Button = findViewById<Button>(R.id.buttonTrack)
        val buttonChart: Button = findViewById<Button>(R.id.buttonChart)
        val buttonAdd: FloatingActionButton = findViewById<FloatingActionButton>(R.id.fab)
        buttonWeight.setBackgroundColor(Color.parseColor("#FF018786"))

        buttonWeight.setOnClickListener() {
            startActivity(Intent(this, WeightActivity::class.java))
        }
        buttonTrack.setOnClickListener() {
            startActivity(Intent(this, TrackActivity::class.java))
        }
        buttonChart.setOnClickListener() {
            startActivity(Intent(this, ChartActivity::class.java))
        }


        val dialog = DialogWeight()


        buttonAdd.setOnClickListener() {
            Log.d("debug", "click")
            dialog.showDialog(this)

        }


    }


}
