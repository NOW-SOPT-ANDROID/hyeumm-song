package com.sopt.now.compose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUpUI()
                }
            }
        }
    }
}
//회원가입 화면
@Composable
    fun SignUpUI() {
    val context = LocalContext.current
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var nick by remember { mutableStateOf("") }
    var etc by remember { mutableStateOf("") }

    Column (
            modifier = Modifier.fillMaxSize().padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(
            text = "SIGN UP",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(50.dp),)
        Text(
            text = "ID",
            fontSize = 20.sp)
        TextField(
                value = id,
                label = { Text("6-10자 입력해주세요") },
                onValueChange = { id = it },
                placeholder = {Text("아이디를 입력하세요.")},
                singleLine = true
            )
        Spacer(modifier = Modifier.height(50.dp),)
        Text(
            text = "비밀번호",
            fontSize = 20.sp)
        TextField(
                value = pw,
                label = { Text("8-12자 입력해주세요") },
                onValueChange = { pw = it },
                placeholder = {Text("비밀번호를 입력하세요.")},
                singleLine = true
            )
        Spacer(modifier = Modifier.height(50.dp),)
        Text(
            text = "닉네임",
            fontSize = 20.sp)
        TextField(
                value = nick,
                label = { Text("한 글자 이상 입력해주세요") },
                onValueChange = { nick = it },
                placeholder = {Text("닉네임을 입력하세요.")},
                singleLine = true
            )
        Spacer(modifier = Modifier.height(50.dp),)
        Text(
            text = "하고싶은 말",
            fontSize = 20.sp)
        TextField(
                value = etc,
                label = { Text("한 글자 이상 입력해주세요") },
                onValueChange = { etc = it },
                placeholder = {Text("하고싶은 말을 입력하세요.")},
                singleLine = true
            )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                //회원가입 조건에 맞는지 검사
                val message = when {
                    id.isEmpty() || pw.isEmpty() || nick.isEmpty() || etc.isEmpty() -> "모든 항목을 입력해주세요."
                    id.length !in 6..10 -> "아이디를 다시 설정하세요."
                    pw.length !in 8..12 -> "비밀번호를 다시 설정하세요."
                    nick.isBlank() || nick.length != nick.trim().length -> "닉네임을 다시 설정하세요."
                    etc.length !in 1..Int.MAX_VALUE -> "하고싶은 말을 다시 설정하세요."
                    else -> {
                        val intent = Intent(context,LoginActivity::class.java)
                        intent.putExtra("userId", id).putExtra("userPw", pw).putExtra("userNick", nick)
                        context.startActivity(intent)
                        "회원가입에 성공했습니다."
                    }
                }
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                      },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("회원가입")
        }
        }
    }
@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    NOWSOPTAndroidTheme {
        SignUpUI()
    }
}