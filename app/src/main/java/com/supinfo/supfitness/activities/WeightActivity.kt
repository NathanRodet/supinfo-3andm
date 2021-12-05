package com.supinfo.supfitness.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.supinfo.supfitness.R
import com.supinfo.supfitness.database.AppDatabase
import com.supinfo.supfitness.utilities.DialogWeight
import kotlinx.android.synthetic.main.tab_layout.*
import com.google.android.material.dialog.MaterialDialogs as MaterialDialogs

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

        buttonAdd.setOnClickListener() {
            Log.d("debug", "click")
            showDialog()


        }


    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.weight_form)


        val yesBtn = dialog.findViewById(R.id.buttonWeight) as Button

        yesBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }
}
