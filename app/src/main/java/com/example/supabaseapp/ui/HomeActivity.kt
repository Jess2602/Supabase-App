package com.example.supabaseapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.supabaseapp.data.DiscAdapter
import com.example.supabaseapp.data.DiscDataClass
import com.example.supabaseapp.R

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val recyclerView: RecyclerView = findViewById(R.id.discRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val discs = arrayListOf(
            DiscDataClass("Disc ", "yyyy", "Nirvana"),
            DiscDataClass("Disc ", "yyy", "Someone"),
            // Add more discs as needed
        )
        val adapter = DiscAdapter(discs)
        recyclerView.adapter = adapter

    }
}
