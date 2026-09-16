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
fun TermsScreen(
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
                    text = "Terms of Service",
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
            // EFFECTIVE DATE HEADER CARD
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
                                imageVector = Icons.Default.Gavel,
                                contentDescription = null,
                                tint = Color(0xFF3B59FF),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text(
                                text = "Last Updated: September 10, 2026",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF1E1E24)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Please read these terms carefully before using MyDocs",
                                fontSize = 11.5.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            // SECTION 1: ACCEPTANCE OF TERMS
            item {
                Text(
                    text = "1. ACCEPTANCE OF TERMS",
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
                        TermsContentRow(
                            icon = Icons.Default.CheckCircle,
                            title = "Agreement to Rules",
                            description = "By downloading, installing, or accessing MyDocs, you agree to be bound by these Terms of Service and all applicable local laws."
                        )
                        TermsDivider()
                        TermsContentRow(
                            icon = Icons.Default.Update,
                            title = "Modifications to Terms",
                            description = "We reserve the right to modify these terms at any time. Continued use of the app following updates signifies your acceptance of the revised terms."
                        )
                    }
                }
            }

            // SECTION 2: USE & CONDUCT
            item {
                Text(
                    text = "2. USER CONDUCT & RESPONSIBILITIES",
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
                        TermsContentRow(
                            icon = Icons.Default.Security,
                            title = "Account Security",
                            description = "You are solely responsible for maintaining the confidentiality of your master password, device PIN, and biometric authentication tokens."
                        )
                        TermsDivider()
                        TermsContentRow(
                            icon = Icons.Default.Block,
                            title = "Prohibited Activities",
                            description = "You agree not to reverse engineer, decompile, or misuse the app infrastructure, nor store illegal or unauthorized content."
                        )
                    }
                }
            }

            // SECTION 3: INTELLECTUAL PROPERTY & LIABILITY
            item {
                Text(
                    text = "3. INTELLECTUAL PROPERTY & LIABILITY",
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
                        TermsContentRow(
                            icon = Icons.Default.Copyright,
                            title = "App Ownership",
                            description = "All source code, UI designs, graphics, trademarks, and logos associated with MyDocs are the exclusive property of the developers."
                        )
                        TermsDivider()
                        TermsContentRow(
                            icon = Icons.Default.Warning,
                            title = "Limitation of Liability",
                            description = "MyDocs is provided on an 'as-is' basis without warranties of any kind. We are not liable for any data loss resulting from device failure."
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun TermsContentRow(
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
fun TermsDivider() {
    HorizontalDivider(
        color = Color.LightGray.copy(alpha = 0.2f),
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}