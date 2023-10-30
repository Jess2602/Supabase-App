package com.example.supabaseapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.supabaseapp.R

class DiscAdapter(private var discs: List<DiscDataClass>) :
    RecyclerView.Adapter<DiscAdapter.DiscViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiscViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_list, parent, false)
        return DiscViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiscViewHolder, position: Int) {
        val currentDisc = discs[position]
        holder.discName.text = currentDisc.discName
        holder.textViewDiscYear.text = currentDisc.discYear
        holder.textViewDiscArtist.text = currentDisc.discArtist
    }
    fun setData(newData: List<DiscDataClass>) {
        discs = newData
        notifyDataSetChanged() // Notifica al RecyclerView que los datos han cambiado
    }
    override fun getItemCount(): Int {
        return discs.size
    }

    class DiscViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val discName: TextView = itemView.findViewById(R.id.textViewDiscName)
        val textViewDiscYear: TextView = itemView.findViewById(R.id.textViewDiscYear)
        val textViewDiscArtist: TextView = itemView.findViewById(R.id.textViewDiscArtist)
    }
}