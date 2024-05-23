package com.sopt.now.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.sopt.now.BindingFragment
import com.sopt.now.databinding.FragmentMyPageBinding
import com.sopt.now.presentation.auth.changepassword.ChangePasswordActivity

class MyPageFragment : BindingFragment<FragmentMyPageBinding>() {
    private val viewModel by viewModels<UserInfoViewModel>()
    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyPageBinding = FragmentMyPageBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        moveToChangePassword()
    }

    private fun initObserver() {
        viewModel.liveData.observe(viewLifecycleOwner) { userInfoState ->
            Toast.makeText(
                activity,
                userInfoState.message,
                Toast.LENGTH_SHORT,
            ).show()
            if (userInfoState.isSuccess) {
                with(binding) {
                    tvMainNickname.text = userInfoState.userNickname
                    tvMainId.text = userInfoState.userId
                    tvMainPhone.text = userInfoState.userPhone
                }
            }
        }
    }

    private fun moveToChangePassword() {
        binding.tvChangePassword.setOnClickListener {
            Intent(activity, ChangePasswordActivity::class.java).apply {
                startActivity(this)
                activity?.finish()
            }
        }
    }
}
