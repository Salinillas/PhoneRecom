// app/src/main/java/com/example/phonerecom/AdminPanel.kt
package com.example.phonerecom

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AdminPanel(navController: NavController, viewModel: LoginViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }
    var users by remember { mutableStateOf(listOf<User>()) }
    var selectedUser by remember { mutableStateOf<User?>(null) }
    var showUserForm by remember { mutableStateOf(false) }
    var showUserList by remember { mutableStateOf(false) }
    var modifyUser by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getAllUsers { users = it }
    }
    BackHandler {
        if (showUserForm || showUserList || modifyUser) {
            username = ""
            password = ""
            role = ""
            showUserForm = false
            showUserList = false
            modifyUser = false
        } else {
            viewModel.logout()
            navController.navigate("login")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (showUserForm || showUserList) {
            IconButton(onClick = {
                username = ""
                password = ""
                role = ""
                showUserForm = false
                showUserList = false
            }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        } else if (modifyUser) {
            IconButton(onClick = {
                username = ""
                password = ""
                role = ""
                showUserList = true
                modifyUser = false
            }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        } else {

            IconButton(onClick = { navController.navigate("login") }) {
                username = ""
                password = ""
                role = ""
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }

        if (showUserForm) {


            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            Box {
                OutlinedTextField(
                    value = role,
                    onValueChange = { role = it },
                    label = { Text("Role") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                        }
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("User") },
                        onClick = {
                            role = "user"
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Admin") },
                        onClick = {
                            role = "admin"
                            expanded = false
                        }
                    )
                }
            }

            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        viewModel.userExists(username) { exists ->
                            if (!exists) {
                                viewModel.addUser(username, password, role) { success ->
                                    if (success) {
                                        viewModel.getAllUsers { users = it }
                                        username = ""
                                        password = ""
                                        role = ""
                                        Toast.makeText(context, "User added", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                            } else {
                                Toast.makeText(context, "User already exists", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Username and password cannot be empty", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add User")
            }
        } else if (showUserList) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                users.forEach { user ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedUser = user
                                username = user.username
                                password = user.password
                                showUserList = false
                                modifyUser = true
                            }
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(user.username)
                        Button(onClick = {
                            viewModel.deleteUser(user.username) { success ->
                                if (success) {
                                    viewModel.getAllUsers { users = it }
                                }
                            }
                        }) {
                            Text("Delete")
                        }
                    }
                }
            }
        } else if (modifyUser) {


            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
            Box {
                OutlinedTextField(
                    value = role,
                    onValueChange = { role = it },
                    label = { Text("Role") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                        }
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("User") },
                        onClick = {
                            role = "user"
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Admin") },
                        onClick = {
                            role = "admin"
                            expanded = false
                        }
                    )
                }
            }
            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty())
                        viewModel.userExists(selectedUser!!.username) { exists ->
                            if (exists) {
                                viewModel.updateUser(
                                    selectedUser!!.username,
                                    username,
                                    password,
                                    role
                                ) { success ->
                                    if (success) {
                                        viewModel.getAllUsers { users = it }
                                        modifyUser = false
                                        selectedUser = null
                                        username = ""
                                        password = ""
                                        role = ""
                                        Toast.makeText(context, "User modified", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                            } else {
                                Toast.makeText(context, "User does not exist", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Modify User")
            }
        } else {

            Button(
                onClick = { showUserForm = true },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text("Add User")
            }
            Button(
                onClick = { showUserList = true },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text("Show Users")
            }
            Button(
                onClick = { navController.navigate("phone_panel") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text("Phone Panel")
            }

        }
    }
}
