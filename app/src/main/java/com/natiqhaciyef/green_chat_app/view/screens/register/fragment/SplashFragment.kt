package com.natiqhaciyef.green_chat_app.view.screens.register.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.green_chat_app.R
import com.natiqhaciyef.green_chat_app.databinding.FragmentSplashBinding
import com.natiqhaciyef.green_chat_app.view.screens.home.MainActivity
import com.natiqhaciyef.green_chat_app.view.viewmodel.registration.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val auth = Firebase.auth.currentUser
        viewModel.dumsFromFirebase.observe(viewLifecycleOwner) { dum ->
            CoroutineScope(Dispatchers.Main).launch {
                delay(2500)
                if (dum.any {
                        if (auth != null)
                            auth.email == it.email
                        else
                            false
                    }
                ) {
                    navigateToOtherActivity()
                } else {
                    findNavController().navigate(R.id.loginFragment)
                }
            }
        }
    }


    private fun navigateToOtherActivity() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }
}