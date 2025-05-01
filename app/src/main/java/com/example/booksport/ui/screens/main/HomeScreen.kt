package com.example.booksport.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.booksport.R
import com.example.booksport.model.SportType
import com.example.booksport.model.Venue
import com.example.booksport.model.data.AuthData
import com.example.booksport.model.data.VenueData

@Composable
fun HomeScreen(navController: NavController) {
    val userName = AuthData.currentUser?.name?.split(" ")?.first() ?: "User"
    val cities = listOf("Surabaya", "Jakarta", "Bandung", "ALL")
    val sportTypes = listOf("ALL") + SportType.entries.map { it.name }
    var selectedCity by remember { mutableStateOf("Surabaya") }
    var selectedSport by remember { mutableStateOf("ALL") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Banner Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.banner),
                contentDescription = "Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(264.dp)
                    .offset( y = -(24).dp)
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            )

            // Profile Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp)
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Good Morning,\n$userName",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.sora_semibold)),
                    lineHeight = 28.sp
                )

                Image(
                    painter = painterResource(R.drawable.p_profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(60.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        // Filter Controls
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilterDropdown(
                items = cities,
                selectedItem = selectedCity,
                onItemSelected = { selectedCity = it },
                modifier = Modifier.weight(1f),
                leadingIcon = Icons.Default.ArrowDropDown
            )

            Spacer(modifier = Modifier.width(16.dp))

            FilterDropdown(
                items = sportTypes,
                selectedItem = selectedSport,
                onItemSelected = { selectedSport = it },
                modifier = Modifier.weight(1f),
                leadingIcon = Icons.Default.ArrowDropDown
            )
        }

        // Venues List
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = VenueData.venues.filter { venue ->
                    (venue.city == selectedCity || selectedCity == "ALL") &&
                            (venue.sportType.name == selectedSport || selectedSport == "ALL")
                }
            ) { venue ->
                VenueItem(
                    venue = venue,
                    onClick = { navController.navigate("booking/${venue.id}") }
                )
            }
        }
    }
}

@Composable
private fun FilterDropdown(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedButton(
            onClick = { expanded = true },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF380C72)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = Color(0xFF380C72),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = selectedItem,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.sora_semibold))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    tint = Color(0xFF380C72),
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item,
                            fontFamily = FontFamily(Font(R.font.sora_regular))
                        )
                    },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun VenueItem(
    venue: Venue,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(
                    when (venue.imageUrl) {
                        "zuper" -> R.drawable.v_zuper
                        "fiva" -> R.drawable.v_fiva
                        "mayasi" -> R.drawable.v_mayasi
                        "strike" -> R.drawable.v_strike
                        else -> R.drawable.banner
                    }
                ),
                contentDescription = venue.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = venue.name,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.sora_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF380C72)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = " ${venue.rating}",
                            fontSize = 14.sp,
                            color = Color.Gray)
                    }

                    Text(
                        text = venue.location,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Rp${"%,.0f".format(venue.pricePerHour)}/hour",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF2E7D32)
                )
            }
        }
    }
}