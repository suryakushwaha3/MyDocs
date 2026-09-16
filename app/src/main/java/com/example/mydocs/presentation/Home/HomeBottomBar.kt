package com.example.mydocs.presentation.Home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeBottomBar(
    selectedRoute: String = "Home",
    onTabSelected: (String) -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 16.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavItem(
                    icon = Icons.Default.Home,
                    label = "Home",
                    selected = selectedRoute == "Home",
                    onClick = { onTabSelected("Home") }
                )
                BottomNavItem(
                    icon = Icons.Default.Description,
                    label = "Documents",
                    selected = selectedRoute == "Documents",
                    onClick = { onTabSelected("Documents") }
                )

                Spacer(modifier = Modifier.width(56.dp)) // Gap for FAB

                BottomNavItem(
                    icon = Icons.Default.GridView,
                    label = "Tools",
                    selected = selectedRoute == "Tools",
                    onClick = { onTabSelected("Tools") }
                )
                BottomNavItem(
                    icon = Icons.Default.Settings,
                    label = "Settings",
                    selected = selectedRoute == "Settings",
                    onClick = { onTabSelected("Settings") }
                )
            }
        }
    }
}

@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val tint = if (selected) Color(0xFF3B59FF) else Color.Gray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = tint, modifier = Modifier.size(23.dp))
        Spacer(modifier = Modifier.height(3.dp))
        Text(text = label, fontSize = 10.sp, color = tint, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
    }
}