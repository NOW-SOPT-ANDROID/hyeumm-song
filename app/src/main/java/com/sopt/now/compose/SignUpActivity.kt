package com.sopt.now.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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

@Composable
fun SignUpUI() {
    val context = LocalContext.current
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var nick by remember { mutableStateOf("") }
    var etc by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SIGN UP",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "ID",
            fontSize = 20.sp
        )
        TextField(
            value = id,
            label = { Text(stringResource(R.string.tf_label_id)) },
            onValueChange = { id = it },
            placeholder = { Text(stringResource(R.string.tf_ph_id)) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = stringResource(R.string.text_pw),
            fontSize = 20.sp
        )
        TextField(
            value = pw,
            label = { Text(stringResource(R.string.tf_label_pw)) },
            onValueChange = { pw = it },
            placeholder = { Text(stringResource(R.string.tf_ph_pw)) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = stringResource(R.string.text_nick),
            fontSize = 20.sp
        )
        TextField(
            value = nick,
            label = { Text(stringResource(R.string.tf_label_nick)) },
            onValueChange = { nick = it },
            placeholder = { Text(stringResource(R.string.tf_ph_nick)) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "하고싶은 말",
            fontSize = 20.sp
        )
        TextField(
            value = etc,
            label = { Text(stringResource(R.string.tf_label_etc)) },
            onValueChange = { etc = it },
            placeholder = { Text(stringResource(R.string.tf_ph_etc)) },
            singleLine = true
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                isSignUpAvailable(context,id,pw,nick,etc)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.btn_sign_up))
        }
    }
}
fun isSignUpAvailable(context: Context,id: String,pw: String,nick: String,etc: String) {
    val message = when {
        id.isEmpty() || pw.isEmpty() || nick.isEmpty() || etc.isEmpty() -> R.string.sign_up_blank_error
        id.length !in 6..10 -> R.string.sign_up_id_error
        pw.length !in 8..12 -> R.string.sign_up_pw_error
        nick.isBlank() || nick.length != nick.trim().length -> R.string.sign_up_nick_error
        etc.length !in 1..Int.MAX_VALUE -> R.string.sign_up_etc_error
        else -> {
            val intent = Intent(context, LoginActivity::class.java).apply{
                putExtra("userId", id)
                putExtra("userPw", pw)
                putExtra("userNick", nick)
            }
            context.startActivity(intent)
            R.string.sign_up_success
        }
    }
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    NOWSOPTAndroidTheme {
        SignUpUI()
    }
}