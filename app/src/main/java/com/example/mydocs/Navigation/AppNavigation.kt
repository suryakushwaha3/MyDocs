package com.example.mydocs.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.doxera.app.presentation.auth.LoginScreen
import com.example.mydocs.Security.AuthViewModel
import com.example.mydocs.presentation.ButtomBarScreenALL.BottomBarNavigation
import com.example.mydocs.presentation.auth.ForgotPasswordScreen
import com.example.mydocs.presentation.auth.SignUpScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val startDestination = if (authViewModel.isUserLoggedIn()) "main" else "login"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // 1. Login Screen Route
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onSignUpClick = {
                    navController.navigate("signup")
                },
                onForgotPasswordClick = {
                    navController.navigate("forgot_password")
                }
            )
        }

        // 2. Sign Up Screen Route
        composable("signup") {
            SignUpScreen(
                authViewModel = authViewModel,
                onSignUpSuccess = {
                    navController.navigate("login") {
                        popUpTo("signup") { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.popBackStack()
                },
                onGoogleSignUpClick = {}
            )
        }

        // 3. Forgot Password Screen Route
        composable("forgot_password") {
            ForgotPasswordScreen(
                authViewModel = authViewModel,
                onSubmitSuccess = {
                    navController.popBackStack()
                },
                onBackToLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        // 4. Main Route (Bottom Bar Navigation)
        composable("main") {
            BottomBarNavigation(
                authViewModel = authViewModel,
                onRootLogout = {
                    navController.navigate("login") {
                        popUpTo(navController.graph.id) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}