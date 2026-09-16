package com.example.mydocs.Settingsmanagment


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppearanceScreen(
    onBackClick: () -> Unit = {}
) {
    var selectedThemeMode by remember { mutableStateOf("system") } // "light", "dark", "system"
    var selectedColorCode by remember { mutableStateOf("blue") }   // "blue", "purple", "emerald", "amber"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {
        // Top App Bar
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF1E1E24)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Appearance",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF1E1E24)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            // THEME MODE SECTION
            item {
                Text(
                    text = "THEME MODE",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp, top = 4.dp, bottom = 2.dp)
                )
            }
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 1.dp
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        AppearanceRadioItem(
                            icon = Icons.Default.WbSunny,
                            title = "Light Mode",
                            subtitle = "Clean and bright interface",
                            isSelected = selectedThemeMode == "light",
                            onClick = { selectedThemeMode = "light" }
                        )
                        AppearanceDivider()
                        AppearanceRadioItem(
                            icon = Icons.Default.NightsStay,
                            title = "Dark Mode",
                            subtitle = "Easy on the eyes in low light",
                            isSelected = selectedThemeMode == "dark",
                            onClick = { selectedThemeMode = "dark" }
                        )
                        AppearanceDivider()
                        AppearanceRadioItem(
                            icon = Icons.Default.SettingsSystemDaydream,
                            title = "System Default",
                            subtitle = "Follows your device settings",
                            isSelected = selectedThemeMode == "system",
                            onClick = { selectedThemeMode = "system" }
                        )
                    }
                }
            }

            // ACCENT COLOR SECTION
            item {
                Text(
                    text = "ACCENT COLOR",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp, top = 8.dp, bottom = 2.dp)
                )
            }
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 1.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Choose primary theme color",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF1E1E24)
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            ColorOptionCircle(
                                color = Color(0xFF3B59FF),
                                isSelected = selectedColorCode == "blue",
                                onClick = { selectedColorCode = "blue" }
                            )
                            ColorOptionCircle(
                                color = Color(0xFF8B5CF6),
                                isSelected = selectedColorCode == "purple",
                                onClick = { selectedColorCode = "purple" }
                            )
                            ColorOptionCircle(
                                color = Color(0xFF10B981),
                                isSelected = selectedColorCode == "emerald",
                                onClick = { selectedColorCode = "emerald" }
                            )
                            ColorOptionCircle(
                                color = Color(0xFFF59E0B),
                                isSelected = selectedColorCode == "amber",
                                onClick = { selectedColorCode = "amber" }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppearanceRadioItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF3B59FF).copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFF3B59FF),
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1E1E24)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    fontSize = 11.5.sp,
                    color = Color.Gray
                )
            }
        }
        RadioButton(
            selected = isSelected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF3B59FF)
            )
        )
    }
}

@Composable
fun ColorOptionCircle(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(color)
            .clickable(onClick = onClick)
            .then(
                if (isSelected) {
                    Modifier.border(3.dp, Color(0xFF1E1E24), CircleShape)
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun AppearanceDivider() {
    HorizontalDivider(
        color = Color.LightGray.copy(alpha = 0.2f),
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}