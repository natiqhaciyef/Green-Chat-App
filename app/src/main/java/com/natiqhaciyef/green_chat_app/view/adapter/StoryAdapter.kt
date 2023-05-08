package com.natiqhaciyef.green_chat_app.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.natiqhaciyef.green_chat_app.databinding.RecyclerStoryViewBinding
import com.natiqhaciyef.green_chat_app.data.model.UserStory

class StoryAdapter(val context: Context, val list: List<UserStory>) :
    RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    class StoryViewHolder(val binding: RecyclerStoryViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = RecyclerStoryViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = list[position]
        holder.binding.storyImage.setImageResource(
            context.resources.getIdentifier(story.image, "drawable", context.packageName)
        )
        holder.binding.storyTitle.text = story.name
    }
}