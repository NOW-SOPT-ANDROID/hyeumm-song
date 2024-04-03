package com.sopt.now.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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

class LoginActivity : ComponentActivity() {
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
fun LoginUI() {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Welcome to SOPT")
        Text(text = "ID")
        TextField(
            value = id,
            onValueChange = {id = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            placeholder = {Text("아이디를 입력하세요.")},
            singleLine = true,
            leadingIcon = { Icon(Icons.Filled.Person,contentDescription = "User Icon") }
            )
        Text(text = "비밀번호")
        TextField(
            value = pw,
            onValueChange = {id = pw},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            placeholder = {Text("비밀번호를 입력하세요.")},
            singleLine = true,
            leadingIcon = { Icon(Icons.Filled.Person,contentDescription = "User Icon") },
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("로그인")
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("회원가입")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    NOWSOPTAndroidTheme {
        LoginUI()
    }
}