package com.example.phonerecom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun FAQPanel(navController: NavHostController) {
    val faqs = listOf(
        "How do I reset my password?" to "To reset your password, contact an administrator of the app.",
        "How do I delete my account?" to "To delete your account, please contact our support team at phoneReconsupport@gmail.com.",
        "How do I contact customer support?" to "You can contact customer support by emailing phoneReconsupport@gmail.com or calling 1-800-123-4567.",
        "How do I know the grades of the specifications are reliable?" to "The grades are determined by the general evaluation of professionals in the matter, you can trust it!",
        "Where can I find the user manual?" to "The user manual can be found contacting an administrator of the app."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            "Frequently Asked Questions",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        faqs.forEach { (question, answer) ->
            Text(
                question,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                answer,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Button(onClick = { navController.navigateUp() }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Back")
        }
    }
}