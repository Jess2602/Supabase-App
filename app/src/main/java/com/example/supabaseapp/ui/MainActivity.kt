package com.example.supabaseapp.ui


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.supabaseapp.databinding.MainActivityBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request.*
import okhttp3.Response
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchSupabaseData()

        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchSupabaseData() {
        val supabaseUrl = "https://orsdaquwteitkgppthww.supabase.co"
        val supabaseKey =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9yc2RhcXV3dGVpdGtncHB0aHd3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTc5NDYyOTIsImV4cCI6MjAxMzUyMjI5Mn0.zuqYT50yUvFMfcxYxUvSg4NS1bO_hdBifJPsIzP_cYQ"

        val client = OkHttpClient()
        val request = Builder()
            .url("$supabaseUrl/rest/v1/users")
            .header("apikey", supabaseKey)
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Error al obtener datos de Supabase: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    println("Datos de Supabase dddd: $responseData")

                } else {
                    println("Error al obtener datos de Supabase: ${response.code}")
                }
            }
        })
    }
}
