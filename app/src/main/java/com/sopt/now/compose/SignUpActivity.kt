package com.sopt.now.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                }
            }
        }
    }
}
@Composable
    fun SignUpUI() {
        var id by remember { mutableStateOf("") }
        var pw by remember { mutableStateOf("") }
        var nick by remember { mutableStateOf("") }
        var etc by remember { mutableStateOf("") }

    Column (
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){
        Text(text = "SIGN UP")
        Text(text = "ID")
            TextField(
                value = id,
                onValueChange = { id = it },
                placeholder = {Text("아이디를 입력하세요.")},
                singleLine = true
            )
            Text(text = "비밀번호")
            TextField(
                value = pw,
                onValueChange = { pw = it },
                placeholder = {Text("비밀번호를 입력하세요.")},
                singleLine = true
            )
            Text(text = "닉네임")
            TextField(
                value = nick,
                onValueChange = { nick = it },
                placeholder = {Text("닉네임을 입력하세요.")},
                singleLine = true
            )
            Text(text = "하고싶은 말")
            TextField(
                value = etc,
                onValueChange = { etc = it },
                placeholder = {Text("하고싶은 말을 입력하세요.")},
                singleLine = true
            )
        Button(
            onClick = { /*TODO*/ },
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