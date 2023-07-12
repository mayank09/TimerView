package com.showcase.timerview

import android.content.Context
import android.icu.text.UnicodeSetSpanner.CountMethod
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class CountAdapter(val list: ArrayList<Int>, val context: Context): RecyclerView.Adapter<CountAdapter.CountViewHolder> (){
    var isClicked : Boolean = false


    class CountViewHolder(itemView: View) : ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_view, parent, false)
        return CountViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: CountViewHolder, position: Int) {
        var count = list.get(position)
        holder.textView.text =count.toString()
        val counterThread = Thread {
            try {
                while (true) {
                    count++
                   // holder.textView.text =count.toString()
                    notifyItemChanged(position)
                    Thread.sleep(1000)
                }
            } catch (e: Exception) {
            }
        }


        //click actions
        holder.itemView.setOnClickListener {
            if(isClicked){
                counterThread.interrupt()
            }else{
                counterThread.start()
            }

        }

    }
}