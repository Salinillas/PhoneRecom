package com.example.phonerecom

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage


@Composable
fun PhoneDeletionPanel(navController: NavHostController, phoneViewModel: PhoneViewModel) {
    var name by remember { mutableStateOf("") }
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
        verticalArrangement = Arrangement.Top
    ) {
        IconButton(onClick = { navController.navigate("admin_panel") }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Phone name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        Button(
            onClick = {
                val n = name
                if (n != "" && phoneViewModel.phoneExists(name)) {
                    phoneViewModel.deletePhone(name)
                    Toast.makeText(context, "Phone deleted.", Toast.LENGTH_SHORT).show()
                    name = ""
                    phones = phoneViewModel.getAllPhones()
                } else {
                    Toast.makeText(context, "Invalid Name.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete Phone")
        }
        Button(
            onClick = {
                val n = name
                if (n != "" && phoneViewModel.phoneExists(name)) {
                    phoneViewModel.setSelectedPhone(phoneViewModel.getPhone(name))
                    navController.navigate("modify_phone_panel")
                } else {
                    Toast.makeText(context, "Invalid Name.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Modify Phone")
        }
        phones.forEach { phone ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedPhone = phone
                        name = phone.name
                    }
                    .padding(8.dp)
            ) {
                Text(phone.name)
                if (selectedPhone == phone) {
                    phone.attributes.forEach { (key, attribute) ->
                        Text("$key: ${attribute.specification} (Score: ${attribute.score})")
                    }
                    if (phone.photoUrl.isNotEmpty()) {
                        AsyncImage(
                            model = phone.photoUrl,
                            contentDescription = phone.name,
                            modifier = Modifier
                                .height(300.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    phone.comments.forEach { comment ->
                        Text(
                            text = "${comment.author}: ${comment.content} (Score: ${comment.score})",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}