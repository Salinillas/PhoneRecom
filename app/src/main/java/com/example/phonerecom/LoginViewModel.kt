// app/src/main/java/com/example/phonerecom/LoginViewModel.kt
package com.example.phonerecom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
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
/*
    fun registerUser(email: String, password: String, role: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = firebaseManager.registerUser(email, password, role)
            onResult(result)
        }
    }
*/
    fun register(email: String, password: String, role: String, callback: (Boolean, String?) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            callback(false, "Por favor ingrese un nombre de usuario y contraseña")
        } else {
            viewModelScope.launch {
                val success = firebaseManager.registerUser(email, password, role)
                if (success) {
                    callback(true, "user")
                } else {
                    callback(false, "Error al crear el usuario en la base de datos")
                }
            }
        }
    }
/*
    fun registerUser(email: String, password: String, role: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        val userData = User(username = email, password = password, role = role)
                        firebaseManager.collection("users").document(it.uid).set(userData)
                            .addOnSuccessListener {
                                // Usuario registrado y datos guardados en Firestore
                            }
                            .addOnFailureListener {
                                // Error al guardar los datos en Firestore
                            }
                    }
                } else {
                    // Error en el registro
                }
            }
    }*/

    fun login(email: String, password: String, callback: (Boolean, String?, String?) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            callback(false, "Por favor ingrese un nombre de usuario y contraseña", null)
        } else {
            viewModelScope.launch {

                val success = firebaseManager.loginUser(email, password)
                val role = firebaseManager.getUserRole()
                if (success) {
                    callback(true, "user logged in successfully", role)
                } else {
                    callback(false, "Error al loggear el usuario", null)
                }
/*
                if (task.isSuccessful) {
                    viewModelScope.launch {
                        val role = firebaseManager.getUserRole(email)
                        if (role != null) {
                            callback(true, "user", role)
                        } else {
                            callback(false, "Error al obtener el rol del usuario", null)
                        }
                    }
                } else {
                    callback(false, task.exception?.message, null)
                }*/
            }
        }
    }














        fun loginUser(email: String, password: String, onResult: (Boolean) -> Unit) {
            viewModelScope.launch {
                val result = firebaseManager.loginUser(email, password)
                onResult(result)
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