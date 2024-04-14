package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //회원가입에서 사용자 정보 받아옴
        getUser()

        //회원가입 페이지로 넘어가기
        moveToSignUp()

    }
    private fun getUser() { // 아쉬운 부분
        var id = ""
        var pw = ""
        var nick = ""
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                id = result.data?.getStringExtra("id") ?: ""
                pw = result.data?.getStringExtra("pw") ?: ""
                nick = result.data?.getStringExtra("nick") ?: ""
            }
        }
        binding.btnLogin.setOnClickListener {
            moveToMain(id,pw,nick)
        }
    }
    private fun moveToSignUp(){
        binding.btnLoginSignIn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            //회원가입 데이터를 받아오기 위해 startActivity가 아닌 resultLauncher사용
            resultLauncher.launch(intent)
        }
    }
    private fun isLoginAvailable(id: String, pw: String) :Boolean {
        var loginBool = false
        val userId = binding.etvLoginId.text.toString()
        val userPw = binding.etvLoginPw.text.toString()
        val message = when{
            userId == "" || userPw == "" -> "모든 항목을 입력해주세요."
            userId != id || userPw != pw -> "아이디 혹은 비밀번호가 일치하지 않습니다."
            else -> {
                loginBool = true
                "로그인에 성공했습니다."
            }
        }
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        return loginBool
    }
    private fun moveToMain(id:String,pw:String,nick:String){
        if (isLoginAvailable(id, pw)) {
            val intent = Intent(this, MainActivity::class.java)
            saveUserInfo(id,pw,nick)
            startActivity(intent)
        }
    }
    private fun saveUserInfo(id:String,pw:String,nick:String) {
    if (isLoginAvailable(id, pw)) {
        val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor
            .putString("userId", id)
            .putString("userPw", pw)
            .putString("userNick", nick)
            .apply()
        }
    }
}
