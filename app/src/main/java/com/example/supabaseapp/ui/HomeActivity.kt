package com.example.supabaseapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supabaseapp.R
import com.example.supabaseapp.databinding.HomeActivityBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding
    private lateinit var adapter: DiscAdapter
    private lateinit var list: ArrayList<DiscDataClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        seeRecycler()
    }

    private fun seeRecycler() {
        list = discs() // Obtener la lista de discos
        adapter = DiscAdapter(list) // Asignar la lista al adaptador
        binding.discRecyclerView.adapter = adapter
        binding.discRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun discs(): ArrayList<DiscDataClass> {
        val list = ArrayList<DiscDataClass>()
        list.add(DiscDataClass("nombre1", "yyyy1", "artista1"))
        list.add(DiscDataClass("nombre2", "yyyy2", "artista2"))
        // Agregar más elementos a la lista si es necesario
        return list
    }
}
