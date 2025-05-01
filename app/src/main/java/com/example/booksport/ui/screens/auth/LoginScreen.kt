package com.example.booksport.ui.screens.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import com.example.booksport.R
import com.example.booksport.model.data.AuthData

@Composable
fun LoginScreen(navController: NavController) {
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
            text = "The Best App\nto Book Sports \nVenue ",
            modifier = Modifier
                .width(284.dp)
                .height(129.dp)
                .absoluteOffset(x = 22.dp, y = 129.dp),
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
                .fillMaxWidth()
                .background(
                    color = Color(0x80000000),
                    shape = RoundedCornerShape(25.dp)
                )
                .padding(horizontal = 26.dp, vertical = 24.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email",
            )

            CustomTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                isPassword = true
            )

            Button(
                onClick = {
                    if (email.isEmpty() || password.isEmpty()) {
                        showToast(context, "Please fill all fields")
                    } else {
                        val user = AuthData.users.find { it.email == email && it.password == password }
                        if (user != null) {
                            AuthData.currentUser = user
                            navController.navigate("main") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            showToast(context, "Invalid email or password")
                        }
                    }
                },
                modifier = Modifier
                    .width(174.dp)
                    .height(38.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = "Sign In",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.sora_semibold)),
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    ))
            }

            TextButton(onClick = { navController.navigate("register") }) {
                Text(
                    text = "Create an Account",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.sora_semibold)),
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier
            .width(332.dp)
            .height(55.dp)
            .background(Color(0xFFD9D9D9), shape = RoundedCornerShape(20.dp))
    )
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
