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
import androidx.compose.material.icons.filled.ArrowDropDown
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
    var expandedSortParameter by remember { mutableStateOf(false) }
    var selectedSortParameter by remember { mutableStateOf("Name") }
    val sortParameters = listOf("Score","Name", "Software", "Screen", "Camera",
        "Battery", "Build Quality", "Speaker", "Microphone", "RAM", "Internal Memory",
        "CPU", "GPU", "Size", "Reviews", "User Opinions", "Popularity", "Price")

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
        Box {
            OutlinedTextField(
                value = selectedSortParameter,
                onValueChange = { selectedSortParameter = it },
                label = { Text("Sort by") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expandedSortParameter = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                }
            )
            DropdownMenu(
                expanded = expandedSortParameter,
                onDismissRequest = { expandedSortParameter = false }
            ) {


                sortParameters.forEach { parameter ->
                    DropdownMenuItem(
                        text = { Text(parameter) },
                        onClick = {
                            selectedSortParameter = parameter
                            expandedSortParameter = false
                        }
                    )
                }
            }
        }
        OutlinedTextField(
            value = budget,
            onValueChange = { budget = it },
            label = { Text("Budget") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                val budgetValue = budget.toFloatOrNull()
                if (budgetValue != null) {
                    recommendedPhone = phoneViewModel.getBestPhoneWithinBudget(budgetValue)
                    navController.navigate("recommended_phone_panel")
                } else {
                    Toast.makeText(context, "Invalid budget", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Get Recommendation by Budget")
        }

        //val attributes = listOf("Software", "Screen", "Camera", "Battery", "Build Quality", "Speaker", "Microphone", "RAM", "Internal Memory", "CPU", "GPU", "Size", "Reviews", "User Opinions", "Popularity", "Price")
        /*
        attributes.forEach { attribute ->
            Button(
                onClick = {
                    val budgetValue = budget.toFloatOrNull()
                    if (budgetValue != null) {

                        recommendedPhone = phoneViewModel.getBestPhoneByAttributeWithinBudget(attribute, budgetValue)
                        navController.navigate("recommended_phone_panel")
                    } else {
                        Toast.makeText(context, "Invalid budget", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                Text("Get Recommendation by $attribute")
            }
        }*/
    }
}