package com.example.booksport.model.data

import com.example.booksport.model.Booking
import java.time.LocalDate
import java.time.LocalTime

object BookingData {
    val bookings = mutableListOf<Booking>(
        Booking(
            id = 1,
            user = AuthData.users[0],
            venue = VenueData.venues[0],
            date = LocalDate.of(2025, 5, 5),
            timeSlots = listOf(LocalTime.of(7, 0), LocalTime.of(8, 0)),
        )
    )
}