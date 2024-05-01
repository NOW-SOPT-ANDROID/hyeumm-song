package com.sopt.now

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val authService by lazy { ServicePool.authService }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.btnSignUp.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val signUpRequest = getSignUpRequestDto()
        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseSignUpDto? = response.body()
                    val userId = response.headers()["location"]
                    Toast.makeText(
                        this@SignUpActivity,
                        "회원가입 성공 userID는 $userId 입니다",
                        Toast.LENGTH_SHORT,
                    ).show()
                    Log.d("SignUp", "data: $data, userId: $userId")
                } else {
                    val error = response.message()
                    Toast.makeText(
                        this@SignUpActivity,
                        "회원가입에 실패했습니다.$error",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, "서버 에러 발생 ", Toast.LENGTH_SHORT).show()
            }
        })
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
/**
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserInfo()
    }

    private fun getUserInfo() {
        binding.btnSignIn.setOnClickListener {
            val id = binding.etvSignInId.text.toString()
            val pw = binding.etvSignInPw.text.toString()
            val nick = binding.etvSignInNick.text.toString()
            val etc = binding.etvSignInEtc.text.toString()

            sendUserInfo(id, pw, nick, etc)
        }
    }

    private fun sendUserInfo(id: String, pw: String, nick: String, etc: String) {
        if (isSignUpAvailable(id, pw, nick, etc)) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("id", id).putExtra("pw", pw).putExtra("nick", nick)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun isSignUpAvailable(id: String, pw: String, nick: String, etc: String): Boolean {
        var signUpBool = false
        val message = when {
            id.isEmpty() || pw.isEmpty() || nick.isEmpty() || etc.isEmpty() -> getString(R.string.sign_up_error_blank)
            id.length !in MIN_ID_LENGTH..MAX_ID_LENGTH -> getString(R.string.sign_up_error_id)
            pw.length !in MIN_PW_LENGTH..MAX_PW_LENGTH -> getString(R.string.sign_up_error_pw)
            nick.isBlank() || nick.length != nick.trim().length -> getString(R.string.sign_up_error_nick)
            etc.length !in 1..Int.MAX_VALUE -> getString(R.string.sign_up_error_etc)
            else -> {
                signUpBool = true
                getString(R.string.sign_up_success)
            }
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        return signUpBool
    }

    companion object {
        const val MIN_ID_LENGTH = 6
        const val MAX_ID_LENGTH = 10
        const val MIN_PW_LENGTH = 8
        const val MAX_PW_LENGTH = 12
    }
}
        */