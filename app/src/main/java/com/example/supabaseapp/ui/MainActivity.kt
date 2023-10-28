package com.example.supabaseapp.ui


import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.supabaseapp.R
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()

        setContentView(R.layout.main_activity)

        val signInButton = findViewById<Button>(R.id.btnSignIn)

        signInButton.setOnClickListener {
            setContentView(R.layout.home_activity)
        }
    }

    private fun getData() {
        lifecycleScope.launch {
            val client = getClient()
            val supabaseResponse = client.postgrest["users"].select()
            val data = supabaseResponse.decodeList<User>()
            Log.e("supabase", data.toString())
        }


    }

    private fun getClient() : SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://orsdaquwteitkgppthww.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9yc2RhcXV3dGVpdGtncHB0aHd3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTc5NDYyOTIsImV4cCI6MjAxMzUyMjI5Mn0.zuqYT50yUvFMfcxYxUvSg4NS1bO_hdBifJPsIzP_cYQ"
        ) {
            install(GoTrue)
            install(Postgrest)
        }
    }
}
@Serializable
data class User(
    val id: Int = 0,
    val email: String = "",
    val password: String = ""
)

