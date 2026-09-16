package com.doxera.app.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mydocs.Roomdatabse.DocumentEntity
import com.example.mydocs.ViewModel.DocumentViewModel
import com.example.mydocs.presentation.Home.HomeTopBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    viewModel: DocumentViewModel = viewModel(),
    onFeatureClick: (String) -> Unit = {},
    onSeeAllClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onEditClick: (DocumentEntity) -> Unit = {}, // Edit action callback
    onDeleteClick: (DocumentEntity) -> Unit = { doc -> viewModel.deleteDocument(doc) } // Delete action callback
) {
    // Room database se live documents collect karna
    val documents by viewModel.allDocuments.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF7F8FC)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 1. Fixed Top Bar at the very top
            Surface(
                color = Color(0xFFF7F8FC),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    HomeTopBar(
                        onNotificationClick = onNotificationClick,
                        onProfileClick = onProfileClick
                    )
                }
            }

            // 2. Scrollable Content below the fixed Top Bar
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Search Bar
                item {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Search your files, documents...", color = Color.Gray, fontSize = 14.sp) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(20.dp)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        )
                    )
                }

                // Feature Cards Grid - Row 1 (PDF & Scanner)
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        FeatureCard(
                            title = "PDF",
                            subtitle = "View, Edit, Manage your PDF files.",
                            icon = Icons.Default.PictureAsPdf,
                            containerColor = Color(0xFFFFECEC),
                            iconTint = Color(0xFFFF5252),
                            isPopular = true,
                            modifier = Modifier.weight(1f)
                        ) { onFeatureClick("PDF") }

                        FeatureCard(
                            title = "Scanner",
                            subtitle = "Scan to PDF/JPG with high quality.",
                            icon = Icons.Default.DocumentScanner,
                            containerColor = Color(0xFFE8F0FE),
                            iconTint = Color(0xFF1A73E8),
                            isPopular = false,
                            modifier = Modifier.weight(1f)
                        ) { onFeatureClick("Scanner") }
                    }
                }

                // Feature Cards Grid - Row 2 (OCR & Converter)
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        FeatureCard(
                            title = "OCR",
                            subtitle = "Extract text from images & PDF.",
                            icon = Icons.Default.TextSnippet,
                            containerColor = Color(0xFFF3E8FF),
                            iconTint = Color(0xFF9C27B0),
                            isPopular = false,
                            modifier = Modifier.weight(1f)
                        ) { onFeatureClick("OCR") }

                        FeatureCard(
                            title = "Converter",
                            subtitle = "PDF • Word • Excel • PPT • Images",
                            icon = Icons.Default.Sync,
                            containerColor = Color(0xFFE6F4EA),
                            iconTint = Color(0xFF34A853),
                            isPopular = false,
                            modifier = Modifier.weight(1f)
                        ) { onFeatureClick("Converter") }
                    }
                }

                // Feature Cards Grid - Row 3 (Editor & File Manager)
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        FeatureCard(
                            title = "Editor",
                            subtitle = "Edit your documents easily.",
                            icon = Icons.Default.EditNote,
                            containerColor = Color(0xFFFFF4E5),
                            iconTint = Color(0xFFFF9800),
                            isPopular = false,
                            modifier = Modifier.weight(1f)
                        ) { onFeatureClick("Editor") }

                        FeatureCard(
                            title = "File Manager",
                            subtitle = "All your files, one place.",
                            icon = Icons.Default.Folder,
                            containerColor = Color(0xFFE0F7FA),
                            iconTint = Color(0xFF00ACC1),
                            isPopular = false,
                            modifier = Modifier.weight(1f)
                        ) { onFeatureClick("File Manager") }
                    }
                }

                // Feature Cards Grid - Row 4 (Cloud & Templates)
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        FeatureCard(
                            title = "Cloud",
                            subtitle = "Backup & Sync your files securely.",
                            icon = Icons.Default.Cloud,
                            containerColor = Color(0xFFE3F2FD),
                            iconTint = Color(0xFF2196F3),
                            isPopular = false,
                            modifier = Modifier.weight(1f)
                        ) { onFeatureClick("Cloud") }

                        FeatureCard(
                            title = "Templates",
                            subtitle = "Ready to use professional templates.",
                            icon = Icons.Default.Dashboard,
                            containerColor = Color(0xFFFCE4EC),
                            iconTint = Color(0xFFE91E63),
                            isPopular = false,
                            modifier = Modifier.weight(1f)
                        ) { onFeatureClick("Templates") }
                    }
                }

                // Recent Files Header
                item {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Schedule, contentDescription = null, tint = Color.DarkGray, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Recent Files", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E1E24))
                        }
                        TextButton(onClick = onSeeAllClick) {
                            Text(text = "See All >", color = Color(0xFF3B59FF), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                // Recent Files List - Room Database Integration
                if (documents.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No recent files saved yet.",
                                fontSize = 13.sp,
                                color = Color.Gray
                            )
                        }
                    }
                } else {
                    items(documents, key = { it.id }) { doc ->
                        val formattedDate = rememberFormattedDate(doc.timestamp)
                        RecentFileItem(
                            document = doc,
                            time = formattedDate,
                            onEditClick = { onEditClick(doc) },
                            onDeleteClick = { onDeleteClick(doc) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun rememberFormattedDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
    return sdf.format(Date(timestamp))
}

@Composable
fun FeatureCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    containerColor: Color,
    iconTint: Color,
    isPopular: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(130.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.8f))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.size(42.dp),
                        shape = RoundedCornerShape(12.dp),
                        color = Color.White,
                        shadowElevation = 2.dp
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = iconTint,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .clip(CircleShape)
                            .background(iconTint),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(13.dp)
                        )
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF1E1E24)
                    )
                    Text(
                        text = subtitle,
                        fontSize = 10.sp,
                        color = Color.DarkGray.copy(alpha = 0.75f),
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun RecentFileItem(
    document: DocumentEntity,
    time: String,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFFFECEC)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Description, contentDescription = null, tint = Color(0xFFFF5252), modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = document.title, fontWeight = FontWeight.SemiBold, fontSize = 13.5.sp, color = Color(0xFF1E1E24), maxLines = 1)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = "${document.category} • Local", fontSize = 10.5.sp, color = Color.Gray)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = time, fontSize = 11.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))

                Box {
                    IconButton(onClick = { expanded = true }, modifier = Modifier.size(28.dp)) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Options", tint = Color.Gray, modifier = Modifier.size(18.dp))
                    }

                    // Dropdown menu for Edit and Delete
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Edit") },
                            onClick = {
                                expanded = false
                                onEditClick()
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(18.dp))
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Delete", color = Color.Red) },
                            onClick = {
                                expanded = false
                                onDeleteClick()
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Red, modifier = Modifier.size(18.dp))
                            }
                        )
                    }
                }
            }
        }
    }
}