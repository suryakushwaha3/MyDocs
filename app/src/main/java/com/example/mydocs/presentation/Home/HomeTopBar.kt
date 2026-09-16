package com.example.mydocs.presentation.Home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import java.io.File

@Composable
fun HomeTopBar(
    onNotificationClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val context = LocalContext.current

    // Global SharedPreferences se active email nikalo
    val generalPrefs = remember {
        context.getSharedPreferences("mydocs_profile_prefs", Context.MODE_PRIVATE)
    }

    // State jo current active email track karegi
    val currentEmail by remember {
        mutableStateOf(generalPrefs.getString("user_email", "") ?: "")
    }

    // 🔥 User-specific SharedPreferences se exact profile image path fetch karo
    val userPrefsName = "user_prefs_${currentEmail.ifEmpty { "guest" }.replace(Regex("[^A-Za-z0-9]"), "_")}"
    val userSharedPreferences = remember(currentEmail) {
        context.getSharedPreferences(userPrefsName, Context.MODE_PRIVATE)
    }

    val profileImageUri = userSharedPreferences.getString("profile_image_path", null)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // App Title / Logo Section
        Column {
            Text(
                text = "MyDoc",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color(0xFF1E1E24)
            )
            Text(
                text = "Your Documents, Your Space.",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        // Icons Section (Notification & Profile)
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Notification Bell Icon with click
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { onNotificationClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notification",
                    tint = Color(0xFF1E1E24),
                    modifier = Modifier.size(20.dp)
                )
            }

            // Profile Avatar with click & Real DP Support
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF3B59FF).copy(alpha = 0.1f))
                    .clickable { onProfileClick() },
                contentAlignment = Alignment.Center
            ) {
                if (profileImageUri != null && File(profileImageUri).exists()) {
                    AsyncImage(
                        model = File(profileImageUri),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = Color(0xFF3B59FF),
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
    }
}