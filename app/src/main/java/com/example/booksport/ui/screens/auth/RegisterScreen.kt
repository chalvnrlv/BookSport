package com.example.booksport.ui.screens.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.booksport.R
import com.example.booksport.data.AuthData

@Composable
fun RegisterScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCFCFC))
            {
                Column(
                    modifier = Modifier
                        .width(384.dp)
                        .height(317.dp)
                        .background(
                            color = Color(0x80000000),
                            shape = RoundedCornerShape(25.dp)
                        )
                        .padding(horizontal = 26.dp, vertical = 24.dp)
                        .align(Alignment.Center),
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomTextField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = "Name"
                    )

                    CustomTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = "Email"
                    )

                    CustomTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "Password",
                        isPassword = true
                    )

                    Button(
                        onClick = {
                            when {
                                name.isEmpty() || email.isEmpty() || password.isEmpty() -> {
                                    showToast(context, "Please fill all fields")
                                }
                                AuthData.users.any { it.email == email } -> {
                                    showToast(context, "Email already registered")
                                }
                                else -> {
                                    AuthData.users.add(User(email, password, name))
                                    navController.popBackStack()
                                    showToast(context, "Registration successful!")
                                }
                            }
                        },
                        modifier = Modifier
                            .width(174.dp)
                            .height(38.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(15.dp)
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent)
                    ) {
                        Text("Register")
                    }
                }
            }
}