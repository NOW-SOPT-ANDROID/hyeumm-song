package com.sopt.now.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemUserBinding

class HomeListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var homeListList: List<HomeList> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bindingUser = ItemUserBinding.inflate(inflater, parent, false)
        val bindingFriend = ItemFriendBinding.inflate(inflater, parent, false)
        return when(viewType){
            HomeList.VIEW_TYPE_USER -> {
                UserViewHolder(bindingUser)
            }
            else -> {
                FriendViewHolder(bindingFriend)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(homeListList.getOrNull(position)?.viewType){
            HomeList.VIEW_TYPE_USER -> {
                (holder as UserViewHolder).onBind(homeListList[position])
            }
            else -> {
                (holder as FriendViewHolder).onBind(homeListList[position])
            }
        }
    }

    override fun getItemCount() = homeListList.size
    override fun getItemViewType(position: Int): Int {
        return homeListList[position].viewType
    }
    fun setHomeList(homeListList: List<HomeList>) {
        this.homeListList = homeListList.toList()
        notifyDataSetChanged()
    }
}