package com.sopt.now

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException

class HomeListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var homeListList: List<HomeList> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val adapterLayout : View?
        return when(viewType){
            HomeList.VIEW_TYPE_USER -> {
                adapterLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user,parent,false)
                UserViewHolder(adapterLayout)
            }
            else -> {
                adapterLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_friend,parent,false)
                FriendViewHolder(adapterLayout)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = homeListList.getOrNull(position)
        if (item != null) {
            when(item.viewType){
                HomeList.VIEW_TYPE_USER -> {
                    (holder as UserViewHolder).ivProfile.setImageResource(item.profileImage)
                    holder.tvName.text = item.name
                    holder.tvSelfDescription.text = item.selfDescription
                    holder.setIsRecyclable(false)
                }
                else -> {
                    (holder as FriendViewHolder).ivProfile.setImageResource(item.profileImage)
                    holder.tvName.text = item.name
                    holder.tvSelfDescription.text = item.selfDescription
                    holder.setIsRecyclable(false)
                }
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