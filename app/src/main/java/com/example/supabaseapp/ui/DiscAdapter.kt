package com.example.supabaseapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.supabaseapp.R

class DiscAdapter(private var discs: List<DiscDataClass>) :
    RecyclerView.Adapter<DiscAdapter.DiscViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_list, parent, false)
        return DiscViewHolder(view)
    }

    override fun getItemCount(): Int {
        return discs.size
    }

    override fun onBindViewHolder(holder: DiscViewHolder, position: Int) {
        val currentDisc = discs[position]
        holder.discName.text = currentDisc.discName
        holder.textViewDiscYear.text = currentDisc.discYear
        holder.textViewDiscArtist.text = currentDisc.discArtist
        // Puedes descomentar esta línea si tienes una propiedad "image" en DiscDataClass
        // holder.imageViewDiscImage.setImageResource(currentDisc.image)
    }

    class DiscViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val discName: TextView = itemView.findViewById(R.id.textViewDiscName)
        val textViewDiscYear: TextView = itemView.findViewById(R.id.textViewDiscYear)
        val textViewDiscArtist: TextView = itemView.findViewById(R.id.textViewDiscArtist)
        // Si tienes una ImageView en el layout, puedes agregarla aquí y descomentar en onBindViewHolder
        // val imageViewDiscImage: ImageView = itemView.findViewById(R.id.imageViewDiscImage)
        }
}