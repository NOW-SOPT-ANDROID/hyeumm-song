package com.sopt.now.compose.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.R
import com.sopt.now.compose.feature.home.HomeRoute
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen(
        modifier: Modifier = Modifier
    ) {
        val navController = rememberNavController() //Jetpack Compose를 사용할 때 NavController를 만들려면 rememberNavController()를 호출합니다.
        val items = listOf(
            BottomNavigationItem(
                icon = Icons.Filled.Home,
                label = "Home",
                route = "home"
            ),
            BottomNavigationItem(
                icon = Icons.Filled.Search,
                label = "Search",
                route = "search"
            ),
            BottomNavigationItem(
                icon = Icons.Filled.Person,
                label = "Profile",
                route = "profile"
            )
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
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
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                NavigationBar {
                    items.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            },
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",//이 두개는 필수이다.
                modifier = modifier.padding(innerPadding)
            ) {
                composable("home") {
                    HomeRoute()
                    //이 부분 원래 HomeScreen(HomeviewModel)이었는데 이렇게 하면 처음에만 데이터가 있는 뷰모델을 넘겨주고 아니면 데이터가 없는 애들을 넘겨줘서 다른 화면으로 갔다오면 리스트에 아무것도 존재하지 않음.
                }
                composable("search") {
                    Text(text = "Search")
                }
                composable("profile") {
                    Text(text = "Profile")
                    //ProfileScreen()
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
