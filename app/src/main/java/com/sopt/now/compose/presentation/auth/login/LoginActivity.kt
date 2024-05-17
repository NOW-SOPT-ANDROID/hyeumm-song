package com.sopt.now.compose.presentation.auth.login

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
import com.sopt.now.compose.MainActivity
import com.sopt.now.compose.PreferenceUtil
import com.sopt.now.compose.R
import com.sopt.now.compose.component.CustomTextField
import com.sopt.now.compose.data.remote.dto.request.RequestLoginDto
import com.sopt.now.compose.presentation.auth.signup.SignUpActivity
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = PreferenceUtil(applicationContext)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var id by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

                    LoginScreen(
                        id,
                        password,
                        onIdChange = { id = it },
                        onPasswordChange = { password = it })
                }
            }
        }
        initObserver()
    }

    private fun initObserver() {
        viewModel.liveData.observe(this) { loginState ->
            Toast.makeText(
                this@LoginActivity,
                loginState.message,
                Toast.LENGTH_SHORT
            ).show()

            if (loginState.isSuccess) {
                intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("userId", viewModel.userId)
                startActivity(intent)
            }
        }
    }

    private fun getLoginRequestDto(
        id: String,
        password: String
    ): RequestLoginDto {
        return RequestLoginDto(
            authenticationId = id,
            password = password
        )
    }

    @Composable
    fun LoginScreen(
        id: String,
        password: String,
        onIdChange: (String) -> Unit,
        onPasswordChange: (String) -> Unit
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
            CustomTextField(
                value = id,
                onValueChange = onIdChange,
                placeholderRes = R.string.tf_login_id,
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "User Icon") }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = stringResource(R.string.text_password))
            TextField(
                value = password,
                onValueChange = onPasswordChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                placeholder = { Text(stringResource(R.string.tf_login_password)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Lock Icon") },
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.login(getLoginRequestDto(id, password))
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
            LoginScreen("아이디", "비밀번호", onIdChange = {}, onPasswordChange = {})
        }
    }

    companion object {
        lateinit var prefs: PreferenceUtil
    }
}