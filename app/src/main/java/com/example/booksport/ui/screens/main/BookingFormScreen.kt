package com.example.booksport.ui.screens.main

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booksport.R
import com.example.booksport.model.Booking
import com.example.booksport.model.Venue
import com.example.booksport.model.data.AuthData
import com.example.booksport.model.data.BookingData
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingFormScreen(
    venue: Venue,
    navController: NavController
) {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var startHour by remember { mutableIntStateOf(7) }
    var endHour by remember { mutableIntStateOf(8) }

    val hours = endHour - startHour
    val subtotal = hours * venue.pricePerHour
    val tax = subtotal * 0.11
    val total = subtotal + tax

    val calendar = Calendar.getInstance()
    val datePicker = DatePickerDialog(
        context,
        { _, y, m, d ->
            selectedDate = LocalDate.of(y, m + 1, d)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booking Form", style = MaterialTheme.typography.titleSmall) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back))
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Date Picker
            OutlinedButton(
                onClick = { datePicker.show() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(selectedDate?.toString() ?: "Select Date")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Time Selection
            TimeRangePicker(
                start = startHour,
                end = endHour,
                onStartChange = { startHour = it },
                onEndChange = { endHour = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Payment Summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray.copy(alpha = 0.2f)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Payment Summary", style = MaterialTheme.typography.titleMedium)
                    PaymentRow("Hours", "$hours hours")
                    PaymentRow("Price/Hour", "Rp${"%,.0f".format(venue.pricePerHour)}")
                    PaymentRow("Subtotal", "Rp${"%,.0f".format(subtotal)}")
                    PaymentRow("Tax (11%)", "Rp${"%,.0f".format(tax)}")
                    PaymentRow("Total", "Rp${"%,.0f".format(total)}", isTotal = true)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Submit Button
            Button(
                onClick = {
                    if (selectedDate == null) {
                        Toast.makeText(context, "Please select a date", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    BookingData.bookings.add(
                        Booking(
                            id = BookingData.bookings.size + 1,
                            user = AuthData.currentUser!!,
                            venue = venue,
                            date = selectedDate!!,
                            timeSlots = (startHour until endHour).map { LocalTime.of(it, 0) }
                        )
                    )
                    Toast.makeText(context, "Booking confirmed! Total Rp${"%,.0f".format(total)}", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirm Booking")
            }
        }
    }
}

@Composable
private fun TimeRangePicker(
    start: Int,
    end: Int,
    onStartChange: (Int) -> Unit,
    onEndChange: (Int) -> Unit
) {
    Column {
        Text("Select Time Range", style = MaterialTheme.typography.titleSmall)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            TimeDropdown(selected = start, range = 7..22, onSelect = onStartChange)
            Text("to", modifier = Modifier.padding(horizontal = 8.dp))
            TimeDropdown(selected = end, range = (start + 1)..23, onSelect = onEndChange)
        }
    }
}

@Composable
private fun TimeDropdown(
    selected: Int,
    range: IntRange,
    onSelect: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(
            onClick = { expanded = true },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("${selected}:00")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            range.forEach { hour ->
                DropdownMenuItem(
                    text = { Text("$hour:00") },
                    onClick = {
                        onSelect(hour)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun PaymentRow(
    label: String,
    value: String,
    isTotal: Boolean = false
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = if (isTotal) MaterialTheme.typography.titleSmall
            else MaterialTheme.typography.bodyMedium,
            color = if (isTotal) MaterialTheme.colorScheme.primary
            else Color.Unspecified
        )
        Text(
            text = value,
            style = if (isTotal) MaterialTheme.typography.titleSmall
            else MaterialTheme.typography.bodyMedium,
            color = if (isTotal) MaterialTheme.colorScheme.primary
            else Color.Unspecified
        )
    }
}