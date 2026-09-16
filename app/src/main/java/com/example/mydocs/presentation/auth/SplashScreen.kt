package com.example.mydocs.presentation.auth


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydocs.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit = {}
) {
    // Animation effect ke liye alpha (fade-in) state
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "SplashAnimation"
    )

    // 2 seconds delay ke baad next screen par navigate karne ke liye
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000L)
        onSplashFinished()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Background Image (Same as Login/SignUp screens)
        Image(
            painter = painterResource(id = R.drawable.bag),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Center Logo & App Name with Fade-In Animation
        Column(
            modifier = Modifier
                .alpha(alphaAnim.value)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App Logo Box (Gradient Circle with Icon/Text)
            val logoBrush = Brush.horizontalGradient(
                colors = listOf(Color(0xFF2E5BFF), Color(0xFF8B5CF6))
            )
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(logoBrush),
                contentAlignment = Alignment.Center
            ) {
                // Aap yahan apni app ka logo image bhi laga sakte hain: Image(painter = painterResource(id = R.drawable.app_logo), ...)
                Text(
                    text = "MD",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // App Name
            Text(
                text = "MyDocs",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1E24)
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Tagline
            Text(
                text = "Secure your documents effortlessly",
                fontSize = 13.5.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}