package com.example.mydocs.Security

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Yeh check karega ki user pehle se logged in hai ya nahi
    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        val trimmedEmail = email.trim()
        if (trimmedEmail.isBlank() || password.isBlank()) {
            errorMessage = "Please fill in all fields"
            return
        }
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    auth.signInWithEmailAndPassword(trimmedEmail, password).await()
                }
                isLoading = false
                onSuccess()
            } catch (e: Exception) {
                isLoading = false
                errorMessage = e.localizedMessage ?: "Login failed"
            }
        }
    }

    fun signUp(fullName: String, email: String, password: String, onSuccess: () -> Unit) {
        val trimmedName = fullName.trim()
        val trimmedEmail = email.trim()
        if (trimmedName.isBlank() || trimmedEmail.isBlank() || password.isBlank()) {
            errorMessage = "Please fill in all fields"
            return
        }
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    auth.createUserWithEmailAndPassword(trimmedEmail, password).await()
                }
                isLoading = false
                onSuccess()
            } catch (e: Exception) {
                isLoading = false
                errorMessage = e.localizedMessage ?: "Sign up failed"
            }
        }
    }

    fun sendPasswordReset(email: String, onSuccess: () -> Unit) {
        val trimmedEmail = email.trim()
        if (trimmedEmail.isBlank()) {
            errorMessage = "Please enter your email address"
            return
        }
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    auth.sendPasswordResetEmail(trimmedEmail).await()
                }
                isLoading = false
                onSuccess()
            } catch (e: Exception) {
                isLoading = false
                errorMessage = e.localizedMessage ?: "Failed to send reset email"
            }
        }
    }

    // YEH FUNCTION ADD KAREIN:
    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    auth.signOut()
                }
                onSuccess()
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Logout failed"
            }
        }
    }
}