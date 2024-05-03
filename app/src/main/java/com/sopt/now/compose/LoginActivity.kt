package com.sopt.now.compose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var id by remember { mutableStateOf("") }
                    var pw by remember { mutableStateOf("") }

                    LoginScreen(
                        id,
                        pw,
                        onIdChange = { id = it },
                        onPwChange = { pw = it })

                    initObserver()
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.liveData.observe(this) { LoginState ->
            Toast.makeText(
                this@LoginActivity,
                LoginState.message,
                Toast.LENGTH_SHORT
            ).show()

            if (LoginState.isSuccess) {
                intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun getLoginRequestDto(
        id: String,
        pw: String
    ): RequestLoginDto {
        return RequestLoginDto(
            authenticationId = id,
            password = pw
        )
    }

    @Composable
    fun LoginScreen(
        id: String,
        pw: String,
        onIdChange: (String) -> Unit,
        onPwChange: (String) -> Unit
    ) {
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.text_login_title),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.text_id)
            )
            TextField(
                value = id,
                onValueChange = onIdChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                placeholder = { Text(stringResource(R.string.tf_login_id)) },
                singleLine = true,
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "User Icon") }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = stringResource(R.string.text_pw))
            TextField(
                value = pw,
                onValueChange = onPwChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                placeholder = { Text(stringResource(R.string.tf_login_pw)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Lock Icon") },
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.login(getLoginRequestDto(id, pw))
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(stringResource(R.string.btn_login))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    val intent = Intent(context, SignUpActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(stringResource(R.string.btn_sign_up))
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun LoginPreview() {
        NOWSOPTAndroidTheme {
            LoginScreen("아이디", "비밀번호", onIdChange = {}, onPwChange = {})
        }
    }
}