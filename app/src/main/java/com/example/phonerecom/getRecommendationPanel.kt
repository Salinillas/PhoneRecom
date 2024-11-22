package com.example.phonerecom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun GetRecommendationPanel(navController: NavHostController, phoneViewModel: PhoneViewModel) {
    var budget by remember { mutableStateOf("") }
    var recommendedPhone by remember { mutableStateOf<Phone?>(null) }
    val context = LocalContext.current
    var phones by remember { mutableStateOf(listOf<Phone>()) }
    var selectedAttributes by remember { mutableStateOf(listOf<String>()) }
    val sortParameters = listOf("Name", "Software", "Screen", "Camera",
        "Battery", "Build Quality", "Speaker", "Microphone", "RAM", "Internal Memory",
        "CPU", "GPU", "Size", "Reviews", "User Opinions", "Popularity", "Price")

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
                if (budgetValue != null) {
                    recommendedPhone = phoneViewModel.getBestPhoneBySelectedAttributesWithinBudget(selectedAttributes,
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
                    recommendedPhone = phoneViewModel.getBestPhoneByBudget(budgetValue)
                } else {
                    Toast.makeText(context, "Invalid budget", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Best phone by budget")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Recommended Phone:")
            if (recommendedPhone != null) {
                Text("ID: ${recommendedPhone!!.id}, Name: ${recommendedPhone!!.nombre} (Average Score: ${
                    recommendedPhone!!.attributes.values.map { it.score }.average()
                })")
                recommendedPhone!!.attributes.forEach { (key, attribute) ->
                    Text("$key: ${attribute.specification} (Score: ${attribute.score})")
                }
            }
        }

    }
}