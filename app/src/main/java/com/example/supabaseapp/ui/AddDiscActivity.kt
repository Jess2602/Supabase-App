package com.example.supabaseapp.ui


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.supabaseapp.databinding.AddDiscActivityBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.Request
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class AddDiscActivity : AppCompatActivity() {
    private lateinit var binding: AddDiscActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddDiscActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle button click to add data to Supabase
        binding.addDiscToRecyclerButton.setOnClickListener {
            val discName = binding.discNameEditText.text.toString()
            val discYear = binding.discYearEditText.text.toString()
            val discArtist = binding.discArtistEditText.text.toString()
            if (discName.isNotEmpty() && discYear.isNotEmpty() && discArtist.isNotEmpty()) {
                addDataToSupabase(discName, discYear, discArtist)
            } else {
                Toast.makeText(applicationContext, "Faltan Campos por Rellenar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backArrow.setOnClickListener {
            val intent = Intent(this@AddDiscActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addDataToSupabase(title: String, year: String, artist: String) {
        val supabaseUrl = "https://orsdaquwteitkgppthww.supabase.co"
        val supabaseKey =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9yc2RhcXV3dGVpdGtncHB0aHd3Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY5Nzk0NjI5MiwiZXhwIjoyMDEzNTIyMjkyfQ.C-Yk_PVltRV4upfWNQ0hAgTxnP8MMWTCOPMTgApeaww"

        val client = OkHttpClient()
        val requestBody = FormBody.Builder()
            .add("discName", title)
            .add("discYear", year)
            .add("discArtist", artist)
            .build()

        val request = Request.Builder()
            .url("$supabaseUrl/rest/v1/discs") // Reemplaza "your_table_name" por el nombre de tu tabla
            .header("apikey", supabaseKey)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Error al insertar datos en Supabase: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    println("Datos insertados en Supabase exitosamente")
                    val intent = Intent(this@AddDiscActivity, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    println("Error al insertar datos en Supabase: ${response.code}")
                }
            }
        })
    }


}




