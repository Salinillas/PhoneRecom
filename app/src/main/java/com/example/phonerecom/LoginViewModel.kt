package com.example.phonerecom

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(private val firebaseManager: FirebaseManager) : ViewModel() {
    fun userExists(username: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val exists = firebaseManager.userExists(username)
            callback(exists)
        }
    }

    fun getAllUsers(callback: (List<User>) -> Unit) {
        viewModelScope.launch {
            val users = firebaseManager.getAllUsers()
            callback(users)
        }
    }

    fun register(
        email: String,
        password: String,
        role: String,
        callback: (success: Boolean, message: String?) -> Unit
    ) {
        // Validaciones locales
        when {
            email.isBlank() || password.isBlank() -> {
                callback(false, "Please enter a username and password.")
                return
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                callback(false, "Invalid email format.")
                return
            }

            password.length < 6 -> {
                callback(false, "Password must be at least 6 characters long.")
                return
            }
        }

        // Intento de registro en Firebase
        viewModelScope.launch {
            val success = firebaseManager.registerUser(email, password, role)
            if (success) {
                callback(true, email)
            } else {
                callback(
                    false,
                    "Error creating user in the database, a user with that name may already exist."
                )
            }
        }
    }

    fun login(email: String, password: String, callback: (Boolean, String?, String?) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            callback(false, "Please enter a username and password.", null)
        } else {
            viewModelScope.launch {

                val success = firebaseManager.loginUser(email, password)
                val role = firebaseManager.getUserRole()
                if (success) {
                    callback(true, "Login successful.", role)
                } else {
                    callback(false, "Login error: Incorrect username or password.", null)
                }
            }
        }
    }

    fun logoutUser() {
        firebaseManager.logoutUser()
    }

}