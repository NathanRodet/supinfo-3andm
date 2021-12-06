package com.supinfo.supfitness.utilities;

import android.app.Dialog
import android.app.Person
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment;
import com.supinfo.supfitness.R
import com.supinfo.supfitness.database.AppDatabase

class DialogWeight: DialogFragment(){



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        setNumberPicker()
        return inflater.inflate(R.layout.weight_form,container,false)
    }
    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun setNumberPicker(){
        val numberPicker = view?.findViewById<NumberPicker>(R.id.numberPicker)
        numberPicker?.minValue = 0
        numberPicker?.maxValue = 300
        numberPicker?.value = 50
    }


    data class dayWeight(var user: String, var poids: Float)

    fun showDialog(context: Context){
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.weight_form)

        val nb : NumberPicker = dialog.findViewById(R.id.numberPicker) as NumberPicker
        val etUsername = dialog.findViewById<EditText>(R.id.username)
        val submit = dialog.findViewById(R.id.buttonWeight) as Button
        val delete = dialog.findViewById(R.id.delete) as Button
        nb.maxValue = 200
        nb.minValue = 0
        nb.wrapSelectorWheel = false
        nb.value = 50

        val d = dayWeight("test", 50.0F)
        delete.setOnClickListener{
            dialog.dismiss()
        }
        submit.setOnClickListener {
            d.user = etUsername.text.toString()
            d.poids = nb.value.toFloat()

            if(d.user.trim().isNotEmpty()) {

                Toast.makeText(context, "Le poids de l'utilisateur: " + d.user + " a été ajouté !", Toast.LENGTH_SHORT).show()
                dialog.dismiss()

            }else{
                Toast.makeText(context, "Saisir un nom d'utilisateur ! ", Toast.LENGTH_SHORT).show()
            }



        }

        dialog.show()


    }

}
