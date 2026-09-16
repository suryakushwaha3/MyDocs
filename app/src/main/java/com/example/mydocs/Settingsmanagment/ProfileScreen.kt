package com.example.mydocs.Settingsmanagment

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.mydocs.ViewModel.DocumentViewModel
import java.io.File
import java.io.FileOutputStream

@Composable
fun ProfileScreen(
    viewModel: DocumentViewModel = viewModel(),
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val documents by viewModel.allDocuments.collectAsState()
    val totalDocuments = documents.size.toString()

    val generalPrefs = remember {
        context.getSharedPreferences("mydocs_profile_prefs", Context.MODE_PRIVATE)
    }

    var currentEmail by remember {
        mutableStateOf(generalPrefs.getString("user_email", "") ?: "")
    }

    key(currentEmail) {
        val userPrefsName = "user_prefs_${currentEmail.ifEmpty { "guest" }.replace(Regex("[^A-Za-z0-9]"), "_")}"
        val userSharedPreferences = context.getSharedPreferences(userPrefsName, Context.MODE_PRIVATE)

        // 🔥 Ab koi dummy/default name, email ya phone pehle se nahi aayega. User khud set karega.
        var userName by remember {
            mutableStateOf(userSharedPreferences.getString("user_name", "") ?: "")
        }
        var userEmail by remember {
            mutableStateOf(userSharedPreferences.getString("user_email", currentEmail) ?: currentEmail)
        }
        var userPhone by remember {
            mutableStateOf(userSharedPreferences.getString("user_phone", "") ?: "")
        }
        var profileImageUri by remember {
            mutableStateOf(userSharedPreferences.getString("profile_image_path", null))
        }

        var showEditDialog by remember { mutableStateOf(false) }
        var tempName by remember { mutableStateOf(userName) }
        var tempEmail by remember { mutableStateOf(userEmail) }
        var tempPhone by remember { mutableStateOf(userPhone) }

        val imagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let { sourceUri ->
                try {
                    val inputStream = context.contentResolver.openInputStream(sourceUri)
                    val uniqueFileName = "profile_dp_${currentEmail.hashCode()}_${System.currentTimeMillis()}.jpg"

                    context.filesDir.listFiles { file -> file.name.startsWith("profile_dp_${currentEmail.hashCode()}_") }?.forEach { it.delete() }

                    val file = File(context.filesDir, uniqueFileName)
                    val outputStream = FileOutputStream(file)
                    inputStream?.copyTo(outputStream)
                    inputStream?.close()
                    outputStream.close()

                    val savedPath = file.absolutePath
                    profileImageUri = savedPath
                    userSharedPreferences.edit().putString("profile_image_path", savedPath).apply()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
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
                        text = "My Profile & Local Data",
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
                item {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White,
                        shadowElevation = 2.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                contentAlignment = Alignment.BottomEnd,
                                modifier = Modifier.clickable {
                                    imagePickerLauncher.launch("image/*")
                                }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(90.dp)
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
                                            contentDescription = "Profile Picture",
                                            tint = Color(0xFF3B59FF),
                                            modifier = Modifier.size(50.dp)
                                        )
                                    }
                                }
                                Surface(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape),
                                    color = Color(0xFF3B59FF),
                                    shadowElevation = 2.dp
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Default.CameraAlt,
                                            contentDescription = "Edit Photo",
                                            tint = Color.White,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = if (userName.isBlank()) "No Name Set" else userName,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = if (userName.isBlank()) Color.Gray else Color(0xFF1E1E24)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (userEmail.isBlank()) "No Email Set" else userEmail,
                                fontSize = 13.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ProfileStatCard(
                            modifier = Modifier.weight(1f),
                            title = "Saved Docs",
                            value = totalDocuments,
                            icon = Icons.Default.Folder
                        )
                        ProfileStatCard(
                            modifier = Modifier.weight(1f),
                            title = "Database",
                            value = "SQLite Room",
                            icon = Icons.Default.Storage
                        )
                    }
                }

                item {
                    Text(
                        text = "PERSONAL INFORMATION",
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
                            ProfileInfoItem(
                                icon = Icons.Default.Badge,
                                label = "Full Name",
                                value = userName.ifBlank { "Not Set" }
                            )
                            ProfileDivider()
                            ProfileInfoItem(
                                icon = Icons.Default.Email,
                                label = "Email Address",
                                value = userEmail.ifBlank { "Not Set" }
                            )
                            ProfileDivider()
                            ProfileInfoItem(
                                icon = Icons.Default.Phone,
                                label = "Phone Number",
                                value = userPhone.ifBlank { "Not Set" }
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = "LOCAL DATA & SETTINGS",
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
                            ProfileClickableItem(
                                icon = Icons.Default.Edit,
                                title = "Edit Profile Info",
                                onClick = {
                                    tempName = userName
                                    tempEmail = userEmail
                                    tempPhone = userPhone
                                    showEditDialog = true
                                }
                            )
                            ProfileDivider()
                            ProfileClickableItem(
                                icon = Icons.Default.Backup,
                                title = "Export Local Database Backup",
                                onClick = { }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            if (showEditDialog) {
                AlertDialog(
                    onDismissRequest = { showEditDialog = false },
                    title = {
                        Text(text = "Edit Profile Information", fontWeight = FontWeight.Bold)
                    },
                    text = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedTextField(
                                value = tempName,
                                onValueChange = { tempName = it },
                                label = { Text("Full Name") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = tempEmail,
                                onValueChange = { tempEmail = it },
                                label = { Text("Email Address") },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = tempPhone,
                                onValueChange = { tempPhone = it },
                                label = { Text("Phone Number") },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                userName = tempName
                                userEmail = tempEmail
                                userPhone = tempPhone

                                userSharedPreferences.edit()
                                    .putString("user_name", tempName)
                                    .putString("user_email", tempEmail)
                                    .putString("user_phone", tempPhone)
                                    .apply()

                                if (tempEmail.isNotBlank()) {
                                    generalPrefs.edit().putString("user_email", tempEmail).apply()
                                    currentEmail = tempEmail
                                }

                                showEditDialog = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B59FF))
                        ) {
                            Text("Save Changes", color = Color.White)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showEditDialog = false }) {
                            Text("Cancel", color = Color.Gray)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ProfileStatCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: ImageVector
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
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
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFF3B59FF),
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = value,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF1E1E24)
                )
                Text(
                    text = title,
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ProfileInfoItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF3B59FF),
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 11.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = if (value == "Not Set") Color.Gray else Color(0xFF1E1E24)
            )
        }
    }
}

@Composable
fun ProfileClickableItem(
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
fun ProfileDivider() {
    HorizontalDivider(
        color = Color.LightGray.copy(alpha = 0.2f),
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}