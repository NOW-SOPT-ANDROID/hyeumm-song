package com.sopt.now.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileUi(userId: String, userPw: String, userNick: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.cute),
            contentDescription = "귀여운 뱁새"
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "ID",
            fontSize = 20.sp
        )
        Text(
            text = userId,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = stringResource(R.string.text_pw),
            fontSize = 20.sp
        )
        Text(
            text = userPw,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = stringResource(R.string.text_nick),
            fontSize = 20.sp
        )
        Text(
            text = userNick,
            fontSize = 15.sp
        )
    }
}