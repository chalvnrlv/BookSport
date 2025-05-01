package com.example.booksport.model.data

import com.example.booksport.model.SportType
import com.example.booksport.model.Venue

object VenueData {
    val venues = listOf(
        Venue(
            id = 1,
            name = "Zuper Badminton Hall",
            location = "Jl. Kejawan Putih Tambak No.89, Surabaya",
            city = "Surabaya",
            imageUrl = "zuper",
            rating = 4.9f,
            pricePerHour = 50000.0,
            sportType = SportType.BADMINTON
        ),
        Venue(
            id = 2,
            name = "Fiva Futsal",
            location = "Jl. Bumi Marina Emas Barat I/15, Surabaya",
            city = "Surabaya",
            imageUrl = "fiva",
            rating = 4.7f,
            pricePerHour = 60000.0,
            sportType = SportType.FUTSAL
        ),
        Venue(
            id = 3,
            name = "Mayasi Basketball Court",
            location = "Jl. Kenjeran No.546, Surabaya",
            city = "Surabaya",
            imageUrl = "mayasi",
            rating = 4.8f,
            pricePerHour = 70000.0,
            sportType = SportType.BASKETBALL
        ),
        Venue(
            id = 4,
            name = "Strike Pool",
            location = "Jl. Raya Dharma Husada Indah AA20 \n No.106, Surabaya ",
            city = "Surabaya",
            imageUrl = "strike",
            rating = 4.8f,
            pricePerHour = 70000.0,
            sportType = SportType.BILLIARD
        )
    )
}