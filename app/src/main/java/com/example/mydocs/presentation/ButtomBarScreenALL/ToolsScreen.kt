package com.example.mydocs.presentation.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ToolItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconTint: Color,
    val onClick: () -> Unit
)

@Composable
fun ToolsScreen(
    onToolClick: (String) -> Unit = {}
) {
    val toolsList = listOf(
        ToolItem(
            title = "PDF",
            description = "View, Edit, Manage your PDF",
            icon = Icons.Default.Description,
            backgroundColor = Color(0xFFFFECEE),
            iconTint = Color(0xFFFF5252),
            onClick = { onToolClick("PDF Toolbox") }
        ),
        ToolItem(
            title = "Scanner",
            description = "Scan to PDF/JPG with high",
            icon = Icons.Default.CameraAlt,
            backgroundColor = Color(0xFFE8F2FF),
            iconTint = Color(0xFF2196F3),
            onClick = { onToolClick("Scanner") }
        ),
        ToolItem(
            title = "OCR",
            description = "Extract text from images & PDF.",
            icon = Icons.Default.DocumentScanner,
            backgroundColor = Color(0xFFF3EDFF),
            iconTint = Color(0xFF9C27B0),
            onClick = { onToolClick("OCR") }
        ),
        ToolItem(
            title = "Converter",
            description = "PDF • Word • Excel • PPT •",
            icon = Icons.Default.Sync,
            backgroundColor = Color(0xFFE8F8F0),
            iconTint = Color(0xFF4CAF50),
            onClick = { onToolClick("PDF Converter") }
        ),
        ToolItem(
            title = "Editor",
            description = "Edit your documents easily.",
            icon = Icons.Default.EditNote,
            backgroundColor = Color(0xFFFFF6E8),
            iconTint = Color(0xFFFF9800),
            onClick = { onToolClick("PDF Editor") }
        ),
        ToolItem(
            title = "File Manager",
            description = "All your files, one place.",
            icon = Icons.Default.Folder,
            backgroundColor = Color(0xFFE0F7FA),
            iconTint = Color(0xFF00BCD4),
            onClick = { onToolClick("File Manager") }
        ),
        ToolItem(
            title = "Cloud",
            description = "Backup & Sync your files",
            icon = Icons.Default.Cloud,
            backgroundColor = Color(0xFFE3F2FD),
            iconTint = Color(0xFF1E88E5),
            onClick = { onToolClick("Cloud") }
        ),
        ToolItem(
            title = "Templates",
            description = "Ready to use professional",
            icon = Icons.Default.Dashboard,
            backgroundColor = Color(0xFFFCE4EC),
            iconTint = Color(0xFFE91E63),
            onClick = { onToolClick("Templates") }
        )
    )

    // Outer Column without nested scaffold to prevent extra padding gaps from BottomBarNavigation
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {
        // Clean Custom Header Bar
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
                    text = "PDF Tools",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF1E1E24)
                )
            }
        }

        // Grid List matching the requested design layout
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(toolsList) { tool ->
                ToolCard(tool = tool)
            }
        }
    }
}

@Composable
fun ToolCard(tool: ToolItem) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(135.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = tool.onClick),
        color = tool.backgroundColor,
        shadowElevation = 0.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top row with Icon and Forward Action Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(40.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = tool.icon,
                            contentDescription = tool.title,
                            tint = tool.iconTint,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }

                // Arrow Action Badge
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(tool.iconTint),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Open",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Bottom Texts
            Column {
                Text(
                    text = tool.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF1E1E24)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = tool.description,
                    fontSize = 11.5.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
            }
        }
    }
}