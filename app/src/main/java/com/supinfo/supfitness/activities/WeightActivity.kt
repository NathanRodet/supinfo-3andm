package com.supinfo.supfitness.activities


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.supinfo.supfitness.R
import com.supinfo.supfitness.views.weightAdapter
import com.supinfo.supfitness.database.AppDatabase
import com.supinfo.supfitness.database.data.Weight
import com.supinfo.supfitness.utilities.GetDate
import kotlinx.android.synthetic.main.activity_weight.*

class WeightActivity : AppCompatActivity() {

    lateinit var data: MutableList<Weight>
    lateinit var weightAdapter: weightAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)


        //Instance Database from companion object
        var database = AppDatabase.getInstance(this)

        fun deleteItem(index : Int){
            if(::data.isInitialized){
                data.removeAt(index)
                Log.d("debug", data.toString())
                database.getWeightDao().delete(data[index])
                database.getWeightDao().update(data[index])
                weightAdapter.setItems(data)
            }
        }

        val allWeightData = database.getWeightDao().getAll()
        data = allWeightData as MutableList<Weight>
        Log.d("debugGetALl", allWeightData.toString())
        weightRecyclerView.apply {

            layoutManager = LinearLayoutManager(this@WeightActivity)
            adapter = weightAdapter(data){index -> deleteItem(index)}

        }



        // Preparing data for database insert and checking boolean
        val setDate = GetDate()
        val dateValue = setDate.getCurrentDateTime()

        // Previous Date
        val previousDateHolder = database.getWeightDao().getDates()
        val previousDateSplitter = previousDateHolder.toString().split(" ").toTypedArray()
        val previousDate = previousDateSplitter[0].drop(1)

        // Today's Date
        val todayDateSplitter = dateValue.split(" ").toTypedArray()
        val todayDate = todayDateSplitter[0]


        // Debug logs previousDate
        Log.d("debugWeight", "previousDateHolder $previousDateHolder")
        // Result logs previousDate
        Log.d("debugWeight", "previousDate $previousDate")

        // Debug logs Today's Date
        Log.d("debugWeight", "todayDateHolder $dateValue")
        // Result logs Today's Date
        Log.d("debugWeight", "todayDate $todayDate")

        //dialog
        fun showDialog(context: Context){
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.weight_form)

            //CustomAlert
            val nb : NumberPicker = dialog.findViewById(R.id.numberPicker)
            val etUsername = dialog.findViewById<EditText>(R.id.username)
            val submit = dialog.findViewById<Button>(R.id.buttonWeight)
            val delete = dialog.findViewById<Button>(R.id.closeDialog)

            nb.maxValue = 200
            nb.minValue = 0
            nb.wrapSelectorWheel = false
            nb.value = 50

            delete.setOnClickListener{
                dialog.dismiss()
            }
            submit.setOnClickListener {

                val userName = etUsername.text.toString()
                val userWeight = nb.value.toFloat()

                if(userName.trim().isNotEmpty()) {

                    // Block multiple date input per day
                    if (todayDate == previousDate) {

                        // Toast date already set Today
                        Toast.makeText(
                            context,
                            getString(R.string.toast_weight_already_set),
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        // Insert data
                        try {
                            val data = Weight(
                                user = userName,
                                weight = userWeight,
                                date = dateValue
                            ).apply {
                                user = userName
                                weight = userWeight
                                date = dateValue
                            }

                            database.getWeightDao().insertAll(data)

                            // Test logs for input data
                            Log.d(
                                "debugWeight",
                                "insertData $data"
                            )

                            // Success toast
                            Toast.makeText(
                                context,
                                "Le poids de l'utilisateur: $userName a été ajouté !",
                                Toast.LENGTH_SHORT
                            ).show()

                        } catch (e: Exception){
                            Log.d("Error", "$e")
                        }
                    }
                    // Close dialog
                    dialog.dismiss()
                }else{

                    Toast.makeText(
                        context,
                        getString(R.string.error_missing_username),
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

            dialog.show()
        }

        val buttonAdd: FloatingActionButton = findViewById(R.id.fab)

        //fab button
        buttonAdd.setOnClickListener {
            Log.d("debug", "click")
            showDialog(this)
        }

        // TabLayout Navigation
        val buttonWeight: Button = findViewById(R.id.buttonWeight)
        val buttonTrack: Button = findViewById(R.id.buttonTrack)
        val buttonChart: Button = findViewById(R.id.buttonChart)
        buttonWeight.setBackgroundColor(Color.parseColor("#33018786"))

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


