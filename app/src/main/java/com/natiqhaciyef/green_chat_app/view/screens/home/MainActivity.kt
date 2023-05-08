package com.natiqhaciyef.green_chat_app.view.screens.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.natiqhaciyef.green_chat_app.R
import com.natiqhaciyef.green_chat_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}