package com.example.supabaseapp.ui


import android.app.AlertDialog
import android.content.Intent
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.supabaseapp.R
import java.io.IOException


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
        val ids = currentDisc.id
        holder.deleteImage?.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val alertDialog = AlertDialog.Builder(activity)
            alertDialog.setTitle("Confirmación")
            alertDialog.setMessage("¿Desea eliminar el disco?")

            // Botón "Sí"
            alertDialog.setPositiveButton("Sí") { dialog, which ->
                val supabaseUrl = "https://orsdaquwteitkgppthww.supabase.co"
                val supabaseKey =
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9yc2RhcXV3dGVpdGtncHB0aHd3Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY5Nzk0NjI5MiwiZXhwIjoyMDEzNTIyMjkyfQ.C-Yk_PVltRV4upfWNQ0hAgTxnP8MMWTCOPMTgApeaww"

                val client = OkHttpClient()

                val request = Request.Builder()
                    .url("$supabaseUrl/rest/v1/discs?id=eq.$ids")
                    .header("apikey", supabaseKey)
                    .delete()
                    .build()

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("Error al eliminar la fila en Supabase: ${e.message}")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        if (response.isSuccessful) {
                            println("Fila eliminada en Supabase exitosamente")
                            val intent = Intent(activity, HomeActivity::class.java)
                            activity.startActivity(intent)
                        } else {
                            println("Error al eliminar la fila en Supabase: ${response.code}")
                        }
                    }
                })
            }

            alertDialog.setNegativeButton("No") { dialog, which ->

            }

            alertDialog.show()

        }

    }
    fun setData(newData: List<DiscDataClass>) {
        discs = newData
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return discs.size
    }

    class DiscViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val discName: TextView = itemView.findViewById(R.id.textViewDiscName)
        val textViewDiscYear: TextView = itemView.findViewById(R.id.textViewDiscYear)
        val textViewDiscArtist: TextView = itemView.findViewById(R.id.textViewDiscArtist)
        val deleteImage: View? = itemView.findViewById(R.id.deleteImageView)
    }
}