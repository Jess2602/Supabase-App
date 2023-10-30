package com.example.supabaseapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.supabaseapp.databinding.AddDiscActivityBinding
import com.example.supabaseapp.databinding.HomeActivityBinding
import com.example.supabaseapp.databinding.MainActivityBinding
import com.example.supabaseapp.ui.theme.SupabaseAppTheme

class AddDiscActivity : AppCompatActivity() {

    private lateinit var binding: AddDiscActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddDiscActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}

