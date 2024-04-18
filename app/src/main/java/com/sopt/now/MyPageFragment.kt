package com.sopt.now

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.now.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater)
        getUserInfo()
        return binding.root
    }

    private fun getUserInfo() {
        val userInfo = activity?.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        binding.tvMainNick.text = userInfo?.getString("userNick", "")
        binding.tvMainId.text = userInfo?.getString("userId", "")
        binding.tvMainPw.text = userInfo?.getString("userPw", "")
    }
}