// app/src/main/java/com/example/phonerecom/LoginViewModel.kt
package com.example.phonerecom

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.launch

class LoginViewModel(private val firebaseManager: FirebaseManager) : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

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

    fun deleteUser(username: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = firebaseManager.deleteUser(username)
            callback(success)
        }
    }
    fun updateUser(user: User, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = firebaseManager.updateUser(user)
            callback(success)
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
            callback(false, "Error creating user in the database, a user with that name may already exist.")
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

        fun getCurrentUser(): User? {
            return firebaseManager.getCurrentUser()
        }

        fun getUserRole(onResult: (String?) -> Unit) {
        viewModelScope.launch {
            val role = firebaseManager.getUserRole()
            onResult(role)
        }
    }

}