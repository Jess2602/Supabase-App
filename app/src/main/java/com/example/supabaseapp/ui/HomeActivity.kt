package com.example.supabaseapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supabaseapp.databinding.HomeActivityBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: HomeActivityBinding
    private lateinit var adapter: DiscAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        seeRecycler()
        fetchSupabaseData()
    }

    private fun seeRecycler() {
        adapter = DiscAdapter(emptyList()) // Inicialmente, el adaptador estará vacío
        binding.discRecyclerView.adapter = adapter
        binding.discRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchSupabaseData() {
        val supabaseUrl = "https://orsdaquwteitkgppthww.supabase.co"
        val supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9yc2RhcXV3dGVpdGtncHB0aHd3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTc5NDYyOTIsImV4cCI6MjAxMzUyMjI5Mn0.zuqYT50yUvFMfcxYxUvSg4NS1bO_hdBifJPsIzP_cYQ"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("$supabaseUrl/rest/v1/discs")
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
                    println("Datos de Supabase: $responseData")

                    val discList = parseData(responseData)

                    runOnUiThread {
                        updateRecyclerView(discList)
                    }
                } else {
                    println("Error al obtener datos de Supabase: ${response.code}")
                }
            }
        })
    }

    private fun parseData(responseData: String?): List<DiscDataClass> {
        val gson = Gson()
        return gson.fromJson(responseData, object : TypeToken<List<DiscDataClass>>() {}.type)
    }

    private fun updateRecyclerView(data: List<DiscDataClass>) {
        adapter.setData(data)
    }
}