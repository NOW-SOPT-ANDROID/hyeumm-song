package com.sopt.now.presentation.auth.changepassword

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.PreferenceUtil
import com.sopt.now.data.remote.dto.request.RequestChagePasswordDto
import com.sopt.now.databinding.ActivityChangePasswordBinding
import com.sopt.now.presentation.auth.login.LoginActivity

class ChangePasswordActivity : AppCompatActivity() {
    private val binding by lazy { ActivityChangePasswordBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<ChangePasswordViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        prefs = PreferenceUtil(applicationContext)
        initViews()
        initObserver()
    }

    private fun initViews() {
        binding.tvChangePassword.setOnClickListener {
            viewModel.changePassword(getChangePasswordRequestDto())
        }
    }

    private fun initObserver() {
        viewModel.liveData.observe(this) { loginState ->
            Toast.makeText(
                this@ChangePasswordActivity,
                loginState.message,
                Toast.LENGTH_SHORT,
            ).show()
            if (loginState.isSuccess) {
                Intent(this, LoginActivity::class.java).apply {
                    startActivity(this)
                }
                finish()
            }
        }
    }

    private fun getChangePasswordRequestDto(): RequestChagePasswordDto {
        val oldPassword = binding.etvOldPassword.text.toString()
        val newPassword = binding.etvNewPassword.text.toString()
        val checkPassword = binding.etvNewPasswordCheck.text.toString()
        return RequestChagePasswordDto(
            previousPassword = oldPassword,
            newPassword = newPassword,
            newPasswordVerification = checkPassword
        )
    }

    companion object {
        lateinit var prefs: PreferenceUtil
    }
}