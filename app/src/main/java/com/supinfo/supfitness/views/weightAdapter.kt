package com.supinfo.supfitness.views

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.supinfo.supfitness.R
import com.supinfo.supfitness.database.data.Weight
import kotlinx.android.synthetic.main.weight_card_view.view.*

class WeightAdapter(private val allWeight: List<Weight>) : RecyclerView.Adapter<WeightAdapter.ViewHolder>(){

private var listData : MutableList<Weight> = allWeight as MutableList<Weight>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weight_card_view, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.twDate.text = allWeight[position].date
        holder.itemView.twUser.text = allWeight[position].user
        holder.itemView.twWeight.text = allWeight[position].weight.toString()
        holder.bind(position)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Weight>){
    listData = items as MutableList<Weight>
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteItem(index : Int){
        listData.removeAt(index)
        notifyDataSetChanged()
    }

    override fun getItemCount() = listData.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        fun bind(index : Int){

            val buttonDelete = view.findViewById<Button>(R.id.buttonDelete)

            buttonDelete.setOnClickListener{deleteItem(index)}

        }

    }


}


