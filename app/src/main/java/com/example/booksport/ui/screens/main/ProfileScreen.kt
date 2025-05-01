package com.example.booksport.ui.screens.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.booksport.model.data.AuthData

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFFCFCFC))
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ProfileHeader()
            LoyaltyPointsSection()
        }
    }
}

// Fix the userName handling in ProfileHeader
@Composable
private fun ProfileHeader() {
    val fullName = AuthData.currentUser?.name ?: "User"

    Column(
        modifier = Modifier
            .padding(top = 32.dp, bottom = 24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/FDMEmIoeub/j4o2nmdl_expires_30_days.png",
            contentDescription = "Profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(48.dp))
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = fullName,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
private fun LoyaltyPointsSection() {
    Column(
        modifier = Modifier
            .padding(bottom = 282.dp)
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .background(Color(0xFF380C72), shape = RoundedCornerShape(12.dp))
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Loyalty Points",
                    color = Color(0xFFEBE7F1),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "850 Pts",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            AsyncImage(
                model = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/FDMEmIoeub/c01ffmvy_expires_30_days.png",
                contentDescription = "Points icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(71.dp)
            )
        }

        AsyncImage(
            model = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/FDMEmIoeub/l6kyqytg_expires_30_days.png",
            contentDescription = "Progress chart",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(start = 151.dp)
                .clip(RoundedCornerShape(12.dp))
                .size(161.dp)
        )

        Text(
            text = "Earn more points and Enjoy exclusive benefits",
            color = Color(0xFFEBE7F1),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 28.dp)
        )

        ClaimPointsCard()
    }
}

@Composable
private fun ClaimPointsCard() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(1.dp, Color(0x57FFFFFF), RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background(Color(0x40FFFFFF), shape = RoundedCornerShape(8.dp))
            .padding(vertical = 18.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(end = 12.dp)
                .weight(1f)
        ) {
            Text(
                text = "Free 50 Points",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Time Remaining:",
                    color = Color(0xFFEBE7F1),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 6.dp)
                )
                Text(
                    text = "10:45:22",
                    color = Color(0xFFEBE7F1),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        OutlinedButton(
            onClick = { /* Handle claim action */ },
            border = BorderStroke(0.dp, Color.Transparent),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(),
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White, shape = RoundedCornerShape(4.dp))
        ) {
            Text(
                text = "Claim",
                color = Color(0xFF380C72),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 11.dp, horizontal = 24.dp)
            )
        }
    }
}
