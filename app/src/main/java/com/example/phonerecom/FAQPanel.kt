package com.example.phonerecom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FAQPanel(navController: NavHostController) {
    val faqs = listOf(
        "How do I reset my password?" to "To reset your password, go to the login screen and click on 'Forgot Password'. Follow the instructions to reset your password.",
        "How do I update my profile information?" to "To update your profile information, go to the user panel and click on 'Edit Profile'. Make the necessary changes and save.",
        "How do I delete my account?" to "To delete your account, please contact our support team at support@example.com.",
        "How do I contact customer support?" to "You can contact customer support by emailing support@example.com or calling 1-800-123-4567.",
        "Where can I find the user manual?" to "The user manual can be found in the 'Help' section of the app or on our website at www.example.com/user-manual."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Frequently Asked Questions", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(bottom = 16.dp))

        faqs.forEach { (question, answer) ->
            Text(question, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(vertical = 8.dp))
            Text(answer, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 16.dp))
        }

        Button(onClick = { navController.navigateUp() }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Back")
        }
    }
}