package com.sopt.now.compose.presentation.auth.signin

import android.content.Context
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
import com.sopt.now.compose.MainActivity
import com.sopt.now.compose.PreferenceUtil
import com.sopt.now.compose.R
import com.sopt.now.compose.component.CustomTextField
import com.sopt.now.compose.data.remote.dto.request.RequestSigninDto
import com.sopt.now.compose.presentation.auth.signup.SignUpActivity
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class SigninActivity : ComponentActivity() {
    private val viewModel by viewModels<SigninViewModel>()
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
                    val context = LocalContext.current

                    SigninScreen(
                        id,
                        password,
                        onIdChange = { id = it },
                        onPasswordChange = { password = it },
                        context
                    )
                }
            }
        }
        initObserver()
    }

    private fun initObserver() {
        viewModel.signinState.observe(this) { signinState ->
            Toast.makeText(
                this@SigninActivity,
                signinState.message,
                Toast.LENGTH_SHORT
            ).show()

            if (signinState.isSuccess) {
                intent = Intent(this@SigninActivity, MainActivity::class.java)
                intent.putExtra("userId", viewModel.userId)
                startActivity(intent)
            }
        }
    }

    private fun getSigninRequestDto(
        id: String,
        password: String
    ): RequestSigninDto {
        return RequestSigninDto(
            authenticationId = id,
            password = password
        )
    }

    @Composable
    fun SigninScreen(
        id: String,
        password: String,
        onIdChange: (String) -> Unit,
        onPasswordChange: (String) -> Unit,
        context: Context
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.text_signin_title),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.text_id)
            )
            CustomTextField(
                value = id,
                onValueChange = onIdChange,
                placeholderRes = R.string.tf_signin_id,
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "User Icon") }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = stringResource(R.string.text_password))
            CustomTextField(
                value = password,
                onValueChange = onPasswordChange,
                placeholderRes = R.string.tf_signin_password,
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Lock Icon") },
                isPassword = true
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.signin(getSigninRequestDto(id, password))
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(stringResource(R.string.btn_signin))
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
    fun SigninPreview() {
        NOWSOPTAndroidTheme {
            SigninScreen("아이디", "비밀번호", onIdChange = {}, onPasswordChange = {}, LocalContext.current)
        }
    }

    companion object {
        lateinit var prefs: PreferenceUtil
    }
}