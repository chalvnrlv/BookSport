package com.example.booksport.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booksport.ui.components.BottomNavigationBar

@Composable
fun MainScreen(rootNavController: NavHostController) {
    val mainNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(mainNavController) }
    ) { innerPadding ->
        NavHost(
            navController = mainNavController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(navController = rootNavController)
            }
            composable("bookings") {
                BookingScreen(
                    venue = null,
                    navController = rootNavController
                )
            }
            composable("profile") {
                ProfileScreen()
            }
        }
    }
}