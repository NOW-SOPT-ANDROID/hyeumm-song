package com.sopt.now

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException

class FriendAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // 임시의 빈 리스트
    private var friendList: List<Friend> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val adapterLayout : View?
        return when(viewType){
            Friend.VIEW_TYPE_USER -> {
                adapterLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user,parent,false)
                UserViewHolder(adapterLayout)
            }
            Friend.VIEW_TYPE_FRIEND -> {
                adapterLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_friend,parent,false)
                FriendViewHolder(adapterLayout)
            }
            else -> throw RuntimeException("알 수 없는 뷰타입입니다.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = friendList[position]
        when(item.viewType){
            Friend.VIEW_TYPE_USER -> {
                (holder as UserViewHolder).ivProfile.setImageResource(item.profileImage)
                holder.tvName.text = item.name
                holder.tvSelfDescription.text = item.selfDescription
                holder.setIsRecyclable(false)
            }
            Friend.VIEW_TYPE_FRIEND -> {
                (holder as FriendViewHolder).ivProfile.setImageResource(item.profileImage)
                holder.tvName.text = item.name
                holder.tvSelfDescription.text = item.selfDescription
                holder.setIsRecyclable(false)
            }
            else -> throw RuntimeException("알 수 없는 뷰타입입니다.")
        }
    }

    override fun getItemCount() = friendList.size
    override fun getItemViewType(position: Int): Int {
        return friendList[position].viewType
    }
    fun setFriendList(friendList: List<Friend>) {
        this.friendList = friendList.toList()
        notifyDataSetChanged()
    }
}