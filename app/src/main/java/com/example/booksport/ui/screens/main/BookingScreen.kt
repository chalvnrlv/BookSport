package com.example.booksport.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.booksport.R
import com.example.booksport.model.Booking
import com.example.booksport.model.data.BookingData
import java.time.format.DateTimeFormatter

@Composable
fun BookingScreen(navController: NavController) {
    val bookings = BookingData.bookings

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp)
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Your Order",
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.sora_semibold)),
                lineHeight = 28.sp
            )

            Image(
                painter = painterResource(R.drawable.p_profile),
                contentDescription = "Profile",
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Crop
            )
        }

        // Booking Content
        if (bookings.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No bookings yet!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(bookings) { booking ->
                    BookingItem(booking = booking)
                }
            }
        }
    }
}

@Composable
fun BookingItem(booking: Booking) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val hours = booking.timeSlots.size
    val total = hours * booking.venue.pricePerHour * 1.11

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = booking.venue.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Date:", color = Color.Gray)
                Text(booking.date.format(dateFormatter), color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Time:", color = Color.Gray)
                Text(
                    "${booking.timeSlots.first().format(timeFormatter)} - ${
                        booking.timeSlots.last().format(timeFormatter)
                    }",
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Total:", color = Color.Gray)
                Text(
                    "Rp${"%,.0f".format(total)}",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}