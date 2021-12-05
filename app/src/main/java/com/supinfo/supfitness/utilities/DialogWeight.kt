package com.supinfo.supfitness.utilities;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment;
import com.supinfo.supfitness.R

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

}
