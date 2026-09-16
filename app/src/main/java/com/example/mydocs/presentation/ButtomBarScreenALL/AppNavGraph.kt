package com.example.mydocs.presentation.ButtomBarScreenALL


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.doxera.app.presentation.home.HomeScreen
import com.example.mydocs.presentation.documents.DocumentsScreen
import com.example.mydocs.presentation.tools.ToolsScreen
import com.example.mydocs.presentation.settings.SettingsScreen
 import com.example.mydocs.Security.AuthViewModel
import com.example.mydocs.Settingsmanagment.AboutScreen
import com.example.mydocs.Settingsmanagment.AccessibilityScreen
import com.example.mydocs.Settingsmanagment.AdvancedScreen
import com.example.mydocs.Settingsmanagment.AiFeaturesScreen
import com.example.mydocs.Settingsmanagment.AppearanceScreen
import com.example.mydocs.Settingsmanagment.BackupSyncScreen
import com.example.mydocs.Settingsmanagment.FileStorageScreen
import com.example.mydocs.Settingsmanagment.LanguageScreen
import com.example.mydocs.Settingsmanagment.NotificationSettingsScreen
import com.example.mydocs.Settingsmanagment.OcrScreen
import com.example.mydocs.Settingsmanagment.PdfSettingsScreen
import com.example.mydocs.Settingsmanagment.PremiumAdsScreen
import com.example.mydocs.Settingsmanagment.PrivacyPolicyScreen
import com.example.mydocs.Settingsmanagment.PrivacySecurityScreen
import com.example.mydocs.Settingsmanagment.ProfileScreen
import com.example.mydocs.Settingsmanagment.RateAppDialog
import com.example.mydocs.Settingsmanagment.ScannerScreen
import com.example.mydocs.Settingsmanagment.SecurityScreen
import com.example.mydocs.Settingsmanagment.SignatureScreen
import com.example.mydocs.Settingsmanagment.TermsScreen
import com.example.mydocs.Settingsmanagment.TrashCleanupScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    onRootLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(
                onFeatureClick = { featureName -> },
                onSeeAllClick = {
                    navController.navigate("documents") {
                        popUpTo("home") { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNotificationClick = {},
                onProfileClick = {
                    navController.navigate("settings") {
                        popUpTo("home") { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onLogoutClick = { }
            )
        }
        composable("documents") {
            DocumentsScreen()
        }
        composable("tools") {
            ToolsScreen()
        }
        composable("settings") {
            SettingsScreen(
                authViewModel = authViewModel,
                onLogoutSuccess = { onRootLogout() },
                onProfileClick = { navController.navigate("profile_screen") },
                onSecurityClick = { navController.navigate("security_screen") },
                onAppearanceClick = { navController.navigate("appearance_screen") },
                onLanguageClick = { navController.navigate("language_screen") },
                onAccessibilityClick = { navController.navigate("accessibility_screen") },
                onFileStorageClick = { navController.navigate("filestorage_screen") },
                onPdfSettingsClick = { navController.navigate("pdfsettings_screen") },
                onScannerClick = { navController.navigate("scanner_screen") },
                onOcrClick = { navController.navigate("ocr_screen") },
                onSignatureClick = { navController.navigate("signature_screen") },
                onBackupSyncClick = { navController.navigate("backupsync_screen") },
                onNotificationSettingsClick = { navController.navigate("notifications_screen") },
                onPrivacySecurityClick = { navController.navigate("privacy_screen") },
                onTrashCleanupClick = { navController.navigate("trash_screen") },
                onPremiumAdsClick = { navController.navigate("premium_screen") },
                onAiFeaturesClick = { navController.navigate("aifeatures_screen") },
                onAdvancedClick = { navController.navigate("advanced_screen") },
                onAboutClick = { navController.navigate("about_screen") },
                onPrivacyPolicyClick = { navController.navigate("privacypolicy_screen") },
                onTermsClick = { navController.navigate("terms_screen") },
                onRateAppClick = {}
            )
        }

        // Sub-screens
        composable("profile_screen") {
            ProfileScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("security_screen") {
            SecurityScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("language_screen") {
            LanguageScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("appearance_screen") {
            AppearanceScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("accessibility_screen") {
            AccessibilityScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("filestorage_screen") {
            FileStorageScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("pdfsettings_screen") {
            PdfSettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("scanner_screen") {
            ScannerScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("ocr_screen") {
            OcrScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("signature_screen") {
            SignatureScreen(
                onBackClick = { navController.popBackStack() }
            )
        }


        composable("backupsync_screen") {
            BackupSyncScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("notification_screen") {
            NotificationSettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("privacy_security_screen") {
            PrivacySecurityScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("trash_cleanup_screen") {
            TrashCleanupScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("premium_ads_screen") {
            PremiumAdsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("ai_features_screen") {
            AiFeaturesScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("advanced_screen") {
            AdvancedScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("about_screen") {
            AboutScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("privacy_policy_screen") {
            PrivacyPolicyScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("terms_screen") {
            TermsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("rate_app_dialog") {
            RateAppDialog(
                onDismiss = { navController.popBackStack() },
                onSubmitRating = { rating, feedback ->
                    // Yahan rating aur feedback handle karein (e.g., API call ya Play Store intent)
                    navController.popBackStack()
                }
            )
        }


     }
}