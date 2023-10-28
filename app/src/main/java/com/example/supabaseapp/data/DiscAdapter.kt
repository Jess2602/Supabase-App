package com.example.supabaseapp.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.supabaseapp.R

class DiscAdapter(private val discs: List<DiscDataClass>) :
    RecyclerView.Adapter<DiscAdapter.DiscViewHolder>() {

    inner class DiscViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val discName: TextView = itemView.findViewById(R.id.textViewDiscName)
        val textViewDiscYear: TextView = itemView.findViewById(R.id.textViewDiscYear)
        val textViewDiscArtist: TextView = itemView.findViewById(R.id.textViewDiscArtist)
        //val imageViewDiscImage: ImageView = itemView.findViewById(R.id.imageViewDiscImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_list, parent, false)
        return DiscViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DiscViewHolder, position: Int) {
        val currentDisc = discs[position]
        holder.discName.text = currentDisc.discName
        holder.textViewDiscYear.text = currentDisc.discYear
        holder.textViewDiscArtist.text = currentDisc.discArtist
        //holder.imageViewDiscImage.image = currentDisc.image
    }

    override fun getItemCount() = discs.size
}
