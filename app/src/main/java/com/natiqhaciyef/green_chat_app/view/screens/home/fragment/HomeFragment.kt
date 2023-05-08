package com.natiqhaciyef.green_chat_app.view.screens.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.natiqhaciyef.green_chat_app.R
import com.natiqhaciyef.green_chat_app.databinding.FragmentHomeBinding
import com.natiqhaciyef.green_chat_app.domain.util.obj.UserList
import com.natiqhaciyef.green_chat_app.view.adapter.ChatAdapter
import com.natiqhaciyef.green_chat_app.view.adapter.StoryAdapter
import com.natiqhaciyef.green_chat_app.view.viewmodel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var storyAdapter: StoryAdapter
    private lateinit var chatAdapter: ChatAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        storyAdapter = StoryAdapter(requireContext(), UserList.storyList)
        binding.recyclerStory.adapter = storyAdapter
        binding.recyclerStory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun observeLiveData() {
        viewModel.chats.observe(viewLifecycleOwner) { chatModels ->
            chatAdapter = ChatAdapter(requireContext(), chatModels, viewModel)
            binding.recyclerChat.adapter = chatAdapter
            binding.recyclerChat.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
}