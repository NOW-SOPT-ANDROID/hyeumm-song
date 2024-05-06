package com.sopt.now.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.now.databinding.FragmentMyPageBinding
import com.sopt.now.presentation.auth.changepassword.ChangePasswordActivity

class MyPageFragment : Fragment() {
    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding) { "_binding이 null이 아닌 경우만 _binding 반환" }
    private var _binding: FragmentMyPageBinding? = null
    private val viewModel by viewModels<UserInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        initViews()
        initObserver()
        moveToChangePassword()
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
                with(binding){
                    tvMainNick.text=userInfoState.userNick
                    tvMainId.text=userInfoState.userId
                    tvMainPhone.text=userInfoState.userPhone
                }
            }
        }
    }

    private fun initViews() {
            viewModel.userInfo()
    }

    private fun moveToChangePassword(){
        binding.tvChangePassword.setOnClickListener {
            Intent(activity, ChangePasswordActivity::class.java).apply{
                startActivity(this)
                activity?.finish()
            }
        }
    }
}
