package com.natiqhaciyef.green_chat_app.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.green_chat_app.databinding.RecyclerChatViewBinding
import com.natiqhaciyef.green_chat_app.view.screens.home.fragment.HomeFragmentDirections
import com.natiqhaciyef.green_chat_app.view.viewmodel.home.HomeViewModel
import com.natiqhaciyef.green_chat_app.data.model.ChatModel

class ChatAdapter(val context: Context, val list: List<ChatModel>, val viewModel: HomeViewModel) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    inner class ChatViewHolder(val binding: RecyclerChatViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = RecyclerChatViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatModel = list[position]
        val auth = Firebase.auth.currentUser
        val receiver = chatModel.users.filter {
            it != auth?.email
        }[0]

        val username = viewModel.getUserByEmail(receiver)


        holder.binding.userName.text = username.username
        holder.binding.chatDescription.text =
            if (chatModel.chat.isNotEmpty() && chatModel.chat.last().length > 20
                && !chatModel.chat.last().contains(auth!!.email!!)
            )
                chatModel.chat.last().substring(0..19)
            else if (chatModel.chat.isNotEmpty() && chatModel.chat.last().length <= 20
                && !chatModel.chat.last()
                    .contains(auth!!.email!!)
            )
                chatModel.chat.last()
            else if (
                chatModel.chat.isNotEmpty() && chatModel.chat.last().startsWith(auth!!.email!!)
            )
                chatModel.chat.last().removePrefix("${auth.email!!} : ")
            else
                ""

        holder.itemView.setOnClickListener {
            // onclick action
            val action = HomeFragmentDirections.actionHomeFragmentToChatFragment2(receiver)
            Navigation.findNavController(it).navigate(action)
        }
    }

}