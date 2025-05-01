package com.example.booksport.ui.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.booksport.model.Venue

@Composable
fun BookingScreen(
    venue: Venue?,  // Make venue nullable
    navController: NavController
) {
    if (venue != null) {
        // Display booking details for specific venue
        Text("Booking orders for ${venue.name}")
    } else {
        // Display general booking history
        Text("Your Booking History Screen")
    }
}