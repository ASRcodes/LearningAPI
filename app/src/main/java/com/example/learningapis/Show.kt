package com.example.learningapis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.learningapis.databinding.ActivityShowBinding

class Show : AppCompatActivity() {
    private lateinit var binding: ActivityShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inst = intent.getStringExtra("INS")
        binding.show.text = inst
        val ingre = intent.getStringExtra("Ingre")
        binding.show2.text = ingre
    }
}