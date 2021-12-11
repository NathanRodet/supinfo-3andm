package com.supinfo.supfitness.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.supinfo.supfitness.R
import kotlinx.android.synthetic.main.activity_chart.*
import com.github.mikephil.charting.components.XAxis
import com.supinfo.supfitness.database.AppDatabase
import java.lang.Exception
class ChartActivity : AppCompatActivity() {
    // Preparing MPAndroidChart variables
    private lateinit var lineList:ArrayList<Entry>
    private lateinit var lineDataSet: LineDataSet
    private lateinit var lineData: LineData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        // Instance Database from companion object
        val database = AppDatabase.getInstance(this)
        // Getting weight data to draw chart
        val dataWeights = database.getWeightDao().getWeights()

        // Processing data on chart creation
        lineList = ArrayList()
        var x = 0f

        try {
            for (y in dataWeights) {
                x += 1f
                lineList.add(Entry(x, y))
                Log.d("debugChart", "entry $x  $y")
            }
        }catch (e: Exception){
            throw e
        }


        //Creating Chart and styling
        lineDataSet = LineDataSet(lineList, "Weight")
        lineData = LineData(lineDataSet)
        lineChart.data=lineData
        lineDataSet.color = Color.WHITE
        lineDataSet.setColors(*ColorTemplate.LIBERTY_COLORS)
        lineDataSet.valueTextColor = Color.BLACK
        lineDataSet.valueTextSize = 12f
        lineDataSet.setDrawFilled(true)
        lineChart.axisRight.setDrawLabels(false)
        lineChart.description.text = getString(R.string.chart_description)
        lineChart.description.textSize = 14f
        val xAxis: XAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM


        // TabLayout Navigation
        val buttonWeight: Button = findViewById(R.id.buttonWeight)
        val buttonTrack: Button = findViewById(R.id.buttonTrack)
        val buttonChart: Button = findViewById(R.id.buttonChart)
        buttonChart.setBackgroundColor(Color.parseColor("#33018786"))

        buttonWeight.setOnClickListener {
            startActivity(Intent(this, WeightActivity::class.java))
        }
        buttonTrack.setOnClickListener {
            startActivity(Intent(this, TrackActivity::class.java))
        }
        buttonChart.setOnClickListener {
            startActivity(Intent(this, ChartActivity::class.java))
        }
    }
}