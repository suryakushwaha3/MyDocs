package com.example.mydocs.Settingsmanagment


import androidx.compose.foundation.background
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
fun PrivacyPolicyScreen(
    onBackClick: () -> Unit = {}
) {
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
                    text = "Privacy Policy",
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
            // LAST UPDATED HEADER CARD
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 1.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF3B59FF).copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Security,
                                contentDescription = null,
                                tint = Color(0xFF3B59FF),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text(
                                text = "Effective Date: September 10, 2026",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF1E1E24)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Your data privacy is our highest priority",
                                fontSize = 11.5.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            // SECTION 1: INFORMATION WE COLLECT
            item {
                Text(
                    text = "1. INFORMATION WE COLLECT",
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
                        PolicyContentRow(
                            icon = Icons.Default.Description,
                            title = "Document Content",
                            description = "Files and scans you store remain encrypted locally and on your chosen cloud storage. We do not inspect your personal documents."
                        )
                        PolicyDivider()
                        PolicyContentRow(
                            icon = Icons.Default.Analytics,
                            title = "Usage & Diagnostics",
                            description = "Anonymous crash reports and performance metrics are collected solely to improve app stability and resolve runtime bugs."
                        )
                    }
                }
            }

            // SECTION 2: HOW WE USE YOUR DATA
            item {
                Text(
                    text = "2. HOW WE USE YOUR DATA",
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
                    Column(modifier = Modifier.fillMaxWidth()) {
                        PolicyContentRow(
                            icon = Icons.Default.Sync,
                            title = "Cloud Synchronization",
                            description = "Data transmission occurs strictly between your device and your personal linked cloud drive using secure SSL/TLS protocols."
                        )
                        PolicyDivider()
                        PolicyContentRow(
                            icon = Icons.Default.Lock,
                            title = "Security Enforcement",
                            description = "Biometric tokens and master PIN data are managed securely inside your device's hardware-backed keystore."
                        )
                    }
                }
            }

            // SECTION 3: YOUR RIGHTS & CONTROLS
            item {
                Text(
                    text = "3. YOUR RIGHTS & CONTROLS",
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
                    Column(modifier = Modifier.fillMaxWidth()) {
                        PolicyContentRow(
                            icon = Icons.Default.DeleteForever,
                            title = "Data Deletion",
                            description = "You retain full authority to wipe local caches, clear trash bins, or permanently delete your account and associated backups at any time."
                        )
                        PolicyDivider()
                        PolicyContentRow(
                            icon = Icons.Default.SupportAgent,
                            title = "Privacy Inquiries",
                            description = "For questions, concerns, or data requests, you can reach out directly via our support channel in the About section."
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun PolicyContentRow(
    icon: ImageVector,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.Top
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
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1E1E24)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color.Gray,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun PolicyDivider() {
    HorizontalDivider(
        color = Color.LightGray.copy(alpha = 0.2f),
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}