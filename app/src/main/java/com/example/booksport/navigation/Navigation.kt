package com.example.booksport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booksport.ui.screens.auth.LoginScreen
import com.example.booksport.ui.screens.auth.RegisterScreen
import com.example.booksport.ui.screens.main.BookingScreen
import com.example.booksport.ui.screens.main.HomeScreen
import com.example.booksport.ui.screens.main.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // Auth routes
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("main") }
            )
        }
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate("main") }
            )
        }

        // Main app routes with bottom nav
        composable("main") {
            MainScreen(navController)
        }
    }
}