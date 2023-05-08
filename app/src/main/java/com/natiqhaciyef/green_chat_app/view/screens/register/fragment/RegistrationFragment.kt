package com.natiqhaciyef.green_chat_app.view.screens.register.fragment

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.natiqhaciyef.green_chat_app.R
import com.natiqhaciyef.green_chat_app.databinding.FragmentRegistrationBinding
import com.natiqhaciyef.green_chat_app.view.viewmodel.registration.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            val email = binding.emailInputRegister.text.toString()
            val password = binding.passwordInputRegister.text.toString()
            val username = binding.usernameInputRegister.text.toString()
            val phone = binding.phoneInputRegister.text.toString()

            register(email, password, username, phone)
        }

        binding.singInBtn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        binding.passwordVisible.setOnClickListener {
            binding.passwordInputRegister.transformationMethod =
                PasswordTransformationMethod.getInstance()
            binding.passwordVisible.visibility = View.GONE
            binding.passwordVisibleOff.visibility = View.VISIBLE
        }

        binding.passwordVisibleOff.setOnClickListener {
            binding.passwordInputRegister.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
            binding.passwordVisible.visibility = View.VISIBLE
            binding.passwordVisibleOff.visibility = View.GONE
        }

    }

    private fun register(
        email: String,
        password: String,
        username: String,
        phone: String,
    ){
        viewModel.register(
            email = email,
            password = password,
            username = username,
            phone = phone,
        ) {
            findNavController().navigate(R.id.loginFragment)
        }
    }
}