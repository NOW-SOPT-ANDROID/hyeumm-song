package com.sopt.now

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemUserBinding

class FriendViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val ivProfile : ImageView = view.findViewById(R.id.iv_profile)
    val tvName : TextView = view.findViewById(R.id.tv_name)
    val tvSelfDescription : TextView = view.findViewById(R.id.tv_self_description)
}
class UserViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val ivProfile : ImageView = view.findViewById(R.id.iv_profile)
    val tvName : TextView = view.findViewById(R.id.tv_name)
    val tvSelfDescription : TextView = view.findViewById(R.id.tv_self_description)
}