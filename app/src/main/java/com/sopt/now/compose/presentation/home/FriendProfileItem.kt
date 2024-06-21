package com.sopt.now.compose.presentation.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun FriendProfileItem(friendImg: ImageVector, friendName: String, friendDescription: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = friendImg,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = friendName,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Spacer(modifier = Modifier.width(30.dp))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = friendDescription,
            fontSize = 10.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}