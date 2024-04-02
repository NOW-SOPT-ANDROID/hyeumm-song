package com.sopt.now

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sopt.now.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    //회원가입 가능한지 검사
    private fun SignUp(id:String,pw:String,nick:String,etc:String):Boolean {
        var signUpBool = false
        val message = when {
            id.isEmpty() || pw.isEmpty() || nick.isEmpty() || etc.isEmpty() -> "모든 항목을 입력해주세요."
            id.length !in 6..10 -> "아이디를 다시 설정하세요."
            pw.length !in 8..12 -> "비밀번호를 다시 설정하세요."
            nick.isBlank() || nick.length != nick.trim().length -> "닉네임을 다시 설정하세요."
            etc.length !in 1..Int.MAX_VALUE -> "하고싶은 말을 다시 설정하세요."
            else -> {
                signUpBool = true
                "회원가입에 성공했습니다."
            }
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        return signUpBool
    }
}