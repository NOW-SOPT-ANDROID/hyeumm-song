package com.sopt.now

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemUserBinding

class FriendViewHolder(private val binding:ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: HomeList) {
        binding.run {
            ivProfile.setImageResource(friendData.profileImage)
            tvName.text = friendData.name
            tvSelfDescription.text = friendData.selfDescription
        }
    }
}

class UserViewHolder(private val binding:ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: HomeList) {
        binding.run {
            ivProfile.setImageResource(friendData.profileImage)
            tvName.text = friendData.name
            tvSelfDescription.text = friendData.selfDescription
        }
    }
}