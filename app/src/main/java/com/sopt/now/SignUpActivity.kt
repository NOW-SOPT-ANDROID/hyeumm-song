package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //사용자 정보 가져오기
        getUserInfo()
    }
    private fun getUserInfo() {
        binding.btnSignIn.setOnClickListener {
            val id = binding.etvSignInId.text.toString()
            val pw = binding.etvSignInPw.text.toString()
            val nick = binding.etvSignInNick.text.toString()
            val etc = binding.etvSignInEtc.text.toString()

            sendUserInfo(id,pw,nick,etc)
        }
    }
    private fun sendUserInfo(id:String,pw:String,nick:String,etc:String){
        if(isSignUpAvailable(id,pw,nick,etc)) {
            val intent= Intent(this,LoginActivity::class.java)
            //로그인 액티비티로 데이터를 보냄
            intent.putExtra("id",id).putExtra("pw",pw).putExtra("nick",nick)
            setResult(RESULT_OK,intent)
            finish()
        }
    }
    //회원가입 가능한지 검사
    private fun isSignUpAvailable(id:String,pw:String,nick:String,etc:String):Boolean {
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
        //회원가입 조건 상수화
        const val MIN_ID_LENGTH = 6
        const val MAX_ID_LENGTH = 10
        const val MIN_PW_LENGTH = 8
        const val MAX_PW_LENGTH = 12
    }
}