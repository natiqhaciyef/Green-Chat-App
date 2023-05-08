package com.natiqhaciyef.green_chat_app.view.screens.register.fragment

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.natiqhaciyef.green_chat_app.R
import com.natiqhaciyef.green_chat_app.databinding.FragmentLoginBinding
import com.natiqhaciyef.green_chat_app.view.screens.home.MainActivity
import com.natiqhaciyef.green_chat_app.view.viewmodel.registration.RegistrationViewModel
import com.natiqhaciyef.greenchatapp.data.model.DataUserModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dumsFromFirebase.observe(viewLifecycleOwner) { dums ->
            binding.loginButton.setOnClickListener {
                val email = binding.emailInputLogin.text.toString()
                val password = binding.passwordInputLogin.text.toString()
                login(
                    email = email,
                    password = password,
                    dumsFromFirebase = dums,
                    viewModel = viewModel,
                )
            }
        }

        binding.googleLogin.setOnClickListener {
            // google login
        }

        binding.facebookLogin.setOnClickListener {
            // facebook login
        }

        binding.singUpBtn.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment)
        }

        binding.passwordVisible.setOnClickListener {
            binding.passwordInputLogin.transformationMethod =
                PasswordTransformationMethod.getInstance()
            binding.passwordVisible.visibility = View.GONE
            binding.passwordVisibleOff.visibility = View.VISIBLE
        }

        binding.passwordVisibleOff.setOnClickListener {
            binding.passwordInputLogin.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
            binding.passwordVisible.visibility = View.VISIBLE
            binding.passwordVisibleOff.visibility = View.GONE
        }

    }



    private fun login(
        email: String,
        password: String,
        dumsFromFirebase: List<DataUserModel>,
        viewModel: RegistrationViewModel,
    ) {
        val test = dumsFromFirebase.filter {
            email == it.email &&
                    password == it.password
        }

        println(test)

        if (test.isNotEmpty()) {
            viewModel.login(email, password)
            navigateToOtherActivity()
        }
    }

    private fun navigateToOtherActivity(){
        val intent = Intent(requireActivity(), MainActivity::class.java)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }

}