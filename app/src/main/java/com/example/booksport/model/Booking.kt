package com.example.booksport.model

data class Booking(
    val id: Int,
    val user: User,
    val venue: Venue,
    val date: LocalDate,
    val timeSlots: List<LocalTime>,
    val status: BookingStatus
)

enum class BookingStatus { ACTIVE, COMPLETED, CANCELLED }