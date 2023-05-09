package com.natiqhaciyef.green_chat_app.view.screens.home.fragment

import android.os.Bundle
import android.os.Message
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.natiqhaciyef.green_chat_app.R
import com.natiqhaciyef.green_chat_app.data.model.ChatModel
import com.natiqhaciyef.green_chat_app.databinding.FragmentChatBinding
import com.natiqhaciyef.green_chat_app.view.adapter.MessageAdapter
import com.natiqhaciyef.green_chat_app.view.viewmodel.home.ChatViewModel
import com.natiqhaciyef.green_chat_app.view.viewmodel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private val viewModel: HomeViewModel by viewModels()
    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllChatsFromFirebase()
        val navHostData: ChatFragmentArgs by navArgs()
        val receiverEmail = navHostData.email
        val senderEmail = Firebase.auth.currentUser?.email
        viewModel.chats.observe(viewLifecycleOwner) { list ->
            val chatModel = if (list.isNotEmpty()) list.filter {
                it.users.contains(receiverEmail)
                        && it.users.contains(senderEmail)
            }[0] else
                ChatModel(mutableListOf(), mutableListOf())

            val adapter =
                MessageAdapter(requireContext(), chatModel.chat, receiverEmail, senderEmail ?: "")
            binding.recyclerMessage.adapter = adapter
            binding.recyclerMessage.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            binding.sendButton.setOnClickListener {
                val message = binding.textingPanel.text.toString()
                if (message.isNotEmpty()){
                    chatViewModel.sendDataToFirestore(chatModel, message, receiverEmail)
                    binding.textingPanel.text.clear()
                }
            }
        }
    }
}