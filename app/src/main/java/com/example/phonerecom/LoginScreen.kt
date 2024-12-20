// app/src/main/java/com/example/phonerecom/LoginScreen.kt
package com.example.phonerecom

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    BackHandler {
        // Do nothing to prevent back navigation
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("User name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)

        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)

        )

        Button(
            onClick = {
                viewModel.login(username, password) { success, role ->
                    if (success) {
                        if (role == "admin") {
                            navController.navigate("admin_panel") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            Toast.makeText(context, "Sesión iniciada", Toast.LENGTH_SHORT).show()

                            navController.navigate("user_panel") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                    } else {
                        Toast.makeText(context, "Credenciales no válidos", Toast.LENGTH_SHORT).show()

                        error = "Nombre de usuario o contraseña incorrectos"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

        Button(
            onClick = {
                viewModel.register(username, password) { success, role ->
                    if (success) {
                        if (role == "admin") {
                            navController.navigate("admin_panel") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show()

                            navController.navigate("user_panel") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                    } else {
                        Toast.makeText(context, "Credenciales no válidos", Toast.LENGTH_SHORT).show()

                        error = "Nombre de usuario o contraseña incorrectos"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }
        if (error != null) {
            Text(
                text = error!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}