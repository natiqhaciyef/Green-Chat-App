package com.natiqhaciyef.green_chat_app.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.natiqhaciyef.green_chat_app.databinding.RecyclerMessageViewBinding

class MessageAdapter(
    val context: Context,
    val messages: List<String>,
    val receiver: String,
    val sender: String
) :
    RecyclerView.Adapter<MessageAdapter.MessageHolder>() {

    inner class MessageHolder(val binding: RecyclerMessageViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val binding =
            RecyclerMessageViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return MessageHolder(binding)
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        val message = messages[position]
        if (message.startsWith(sender)) {
            // sender
            val mess = message.removePrefix(sender).removePrefix(" : ")
            holder.binding.messageTextSender.visibility = View.VISIBLE
            holder.binding.messageTextReceiver.visibility = View.GONE

            holder.binding.messageTextSender.text = mess
        } else if(message.startsWith(receiver)) {
            // receiver
            val mess = message.removePrefix(receiver).removePrefix(" : ")
            holder.binding.messageTextSender.visibility = View.GONE
            holder.binding.messageTextReceiver.visibility = View.VISIBLE

            holder.binding.messageTextReceiver.text = mess
        }else{
            // receiver
            val mess = message.removePrefix(receiver).removePrefix(" : ")
            holder.binding.messageTextSender.visibility = View.GONE
            holder.binding.messageTextReceiver.visibility = View.VISIBLE

            holder.binding.messageTextReceiver.text = mess
        }
    }
}