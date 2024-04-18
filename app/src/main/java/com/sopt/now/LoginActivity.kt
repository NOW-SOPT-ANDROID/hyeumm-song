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

        getUser()

        moveToSignUp()
    }
    private fun getUser() {
        var id = ""
        var pw = ""
        var nick = ""
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let { data ->
                    id = data.getStringExtra("id") ?: ""
                    pw = data.getStringExtra("pw") ?: ""
                    nick = data.getStringExtra("nick") ?: ""
                }
            }
        }
        binding.btnLogin.setOnClickListener {
            moveToMain(id,pw,nick)
        }
    }
    private fun moveToSignUp(){
        binding.btnLoginSignIn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
    private fun moveToMain(id:String,pw:String,nick:String){
        if (isLoginAvailable(id, pw)) {
            val intent = Intent(this, MainActivity::class.java)
            saveUserInfo(id,pw,nick)
            startActivity(intent)
        }
    }
    private fun saveUserInfo(id:String,pw:String,nick:String) {
        val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor
            .putString("userId", id)
            .putString("userPw", pw)
            .putString("userNick", nick)
            .apply()
    }
    private fun isLoginAvailable(id: String, pw: String) :Boolean {
        var loginBool = false
        val userId = binding.etvLoginId.text.toString()
        val userPw = binding.etvLoginPw.text.toString()
        val message = when{
            userId == "" || userPw == "" -> getString(R.string.login_error_blank)
            userId != id || userPw != pw -> getString(R.string.login_error_different)
            else -> {
                loginBool = true
                getString(R.string.login_success)
            }
        }
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        return loginBool
    }
}
