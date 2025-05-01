package com.example.booksport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booksport.model.data.VenueData
import com.example.booksport.ui.screens.auth.LoginScreen
import com.example.booksport.ui.screens.auth.RegisterScreen
import com.example.booksport.ui.screens.main.BookingFormScreen
import com.example.booksport.ui.screens.main.MainScreen

@Composable
fun AppNavigation() {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController = rootNavController)
        }
        composable("register") {
            RegisterScreen(navController = rootNavController)
        }
        composable("main") {
            MainScreen(rootNavController = rootNavController)
        }
        composable("booking/{venueId}") { backStackEntry ->
            val venueId = backStackEntry.arguments?.getString("venueId")?.toIntOrNull()
            val venue = VenueData.venues.find { it.id == venueId }

            if (venue != null) {
                BookingFormScreen(
                    venue = venue,
                    navController = rootNavController
                )
            }
        }
    }
}