package com.sopt.now.compose.presentation.auth.signup

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.component.CustomTextField
import com.sopt.now.compose.data.remote.dto.request.RequestSignUpDto
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class SignUpActivity : ComponentActivity() {
    private val viewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var id by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }
                    var nickname by remember { mutableStateOf("") }
                    var phone by remember { mutableStateOf("") }

                    SignUpScreen(
                        id,
                        password,
                        nickname,
                        phone,
                        onIdChange = { id = it },
                        onPasswordChange = { password = it },
                        onNicknameChange = { nickname = it },
                        onPhoneChange = { phone = it })

                    initObserver()
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.liveData.observe(this) { signUpState ->
            Toast.makeText(
                this@SignUpActivity,
                signUpState.message,
                Toast.LENGTH_SHORT
            ).show()

            if (signUpState.isSuccess) {
                finish()
            }
        }
    }

    private fun getSignUpRequestDto(
        id: String,
        password: String,
        nickname: String,
        phone: String
    ): RequestSignUpDto {
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phone
        )
    }

    @Composable
    fun SignUpScreen(
        id: String,
        password: String,
        nickname: String,
        phone: String,
        onIdChange: (String) -> Unit,
        onPasswordChange: (String) -> Unit,
        onNicknameChange: (String) -> Unit,
        onPhoneChange: (String) -> Unit
    ) {
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
                onValueChange = onIdChange,
                placeholder = { Text(stringResource(R.string.tf_ph_id)) },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(R.string.text_password),
                fontSize = 20.sp
            )
            TextField(
                value = password,
                label = { Text(stringResource(R.string.tf_label_password)) },
                onValueChange = onPasswordChange,
                placeholder = { Text(stringResource(R.string.tf_ph_password)) },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(R.string.text_nickname),
                fontSize = 20.sp
            )
            TextField(
                value = nickname,
                label = { Text(stringResource(R.string.tf_label_nickname)) },
                onValueChange = onNicknameChange,
                placeholder = { Text(stringResource(R.string.tf_ph_nickname)) },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(R.string.text_phone),
                fontSize = 20.sp
            )
            TextField(
                value = phone,
                label = { Text(stringResource(R.string.tf_label_phone)) },
                onValueChange = onPhoneChange,
                placeholder = { Text(stringResource(R.string.tf_ph_phone)) },
                singleLine = true
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.signUp(getSignUpRequestDto(id, password, nickname, phone))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.btn_sign_up))
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun SignUpPreview() {
        NOWSOPTAndroidTheme {
            SignUpScreen(
                id = "아이디",
                password = "비밀번호",
                nickname = "닉네임",
                phone = "전화번호",
                onIdChange = {},
                onPasswordChange = {},
                onNicknameChange = {},
                onPhoneChange = {}
            )
        }
    }
}