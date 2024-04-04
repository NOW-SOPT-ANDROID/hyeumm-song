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
        binding.signupBtn.setOnClickListener {
            movetoSignUp()
        }
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
        binding.loginBtn.setOnClickListener {
            sendData(id,pw)
        }
    }
    private fun movetoSignUp(){
        val intent = Intent(this, SignUpActivity::class.java)
        //회원가입 데이터를 받아오기 위해 startActivity가 아닌 resultLauncher사용
        resultLauncher.launch(intent)
    }
    private fun login(id: String, pw: String) :Boolean {
        var loginBool = false
        val userId = binding.getID.text.toString()
        val userPw = binding.getPw.text.toString()
        val message = when{
            userId != id || userPw != pw -> "아이디 혹은 비밀번호가 일치하지 않습니다."
            else -> {
                loginBool = true
                "로그인에 성공했습니다."
            }
        }
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        return loginBool
    }
    private fun sendData(id:String,pw:String){
        if (login(id, pw)) {
            val intent = Intent(this, MainActivity::class.java)
            //메인 액티비티로 데이터를 보냄
            intent.putExtra("id", id).putExtra("pw", pw).putExtra("nick", nick)
            startActivity(intent)
        }
    }
}