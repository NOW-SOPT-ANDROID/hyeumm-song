package com.sopt.now.presentation.auth.signup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.R
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.data.remote.dto.request.RequestSignUpDto

class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        initObserver()
    }

    private fun initViews() {
        binding.btnSignUp.setOnClickListener {
            viewModel.signUp(getSignUpRequestDto())
        }
    }

    private fun initObserver() {
        viewModel.liveData.observe(this) { signUpState ->
            if (signUpState.isSuccess) {
                Toast.makeText(
                    this@SignUpActivity,
                    getString(R.string.sign_up_success, signUpState.message),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
            else {
                Toast.makeText(
                    this@SignUpActivity,
                    getString(R.string.sign_up_failed, signUpState.message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        val id = binding.etvSignUpId.text.toString()
        val password = binding.etvSignUpPassword.text.toString()
        val nickname = binding.etvSignUpNickname.text.toString()
        val phoneNumber = binding.etvSignUpPhone.text.toString()
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phoneNumber
        )
    }
}