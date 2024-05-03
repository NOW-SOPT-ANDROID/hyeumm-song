package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        initObserver()
        moveToSignUp()
    }

    private fun initViews() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(getLoginRequestDto())
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
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("userId", viewModel.userId)
                startActivity(intent)
            }
        }
    }

    private fun getLoginRequestDto(): RequestLoginDto {
        val id = binding.etvLoginId.text.toString()
        val password = binding.etvLoginPw.text.toString()
        return RequestLoginDto(
            authenticationId = id,
            password = password
        )
    }

    private fun moveToSignUp() {
        binding.btnLoginSignIn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
/**
class LoginActivity : AppCompatActivity() {
private lateinit var binding: ActivityLoginBinding
private lateinit var resultLauncher: ActivityResultLauncher<Intent>
override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
binding = ActivityLoginBinding.inflate(layoutInflater)
setContentView(binding.root)

getUserInfo()

moveToSignUp()
}
private fun getUserInfo() {
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
val intent = Intent(this, MainActivity::class.java).apply {
saveUserInfo(id, pw, nick)
}
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
userId.isBlank() || userPw.isBlank() -> getString(R.string.login_error_blank)
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
 */