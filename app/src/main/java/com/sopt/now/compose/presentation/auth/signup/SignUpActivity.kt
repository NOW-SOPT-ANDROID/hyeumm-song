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
                    var pw by remember { mutableStateOf("") }
                    var nick by remember { mutableStateOf("") }
                    var phone by remember { mutableStateOf("") }

                    SignUpScreen(
                        id,
                        pw,
                        nick,
                        phone,
                        onIdChange = { id = it },
                        onPwChange = { pw = it },
                        onNickChange = { nick = it },
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
        pw: String,
        nick: String,
        phone: String
    ): RequestSignUpDto {
        return RequestSignUpDto(
            authenticationId = id,
            password = pw,
            nickname = nick,
            phone = phone
        )
    }

    @Composable
    fun SignUpScreen(
        id: String,
        pw: String,
        nick: String,
        phone: String,
        onIdChange: (String) -> Unit,
        onPwChange: (String) -> Unit,
        onNickChange: (String) -> Unit,
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
                text = stringResource(R.string.text_pw),
                fontSize = 20.sp
            )
            TextField(
                value = pw,
                label = { Text(stringResource(R.string.tf_label_pw)) },
                onValueChange = onPwChange,
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
                onValueChange = onNickChange,
                placeholder = { Text(stringResource(R.string.tf_ph_nick)) },
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
                    viewModel.signUp(getSignUpRequestDto(id, pw, nick, phone))
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
                pw = "비밀번호",
                nick = "닉네임",
                phone = "전화번호",
                onIdChange = {},
                onPwChange = {},
                onNickChange = {},
                onPhoneChange = {}
            )
        }
    }
}