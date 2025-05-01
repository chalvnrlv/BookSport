package com.example.booksport.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.booksport.R
import com.example.booksport.model.data.AuthData
import com.example.booksport.model.User


@Composable
fun RegisterScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logres_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = "Register",
            modifier = Modifier
                .width(284.dp)
                .height(129.dp)
                .absoluteOffset(x = 22.dp, y = 149.dp),
            style = TextStyle(
                fontSize = 36.sp,
                lineHeight = 43.2.sp,
                fontFamily = FontFamily(Font(R.font.sora_semibold)),
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .background(Color(0x80000000), shape = RoundedCornerShape(25.dp))
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
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
                    .height(38.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = "Register",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.sora_semibold)),
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    ))
            }
        }
    }
}
