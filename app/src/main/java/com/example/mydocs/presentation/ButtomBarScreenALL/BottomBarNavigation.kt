package com.example.mydocs.presentation.ButtomBarScreenALL

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mydocs.presentation.Home.HomeBottomBar
import com.example.mydocs.Security.AuthViewModel
import com.example.mydocs.ViewModel.DocumentViewModel
import android.provider.OpenableColumns
import androidx.compose.foundation.layout.size

@Composable
fun BottomBarNavigation(
    authViewModel: AuthViewModel,
    onRootLogout: () -> Unit,
    documentViewModel: DocumentViewModel = viewModel() // Room Database ViewModel connected here
) {
    val navController = rememberNavController()
    val context = LocalContext.current

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"

    // File picker to save directly into Room Database
    val documentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let { fileUri ->
            try {
                context.contentResolver.takePersistableUriPermission(
                    fileUri,
                    android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }

            var fileName = "Unknown Document"
            context.contentResolver.query(fileUri, null, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (nameIndex != -1) fileName = cursor.getString(nameIndex)
                }
            }

            val fileExtension = fileName.substringAfterLast('.', "").uppercase()
            val category = when {
                fileExtension.contains("PDF") -> "PDF"
                fileExtension.contains("JPG") || fileExtension.contains("PNG") || fileExtension.contains("JPEG") -> "Image"
                fileExtension.contains("DOC") -> "Word"
                fileExtension.contains("XLS") -> "Excel"
                else -> "Scan"
            }

            // Add document to database
            documentViewModel.addDocument(
                title = fileName,
                category = category,
                filePath = fileUri.toString()
            )
        }
    }

    val showBottomBar = currentRoute in listOf("home", "documents", "tools", "settings")

    // Box ka use kiya gaya hai taaki FAB ka offset position properly kaam kare
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (showBottomBar) {
                    HomeBottomBar(
                        selectedRoute = when (currentRoute) {
                            "documents" -> "Documents"
                            "tools" -> "Tools"
                            "settings" -> "Settings"
                            else -> "Home"
                        },
                        onTabSelected = { route ->
                            val destination = route.lowercase()
                            if (currentRoute != destination) {
                                navController.navigate(destination) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            AppNavGraph(
                navController = navController,
                authViewModel = authViewModel,
                onRootLogout = onRootLogout,
                modifier = Modifier.padding(innerPadding)
            )
        }

        // Floating Action Button with fully working offset control
        if (showBottomBar) {
            FloatingActionButton(
                onClick = {
                    documentLauncher.launch(
                        arrayOf(
                            "application/pdf",
                            "image/*",
                            "text/*",
                            "application/msword",
                            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                        )
                    )
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = -45.dp) // <-- Yahan value badha kar (jaise 45.dp ya 50.dp) button ko aur niche la sakte hain
                    .size(58.dp),
                containerColor = Color(0xFF3B59FF),
                contentColor = Color.White,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Document",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}