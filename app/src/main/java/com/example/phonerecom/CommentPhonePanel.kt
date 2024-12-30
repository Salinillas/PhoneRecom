package com.example.phonerecom

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CommentPhonePanel(navController: NavHostController, phoneViewModel: PhoneViewModel) {
    var phones by remember { mutableStateOf(listOf<Phone>()) }
    var phoneSearch by remember { mutableStateOf("") }
    var selectedPhone by remember { mutableStateOf<Phone?>(null) }
    var comment by remember { mutableStateOf("") }
    var score by remember { mutableStateOf("") }
    var currentUser by remember { mutableStateOf("") }
    /*LaunchedEffect(Unit) {
        phones = phoneViewModel.getAllPhones()
    }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        IconButton(onClick = { navController.navigate("user_panel") }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        OutlinedTextField(
            value = phoneSearch,
            onValueChange = { phoneSearch = it },
            label = { Text("Phone Name") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Button(
            onClick = {
                phones = phoneViewModel.sortPhonesByName(phoneSearch)
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Filter")
        }
        phones.forEach { phone ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedPhone = phone }
                    .padding(8.dp)
            ) {
                Text(
                    text = phone.name,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        selectedPhone?.let { phone ->
            Spacer(modifier = Modifier.height(16.dp))
            Text("Selected Phone: ${phone.name}", style = MaterialTheme.typography.bodyLarge)
            OutlinedTextField(
                value = comment,
                onValueChange = { comment = it },
                label = { Text("Comment") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = score,
                onValueChange = { score = it },
                label = { Text("Score (0-10)") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            Button(onClick = {
                currentUser = phoneViewModel.getCurrentUser()?.username ?: ""
                val userScore = score.toFloatOrNull() ?: 0f
                val updatedPhone = phone.copy(
                    comments = phone.comments + Comment(currentUser, comment, userScore),
                    //attributes = phone.attributes + ("User_Score" to PhoneAttribute("User Score", userScore))
                )
                phoneViewModel.updatePhone(updatedPhone)
                comment = ""
                score = ""
            }) {
                Text("Submit")
            }
        }
    }
}