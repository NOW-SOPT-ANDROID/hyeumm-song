package com.sopt.now.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val userId = intent.getStringExtra("userId")
                    val userPw = intent.getStringExtra("userPw")
                    val userNick = intent.getStringExtra("userNick")
                    MainUI(userId,userPw,userNick)
                }
            }
        }
    }
}
//메인화면
@Composable
fun MainUI(userId: String?="", userPw:String?="", userNick:String?=""){
    Column(
        modifier = Modifier.fillMaxSize().padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "NOW SOPT",
            fontSize = 30.sp
        )
        Text(
            text = "SHOUT OUR PASSION TOGETHER",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(50.dp),)
        Text(
            text = "ID",
            fontSize = 20.sp
        )
        Text(
            text = "$userId",
            fontSize = 15.sp
        )
        Text(
            text = "비밀번호",
            fontSize = 20.sp
        )
        Text(
            text = "$userPw",
            fontSize = 15.sp
        )
        Text(
            text = "닉네임",
            fontSize = 20.sp
        )
        Text(
            text = "$userNick",
            fontSize = 15.sp
        )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NOWSOPTAndroidTheme {
        MainUI()
    }
}