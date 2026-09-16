package com.example.mydocs.presentation.ButtomBarScreenALL

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeFloatingActionButton(onAddClick: () -> Unit = {}) {
    Box(
        modifier = Modifier.offset(y = 36.dp)
    ) {
        FloatingActionButton(
            onClick = onAddClick,
            containerColor = Color(0xFF3B59FF),
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier.size(58.dp),
            elevation = FloatingActionButtonDefaults.elevation(6.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add", modifier = Modifier.size(28.dp))
        }
    }
}