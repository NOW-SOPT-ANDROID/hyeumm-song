package com.sopt.now.presentation.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.PreferenceUtil
import com.sopt.now.presentation.auth.signup.SignUpActivity
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.data.remote.dto.request.RequestLoginDto

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        prefs = PreferenceUtil(applicationContext)
        initViews()
        initObserver()
        moveToSignUp()
    }

    private fun initViews() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                RequestLoginDto(
                    authenticationId = binding.etvLoginId.text.toString(),
                    password = binding.etvLoginPw.text.toString()
                )
            )
        }
    }

    private fun initObserver() {
        viewModel.liveData.observe(this) { loginState ->
            Toast.makeText(
                this@LoginActivity,
                loginState.message,
                Toast.LENGTH_SHORT,
            ).show()
            if (loginState.isSuccess) {
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

    private fun moveToSignUp() {
        binding.btnLoginSignIn.setOnClickListener {
            Intent(this, SignUpActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    companion object {
        lateinit var prefs: PreferenceUtil
    }
}