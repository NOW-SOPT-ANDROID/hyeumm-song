package com.sopt.now

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivitySignUpBinding

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
            Toast.makeText(
                this@SignUpActivity,
                signUpState.message,
                Toast.LENGTH_SHORT
            ).show()

            if (signUpState.isSuccess) {
                finish()
            }
        }
    }


    private fun getSignUpRequestDto(): RequestSignUpDto {
        val id = binding.etvSignUpId.text.toString()
        val password = binding.etvSignUpPw.text.toString()
        val nickname = binding.etvSignUpNick.text.toString()
        val phoneNumber = binding.etvSignUpPhone.text.toString()
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phoneNumber
        )
    }
}