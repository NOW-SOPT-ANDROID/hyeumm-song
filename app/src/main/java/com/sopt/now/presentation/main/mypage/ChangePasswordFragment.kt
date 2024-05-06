package com.sopt.now.presentation.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMyPageBinding

class ChangePasswordFragment : Fragment() {
    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding) { "_binding이 null이 아닌 경우만 _binding 반환" }
    private var _binding: FragmentMyPageBinding? = null
    private val viewModel by viewModels<ChangePasswordViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        initViews()
        initObserver()
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObserver() {
        viewModel.liveData.observe(viewLifecycleOwner) { userInfoState ->
            Toast.makeText(
                activity,
                userInfoState.message,
                Toast.LENGTH_SHORT,
            ).show()
            if (userInfoState.isSuccess) {
                binding.tvMainNick.text=userInfoState.userNick
                binding.tvMainId.text=userInfoState.userId
                binding.tvMainPhone.text=userInfoState.userPhone
            }
        }
    }

    private fun initViews() {
        viewModel.userInfo()
    }
}
