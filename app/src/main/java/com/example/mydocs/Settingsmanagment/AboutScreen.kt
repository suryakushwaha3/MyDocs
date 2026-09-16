package com.example.mydocs.Settingsmanagment

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mydocs.ViewModel.DocumentViewModel

@Composable
fun AboutScreen(
    viewModel: DocumentViewModel = viewModel(),
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    // Room database se live documents collect karna
    val documents by viewModel.allDocuments.collectAsState()
    val totalDocuments = documents.size

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
                    text = "About MyDocs",
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
            // APP LOGO & ROOM DATABASE LIVE STATS HEADER
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
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFF3B59FF).copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Storage,
                                contentDescription = null,
                                tint = Color(0xFF3B59FF),
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "MyDocs - Local SQLite Engine",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E1E24),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Version 2.4.0 • Local Files Stored: $totalDocuments",
                            fontSize = 12.sp,
                            color = Color(0xFF3B59FF),
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // DATABASE MANAGEMENT SECTION (SQLite Mastery Tools)
            item {
                Text(
                    text = "LOCAL DATABASE STORAGE",
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
                        AboutClickableItem(
                            icon = Icons.Default.Backup,
                            title = "Export SQLite Database",
                            subtitle = "Backup local .db file to device storage",
                            onClick = {
                                Toast.makeText(context, "Database path: Internal Room DB active", Toast.LENGTH_SHORT).show()
                            }
                        )
                        AboutDivider()
                        AboutClickableItem(
                            icon = Icons.Default.CleaningServices,
                            title = "Clear Local Cache",
                            subtitle = "Free up device space by cleaning temp files",
                            onClick = {
                                Toast.makeText(context, "Cache cleared successfully!", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }

            // APP INFORMATION SECTION
            item {
                Text(
                    text = "INFORMATION",
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
                        AboutClickableItem(
                            icon = Icons.Default.NewReleases,
                            title = "What's New",
                            subtitle = "Check out latest features and improvements",
                            onClick = { Toast.makeText(context, "You are on the latest version", Toast.LENGTH_SHORT).show() }
                        )
                        AboutDivider()
                        AboutClickableItem(
                            icon = Icons.Default.RateReview,
                            title = "Rate on Play Store",
                            subtitle = "Support us by leaving a review",
                            onClick = { Toast.makeText(context, "Redirecting to Play Store...", Toast.LENGTH_SHORT).show() }
                        )
                        AboutDivider()
                        AboutClickableItem(
                            icon = Icons.Default.Share,
                            title = "Share App",
                            subtitle = "Recommend MyDocs to your friends",
                            onClick = { Toast.makeText(context, "Share intent triggered", Toast.LENGTH_SHORT).show() }
                        )
                    }
                }
            }

            // LEGAL & POLICIES SECTION
            item {
                Text(
                    text = "LEGAL & POLICIES",
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
                        AboutClickableItem(
                            icon = Icons.Default.Gavel,
                            title = "Terms of Service",
                            subtitle = "Read our user agreement and conditions",
                            onClick = {}
                        )
                        AboutDivider()
                        AboutClickableItem(
                            icon = Icons.Default.PrivacyTip,
                            title = "Privacy Policy",
                            subtitle = "Your data stays 100% secure on your device",
                            onClick = {}
                        )
                        AboutDivider()
                        AboutClickableItem(
                            icon = Icons.Default.Code,
                            title = "Open Source Licenses",
                            subtitle = "Room, SQLite, Jetpack Compose acknowledgments",
                            onClick = {}
                        )
                    }
                }
            }

            // SUPPORT & COMMUNITY
            item {
                Text(
                    text = "SUPPORT & COMMUNITY",
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
                        AboutClickableItem(
                            icon = Icons.Default.SupportAgent,
                            title = "Contact Support",
                            subtitle = "Get help with bugs or feedback",
                            onClick = {}
                        )
                        AboutDivider()
                        AboutClickableItem(
                            icon = Icons.Default.Language,
                            title = "Visit Official Website",
                            subtitle = "www.mydocsapp.com",
                            onClick = {}
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun AboutClickableItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
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
                modifier = Modifier,
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
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun AboutDivider() {
    HorizontalDivider(
        color = Color.LightGray.copy(alpha = 0.2f),
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}