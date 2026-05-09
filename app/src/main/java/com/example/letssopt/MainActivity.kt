package com.example.letssopt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.letssopt.navigation.BottomNavItem
import com.example.letssopt.screen.home.HomeScreen
import com.example.letssopt.screen.library.LibraryScreen
import com.example.letssopt.ui.theme.LETSSOPTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefManager = PreferenceManager(this)

        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                val rootNavController = rememberNavController()

                NavHost(
                    navController = rootNavController,
                    startDestination = if (prefManager.isLoggedIn()) "main" else "login"
                ){
                    composable("login"){
                        SignInScreen(
                            prefManager = prefManager,
                            onNavigateToSignUp = {
                                rootNavController.navigate("signup")
                            },
                            onSignInSuccess =  {
                                prefManager.setLoggedIn(true)
                                rootNavController.navigate("main"){
                                    popUpTo("login"){ inclusive = true }
                                }
                            }
                        )
                    }
                    composable("signup"){
                        SignUpScreen(
                            onSignUpSuccess = {
                                rootNavController.popBackStack()
                            }
                        )
                    }
                    composable ("main"){
                        MainScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen( modifier: Modifier = Modifier ) {
    val bottomNavController = rememberNavController()

    Scaffold(
        modifier = modifier,
        containerColor = Color(0xFF141414),
        bottomBar = {
            BottomNavigationBar(bottomNavController)
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Main.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Main.route) {
                HomeScreen()
            }
            composable(BottomNavItem.Purchase.route) { TextScreen("개별구매 화면") }
            composable(BottomNavItem.Webtoon.route) { TextScreen("웹툰 화면") }
            composable(BottomNavItem.Search.route) { TextScreen("찾기 화면") }
            composable(BottomNavItem.Library.route) { LibraryScreen() }
        }
    }

}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Main,
        BottomNavItem.Purchase,
        BottomNavItem.Webtoon,
        BottomNavItem.Search,
        BottomNavItem.Library
    )

    NavigationBar(
        containerColor = Color(0xFF141414)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painter = painterResource(id = item.iconRes), contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun TextScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
    }
}