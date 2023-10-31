package com.example.supabaseapp.ui


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.supabaseapp.R
import com.example.supabaseapp.databinding.MainActivityBinding


class MainActivity : AppCompatActivity() {

    private val splashDisplayLength = 800 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity) // Use the splash layout

        // Use a Handler to delay the transition to the HomeActivity
        Handler(Looper.getMainLooper()).postDelayed({
            // Create an Intent to navigate to your HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // Finish the splash activity to prevent going back to it
        }, splashDisplayLength.toLong())
    }
}
