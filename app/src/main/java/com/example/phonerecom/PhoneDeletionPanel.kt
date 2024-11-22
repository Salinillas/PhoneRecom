package com.example.phonerecom

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun PhoneDeletionPanel(navController: NavHostController, phoneViewModel: PhoneViewModel) {
    var phoneId by remember { mutableStateOf("") }
    var selectedPhone by remember { mutableStateOf<Phone?>(null) }
    var phones by remember { mutableStateOf(listOf<Phone>()) }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        phones = phoneViewModel.getAllPhones()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { navController.navigate("user_panel") }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }

        phones.forEach { phone ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedPhone = phone }
                    .padding(8.dp)
            ) {
                Text("ID: ${phone.id}, Name: ${phone.nombre}")
                if (selectedPhone == phone) {
                    phone.attributes.forEach { (key, attribute) ->
                        Text("$key: ${attribute.specification} (Score: ${attribute.score})")
                    }
                }
            }
        }

        OutlinedTextField(
            value = phoneId,
            onValueChange = { phoneId = it },
            label = { Text("Phone ID") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                val id = phoneId.toIntOrNull()
                if (id != null) {
                    phoneViewModel.deletePhone(id)
                    phoneId = ""
                    phones = phoneViewModel.getAllPhones()
                } else {
                    Toast.makeText(context, "Invalid ID", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete Phone")
        }
    }
}