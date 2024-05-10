package com.sopt.now.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.presentation.main.home.HomeScreen
import com.sopt.now.compose.presentation.main.home.HomeViewModel
import com.sopt.now.compose.presentation.main.mypage.ProfileScreen
import com.sopt.now.compose.presentation.main.mypage.UserInfoViewModel
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<UserInfoViewModel>()
    private var userId: String = ""
    private var userNick: String = ""
    private var userPhone: String = ""// 변수를 이렇게 여기에 선언해도 괜찮은지...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val userId = intent.getStringExtra("userId").toString()

                    initViews(userId)
                    MainScreen()
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.liveData.observe(this) { userInfoState ->
            Toast.makeText(
                this,
                userInfoState.message,
                Toast.LENGTH_SHORT,
            ).show()
            if (userInfoState.isSuccess) {
                userId = userInfoState.userId ?: ""
                userNick = userInfoState.userNick ?: ""
                userPhone = userInfoState.userPhone ?: ""
            }
        }
    }

    private fun initViews(userId: String) {
        viewModel.userInfo(userId.toInt())
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen() {
        var selectedItem by remember { mutableIntStateOf(0) }
        val items = listOf(
            BottomNavigationItem(
                icon = Icons.Filled.Home,
                label = "Home"
            ),
            BottomNavigationItem(
                icon = Icons.Filled.Search,
                label = "Search"
            ),
            BottomNavigationItem(
                icon = Icons.Filled.Person,
                label = "Profile"
            )
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            text = stringResource(R.string.app_name),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                }
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                when (selectedItem) {
                    0 -> {
                        HomeScreen(HomeViewModel())
                    }

                    1 -> {
                        Text(text = "Search")
                    }

                    2 -> {
                        initObserver()
                        ProfileScreen(userNick, userId, userPhone)
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        NOWSOPTAndroidTheme {
            MainScreen()
        }
    }
}