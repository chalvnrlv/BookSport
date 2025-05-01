package com.example.booksport.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.booksport.R

val Typography = androidx.compose.material3.Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sora_bold)),
        fontSize = 36.sp,
        lineHeight = 43.2.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sora_regular)),
        fontSize = 16.sp,
        lineHeight = 19.2.sp
    )
)