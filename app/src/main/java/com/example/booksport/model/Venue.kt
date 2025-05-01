package com.example.booksport.model

data class Venue(
    val id: Int,
    val name: String,
    val location: String,
    val city: String,
    val imageUrl: String,
    val rating: Float,
    val pricePerHour: Double,
    val sportType: SportType
)

enum class SportType {
    FUTSAL, BADMINTON, BASKETBALL, MINISOCCER, BILLIARD
}