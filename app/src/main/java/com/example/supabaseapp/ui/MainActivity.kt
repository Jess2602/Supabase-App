package com.example.supabaseapp.ui


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.supabaseapp.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val signInButton = findViewById<Button>(R.id.btnSignIn)

        signInButton.setOnClickListener() {
         setContentView(R.layout.home_activity)

        }
    }


}

