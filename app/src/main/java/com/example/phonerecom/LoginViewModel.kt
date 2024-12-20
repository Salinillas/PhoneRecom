// app/src/main/java/com/example/phonerecom/LoginViewModel.kt
package com.example.phonerecom

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel(private val dbHelper: DatabaseHelper) : ViewModel() {
    private var currentUser: String? = null
    private var currentUserRole: String? = null

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    /*
    fun login(username: String, password: String, callback: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            val db = dbHelper.readableDatabase
            val cursor = db.query(
                DatabaseHelper.TABLE_USER,
                arrayOf(DatabaseHelper.COLUMN_ROLE),
                "${DatabaseHelper.COLUMN_USERNAME} = ? AND ${DatabaseHelper.COLUMN_PASSWORD} = ?",
                arrayOf(username, password),
                null,
                null,
                null
            )
            if (cursor.moveToFirst()) {
                currentUser = username
                val role = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ROLE))
                currentUserRole = role
                callback(true, role)
            } else {
                callback(false, null)
            }
            cursor.close()
        }
    }

    fun logout() {
        currentUser = null
    }*/
    fun register (email: String, password: String, callback: (Boolean, String?) -> Unit) {
        if(email.isEmpty() || password.isEmpty()) {
            callback(false, "Por favor ingrese un nombre de usuario y contraseña")
        }else{
        viewModelScope.launch {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback(true, "user")

                    } else {
                        callback(false, task.exception?.message)
                    }
                }
        }
        }
    }

    fun login(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        if(email.isEmpty() || password.isEmpty()) {
            callback(false, "Por favor ingrese un nombre de usuario y contraseña")
        }else{
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback(true, "user")
                    } else {
                        callback(false, task.exception?.message)
                    }
                }
            }
        }
    }

    fun logout(){
        auth.signOut()
    }

    fun addUser(username: String, password: String, role: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = dbHelper.addUser(username, password, role)
            callback(result != -1L)
        }
    }

    fun deleteUser(username: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = dbHelper.deleteUser(username)
            callback(result > 0)
        }
    }

    fun updateUser(oldUsername: String, newUsername: String, newPassword: String, newRole: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = dbHelper.updateUser(oldUsername, newUsername, newPassword, newRole)
            callback(result > 0)
        }
    }

    fun userExists(username: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val exists = dbHelper.userExists(username)
            callback(exists)
        }
    }

    fun getAllUsers(callback: (List<User>) -> Unit) {
        viewModelScope.launch {
            val users = dbHelper.getAllUsers()
            callback(users)
        }
    }
}