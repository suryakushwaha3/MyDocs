package com.example.mydocs.presentation.settings

import android.content.Context
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.example.mydocs.Security.AuthViewModel
import java.io.File

@Composable
fun SettingsScreen(
    authViewModel: AuthViewModel,
    onLogoutSuccess: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onSecurityClick: () -> Unit = {},
    onAppearanceClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {},
    onAccessibilityClick: () -> Unit = {},
    onFileStorageClick: () -> Unit = {},
    onPdfSettingsClick: () -> Unit = {},
    onScannerClick: () -> Unit = {},
    onOcrClick: () -> Unit = {},
    onSignatureClick: () -> Unit = {},
    onBackupSyncClick: () -> Unit = {},
    onNotificationSettingsClick: () -> Unit = {},
    onPrivacySecurityClick: () -> Unit = {},
    onTrashCleanupClick: () -> Unit = {},
    onPremiumAdsClick: () -> Unit = {},
    onAiFeaturesClick: () -> Unit = {},
    onAdvancedClick: () -> Unit = {},
    onAboutClick: () -> Unit = {},
    onPrivacyPolicyClick: () -> Unit = {},
    onTermsClick: () -> Unit = {},
    onRateAppClick: () -> Unit = {}
) {
    val context = LocalContext.current
    var showLogoutDialog by remember { mutableStateOf(false) }

    // 1. Get current logged-in Firebase user email
    val firebaseUser = FirebaseAuth.getInstance().currentUser
    val currentUserEmail = firebaseUser?.email ?: "user@mydocs.com"

    // 2. Global/General Prefs se check karo active email
    val generalPrefs = remember {
        context.getSharedPreferences("mydocs_profile_prefs", Context.MODE_PRIVATE)
    }

    LaunchedEffect(currentUserEmail) {
        if (firebaseUser?.email != null) {
            generalPrefs.edit().putString("user_email", currentUserEmail).apply()
        }
    }

    // 3. User-Specific SharedPreferences file based on current email
    val userPrefsName = "user_prefs_${currentUserEmail.replace(Regex("[^A-Za-z0-9]"), "_")}"
    val userSharedPreferences = remember(currentUserEmail) {
        context.getSharedPreferences(userPrefsName, Context.MODE_PRIVATE)
    }

    val defaultName = firebaseUser?.displayName?.takeIf { !it.isBlank() }
        ?: currentUserEmail.substringBefore("@").replaceFirstChar { it.uppercase() }

    var displayName by remember {
        mutableStateOf(userSharedPreferences.getString("user_name", defaultName) ?: defaultName)
    }
    var profileImageUri by remember {
        mutableStateOf(userSharedPreferences.getString("profile_image_path", null))
    }

    LaunchedEffect(currentUserEmail) {
        val freshPrefs = context.getSharedPreferences(userPrefsName, Context.MODE_PRIVATE)
        displayName = freshPrefs.getString("user_name", defaultName) ?: defaultName
        profileImageUri = freshPrefs.getString("profile_image_path", null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "Settings",
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
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            // Profile Header Card with Real Dynamic User Data & DP Support
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onProfileClick() },
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
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF3B59FF).copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (profileImageUri != null && File(profileImageUri!!).exists()) {
                                AsyncImage(
                                    model = File(profileImageUri!!),
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Profile Icon",
                                    tint = Color(0xFF3B59FF),
                                    modifier = Modifier.size(26.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = displayName,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color(0xFF1E1E24)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = currentUserEmail,
                                fontSize = 12.5.sp,
                                color = Color.Gray
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                }
            }

            // ACCOUNT
            item { SettingsSectionHeader(title = "ACCOUNT") }
            item {
                SettingsGroupCard {
                    SettingsNavItem(icon = Icons.Default.Person, title = "Profile", onClick = onProfileClick)
                    SettingsDivider()
                    SettingsNavItem(icon = Icons.Default.Lock, title = "Security", onClick = onSecurityClick)
                }
            }

            // APPEARANCE
            item { SettingsSectionHeader(title = "APPEARANCE") }
            item {
                SettingsGroupCard {
                    SettingsNavItem(icon = Icons.Default.Palette, title = "Appearance", onClick = onAppearanceClick)
                    SettingsDivider()
                    SettingsNavItem(icon = Icons.Default.Language, title = "Language", onClick = onLanguageClick)
                    SettingsDivider()
                    SettingsNavItem(icon = Icons.Default.Accessibility, title = "Accessibility", onClick = onAccessibilityClick)
                }
            }

            // DOCUMENTS
            item { SettingsSectionHeader(title = "DOCUMENTS") }
            item {
                SettingsGroupCard {
                    SettingsNavItem(icon = Icons.Default.Folder, title = "File & Storage", onClick = onFileStorageClick)
                    SettingsDivider()
                    SettingsNavItem(icon = Icons.Default.Description, title = "PDF Settings", onClick = onPdfSettingsClick)
                    SettingsDivider()
                    SettingsNavItem(icon = Icons.Default.CameraAlt, title = "Scanner", onClick = onScannerClick)
                    SettingsDivider()
                    SettingsNavItem(icon = Icons.Default.Search, title = "OCR", onClick = onOcrClick)
                    SettingsDivider()
                    SettingsNavItem(icon = Icons.Default.Create, title = "Signature", onClick = onSignatureClick)
                }
            }

            // BACKUP
            item { SettingsSectionHeader(title = "BACKUP") }
            item {
                SettingsGroupCard {
                    SettingsNavItem(icon = Icons.Default.CloudSync, title = "Backup & Sync", onClick = onBackupSyncClick)
                }
            }

            // NOTIFICATIONS
            item { SettingsSectionHeader(title = "NOTIFICATIONS") }
            item {
                SettingsGroupCard {
                    SettingsNavItem(icon = Icons.Default.Notifications, title = "Notifications", onClick = onNotificationSettingsClick)
                }
            }

            // PRIVACY
            item { SettingsSectionHeader(title = "PRIVACY") }
            item {
                SettingsGroupCard {
                    SettingsNavItem(icon = Icons.Default.Security, title = "Privacy & Security", onClick = onPrivacySecurityClick)
                    SettingsDivider()
                    SettingsNavItem(icon = Icons.Default.Delete, title = "Trash & Cleanup", onClick = onTrashCleanupClick)
                }
            }

            // APP
            item { SettingsSectionHeader(title = "APP") }
            item {
                SettingsGroupCard {
                    SettingsNavItem(icon = Icons.Default.CreditCard, title = "Premium & Ads", onClick = onPremiumAdsClick)
                    SettingsDivider()
                    SettingsNavItem(icon = Icons.Default.Psychology, title = "AI Features", onClick = onAiFeaturesClick)
                    SettingsDivider()
                    SettingsNavItem(icon = Icons.Default.Build, title = "Advanced", onClick = onAdvancedClick)
                }
            }

            // ABOUT
            item { SettingsSectionHeader(title = "ABOUT") }
            item {
                SettingsGroupCard {
                    SettingsNavItem(icon = Icons.Default.Info, title = "About MyDoc", onClick = onAboutClick)
                    SettingsDivider()
                    SettingsNavItem(icon = Icons.Default.MenuBook, title = "Privacy Policy", onClick = onPrivacyPolicyClick)
                    SettingsDivider()
                    SettingsNavItem(icon = Icons.Default.Description, title = "Terms", onClick = onTermsClick)
                    SettingsDivider()
                    SettingsNavItem(icon = Icons.Default.Star, title = "Rate App", onClick = onRateAppClick)
                }
            }

            // Logout Button
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { showLogoutDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444).copy(alpha = 0.1f)),
                    elevation = ButtonDefaults.buttonElevation(0.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Logout Icon",
                            tint = Color(0xFFEF4444),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Log Out",
                            color = Color(0xFFEF4444),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Log Out", fontWeight = FontWeight.Bold) },
            text = { Text("Are you sure you want to log out of your account?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        authViewModel.logout {
                            onLogoutSuccess()
                        }
                    }
                ) {
                    Text("Log Out", color = Color(0xFFEF4444), fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancel", color = Color.Gray) // 🔥 Yahan error tha jo ab theek kar diya hai
                }
            }
        )
    }
}

@Composable
fun SettingsSectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        modifier = Modifier.padding(start = 4.dp, top = 8.dp, bottom = 2.dp)
    )
}

@Composable
fun SettingsGroupCard(content: @Composable ColumnScope.() -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth(), content = content)
    }
}

@Composable
fun SettingsNavItem(
    icon: ImageVector,
    title: String,
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF3B59FF),
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1E1E24)
            )
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
fun SettingsDivider() {
    HorizontalDivider(
        color = Color.LightGray.copy(alpha = 0.2f),
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}