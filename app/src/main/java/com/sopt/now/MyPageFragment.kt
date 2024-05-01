package com.sopt.now

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private val binding: FragmentMyPageBinding
        get()= requireNotNull(_binding){"_binding이 null이 아닌 경우만 _binding 반환"}
    private var _binding: FragmentMyPageBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater,container,false)
        getUserInfo()
        return binding.root
    }

    private fun getUserInfo() {
        val userInfo = activity?.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        binding.tvMainNick.text = userInfo?.getString("userNick", "")
        binding.tvMainId.text = userInfo?.getString("userId", "")
        binding.tvMainPw.text = userInfo?.getString("userPw", "")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}