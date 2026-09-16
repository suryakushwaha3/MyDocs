package com.doxera.app.presentation.auth

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydocs.R
import com.example.mydocs.Security.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onGoogleLoginClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onLoginSuccess: () -> Unit = {}
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(true) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val isLoading = authViewModel.isLoading
    val errorMessage = authViewModel.errorMessage

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bag),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .statusBarsPadding()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(180.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Welcome Back",
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = Color(0xFF1E1E24)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = " 👋", fontSize = 22.sp)
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Sign in to continue to your documents and more.",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )

                if (!errorMessage.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        color = MaterialTheme.colorScheme.errorContainer,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = errorMessage,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            fontSize = 12.5.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(text = "Email Address", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("Enter your email address", color = Color.LightGray, fontSize = 13.sp) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email Icon",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        enabled = !isLoading,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White.copy(alpha = 0.95f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.95f),
                            disabledContainerColor = Color.White.copy(alpha = 0.95f),
                            focusedBorderColor = Color(0xFF3B59FF).copy(alpha = 0.5f),
                            unfocusedBorderColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(text = "Password", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Enter your password", color = Color.LightGray, fontSize = 13.sp) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Lock Icon",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "Toggle Visibility",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        enabled = !isLoading,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White.copy(alpha = 0.95f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.95f),
                            disabledContainerColor = Color.White.copy(alpha = 0.95f),
                            focusedBorderColor = Color(0xFF3B59FF).copy(alpha = 0.5f),
                            unfocusedBorderColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.offset(x = (-10).dp)
                    ) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { rememberMe = it },
                            enabled = !isLoading,
                            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3B59FF))
                        )
                        Text(text = "Remember me", fontSize = 12.5.sp, color = Color(0xFF1E1E24))
                    }

                    TextButton(
                        onClick = onForgotPasswordClick,
                        enabled = !isLoading
                    ) {
                        Text(
                            text = "Forgot Password?",
                            fontSize = 12.5.sp,
                            color = Color(0xFF3B59FF),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                val buttonBrush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF2E5BFF), Color(0xFF8B5CF6))
                )

                Button(
                    onClick = {
                        keyboardController?.hide()
                        authViewModel.login(email, password) {
                            // 🔥 Yahan login hone ke baad email aur name SharedPreferences mein save ho raha hai
                            if (email.isNotBlank()) {
                                val sharedPreferences = context.getSharedPreferences("mydocs_profile_prefs", Context.MODE_PRIVATE)
                                // Email ke '@' se pehle ka part name bana lenge (jaise 'mantoo@gmail.com' se 'mantoo')
                                val extractedName = email.substringBefore("@").replaceFirstChar { it.uppercase() }

                                sharedPreferences.edit()
                                    .putString("user_email", email)
                                    .putString("user_name", extractedName)
                                    .apply()
                            }
                            onLoginSuccess()
                        }
                    },
                    enabled = !isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(buttonBrush),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(text = "Login", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "→", color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray.copy(alpha = 0.4f))
                    Text(
                        text = "  OR  ",
                        fontSize = 11.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray.copy(alpha = 0.4f))
                }

                OutlinedButton(
                    onClick = onGoogleLoginClick,
                    enabled = !isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White.copy(alpha = 0.95f))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "Google Icon",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Continue with Google",
                            color = Color(0xFF1E1E24),
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account?",
                    fontSize = 12.5.sp,
                    color = Color.Gray
                )

                TextButton(
                    onClick = onSignUpClick,
                    enabled = !isLoading
                ) {
                    Text(
                        text = "Sign Up",
                        fontSize = 12.5.sp,
                        color = Color(0xFF3B59FF),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}