package com.sopt.now

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.now.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMyPageBinding.inflate(inflater)
        getUserInfo()
        return binding.root
    }
    private fun getUserInfo() {
        binding.tvMainNick.text = arguments?.getString("nick")
        binding.tvMainId.text = arguments?.getString("id")
        binding.tvMainPw.text = arguments?.getString("pw")
    }
}