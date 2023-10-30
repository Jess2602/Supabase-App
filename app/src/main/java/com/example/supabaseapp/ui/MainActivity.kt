package com.example.supabaseapp.ui


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.supabaseapp.R
import com.example.supabaseapp.databinding.MainActivityBinding
import kotlinx.serialization.Serializable
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request.*
import okhttp3.Response
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        getData()
        fetchSupabaseData()

        setContentView(R.layout.main_activity)

        val signInButton = findViewById<Button>(R.id.btnSignIn)

        signInButton.setOnClickListener {
            setContentView(R.layout.home_activity)
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

@Serializable
data class User(
    val id: Int = 0,
    val email: String = "",
    val password: String = ""
)

