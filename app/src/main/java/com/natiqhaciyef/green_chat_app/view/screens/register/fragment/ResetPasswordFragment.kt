package com.natiqhaciyef.green_chat_app.view.screens.register.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.natiqhaciyef.green_chat_app.R
import com.natiqhaciyef.green_chat_app.databinding.FragmentResetPasswordBinding
import com.natiqhaciyef.green_chat_app.view.viewmodel.registration.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendButton.setOnClickListener {
            val email = binding.emailInputResetPassword.text.toString()
            viewModel.send(email){
                findNavController().navigate(R.id.loginFragment)
            }
        }
    }
}