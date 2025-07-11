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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CommentPhonePanel(navController: NavHostController, phoneViewModel: PhoneViewModel) {
    var phones by remember { mutableStateOf(listOf<Phone>()) }
    var phoneSearch by remember { mutableStateOf("") }
    var selectedPhone by remember { mutableStateOf<Phone?>(null) }
    var comment by remember { mutableStateOf("") }
    var score by remember { mutableStateOf("") }
    var currentUser by remember { mutableStateOf("") }
    val context = LocalContext.current

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
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Button(
            onClick = {
                phones = phoneViewModel.sortPhonesByName(phoneSearch)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = score,
                onValueChange = { score = it },
                label = { Text("Score (0-10)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Button(onClick = {
                val userScore = score.toFloatOrNull() //?: 0f
                if (userScore != null && comment.length >= 5 && userScore in 0f..10f) {
                    currentUser = phoneViewModel.getCurrentUser()?.username ?: ""
                    val updatedPhone = phone.copy(
                        comments = phone.comments + Comment(currentUser, comment, userScore),
                    )
                    phoneViewModel.updatePhone(updatedPhone)
                    Toast.makeText(context, "Comment added.", Toast.LENGTH_SHORT).show()
                    comment = ""
                    score = ""
                } else {
                    Toast.makeText(
                        context,
                        "Comment must be at least 5 characters and the score must be a number from 0 to 10.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Text("Submit")
            }
        }
    }
}