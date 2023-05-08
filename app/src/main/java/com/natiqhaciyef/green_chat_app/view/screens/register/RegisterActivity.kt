package com.natiqhaciyef.green_chat_app.view.screens.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.natiqhaciyef.green_chat_app.R
import com.natiqhaciyef.green_chat_app.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}