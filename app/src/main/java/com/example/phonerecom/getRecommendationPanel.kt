package com.example.phonerecom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
@Composable
fun GetRecommendationPanel(navController: NavHostController, phoneViewModel: PhoneViewModel) {
    var budget by remember { mutableStateOf("") }
    var phones by remember { mutableStateOf(listOf<Phone>()) }
    var selectedPhone by remember { mutableStateOf<Phone?>(null) }
    val context = LocalContext.current
    var selectedAttributes by remember { mutableStateOf(listOf<String>()) }
    val sortParameters = listOf("Software", "Screen", "Camera",
        "Battery", "Build_Quality", "Speaker", "Microphone", "RAM", "Internal_Memory",
        "CPU", "GPU", "Size", "Reviews", "User_Opinion", "Popularity", "Price")



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

        MultiSelectDropdown(
            options = sortParameters,
            selectedOptions = selectedAttributes,
            onSelectionChange = { selectedAttributes = it }
        )
        OutlinedTextField(
            value = budget,
            onValueChange = { budget = it },
            label = { Text("Budget") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text("Use the button below to get a phone recommendation based on your budget and selected attributes.")
        Button(
            onClick = {
                val budgetValue = budget.toFloatOrNull()
                if(selectedAttributes.isEmpty()) {
                    Toast.makeText(context, "Please select at least one attribute", Toast.LENGTH_SHORT).show()
                } else if (budgetValue != null) {
                    phones = phoneViewModel.getBestPhoneBySelectedAttributesWithinBudget(selectedAttributes,
                        budgetValue-50, budgetValue+50)
                } else {
                    Toast.makeText(context, "Invalid budget", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Get Recommendation by Budget")
        }
        Text("Use the button below to get the best score phone based on your budget.")
        Button(
            onClick = {
                val budgetValue = budget.toFloatOrNull()
                if (budgetValue != null) {
                    phones = phoneViewModel.getBestPhoneByBudget(budgetValue)
                } else {
                    Toast.makeText(context, "Invalid budget", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Best phone by budget")
        }/*
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Recommended Phone:")
            if (recommendedPhone != null) {
                Text("Name: ${recommendedPhone!!.name} (Average Score: ${
                    recommendedPhone!!.attributes.values.map { it.score }.average()
                })")
                recommendedPhone!!.attributes.forEach { (key, attribute) ->
                    Text("$key: ${attribute.specification} (Score: ${attribute.score})")
                }
            }
        }*/
            phones.forEach { phone ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedPhone = phone }
                        .padding(8.dp)
                ) {
                    Text(
                        "Name: ${phone.name} (Average Score: ${
                            phone.attributes.values.map { it.score }.average()
                        })", style = MaterialTheme.typography.bodyLarge
                    )
                    phone.attributes.forEach { (key, attribute) ->
                        Text("$key: ${attribute.specification} (Score: ${attribute.score})")
                    }
                    if(phone.photoUrl.isNotEmpty()){
                        AsyncImage(
                            model = phone.photoUrl,
                            contentDescription = phone.name,
                            modifier = Modifier
                                .height(300.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                        }
                    if (selectedPhone == phone) {
                        phone.comments.forEach { comment ->
                            Text(
                                text = "${comment.author}: ${comment.content} (Score: ${comment.score})",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
                HorizontalDivider()
            }
    }
}