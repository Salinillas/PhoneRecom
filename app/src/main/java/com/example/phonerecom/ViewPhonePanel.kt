package com.example.phonerecom


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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController




@Composable
fun ViewPhonesPanel(navController: NavHostController, phoneViewModel: PhoneViewModel) {
    var phones by remember { mutableStateOf(listOf<Phone>()) }
    var phonesToShow by remember { mutableStateOf(listOf<Phone>()) }
    var selectedSortParameter by remember { mutableStateOf("Name") }
    var sortOrder by remember { mutableStateOf("Ascending") }
    var minPrice by remember { mutableStateOf("") }
    var maxPrice by remember { mutableStateOf("") }
    val context = LocalContext.current
    var expandedSortParameter by remember { mutableStateOf(false) }
    var expandedSortOrder by remember { mutableStateOf(false) }
    var selectedAttributes by remember { mutableStateOf(listOf<String>()) }
    val sortParameters = listOf("Score", "Software", "Screen", "Camera",
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
        verticalArrangement = Arrangement.Top
    ) {
        IconButton(onClick = { navController.navigate("user_panel") }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        // Dropdown menu for sorting parameters
        /*Box {
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
                val sortParameters = listOf("Score","Name", "Software", "Screen", "Camera",
                    "Battery", "Build Quality", "Speaker", "Microphone", "RAM", "Internal Memory",
                    "CPU", "GPU", "Size", "Reviews", "User Opinions", "Popularity", "Price")

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
        }*/
        MultiSelectDropdown(
            options = sortParameters,
            selectedOptions = selectedAttributes,
            onSelectionChange = { selectedAttributes = it }
        )
        Box {
            OutlinedTextField(
                value = sortOrder,
                onValueChange = { sortOrder = it },
                label = { Text("Sort order") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expandedSortOrder = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                }
            )
            DropdownMenu(
                expanded = expandedSortOrder,
                onDismissRequest = { expandedSortOrder = false }
            ) {
                val sortParameters = listOf("Ascending","Descending")

                sortParameters.forEach { parameter ->
                    DropdownMenuItem(
                        text = { Text(parameter) },
                        onClick = {
                            sortOrder = parameter
                            expandedSortOrder = false
                        }
                    )
                }
            }
        }

        // Input fields for price range
        OutlinedTextField(
            value = minPrice,
            onValueChange = { minPrice = it },
            label = { Text("Min Price") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = maxPrice,
            onValueChange = { maxPrice = it },
            label = { Text("Max Price") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Button to apply filters
        Button(
            onClick = {
                val min = minPrice.toFloatOrNull()
                val max = maxPrice.toFloatOrNull()
                if (min != null && max != null) {
                    phonesToShow = phones.filter { phone ->
                        val price =
                            phone.attributes["Price"]?.specification?.replace(Regex("[^\\d.]"), "")
                                ?.toFloatOrNull()
                        price != null && price in min..max
                    }
                } else if (min == null && max == null) {
                    phonesToShow = phones
            }else {
                    Toast.makeText(context, "Invalid price range", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Apply Filters")
        }
/*
        // Display the list of phones
        when (sortOrder){
            "Ascending" -> phonesToShow.sortedBy { phone ->
                when (selectedSortParameter) {
                    "Score" -> phone.attributes.values.sumOf { (it.score * 1000).toInt() }
                    "Price" -> phone.attributes["Price"]?.specification?.replace(Regex("[^\\d.]"), "")?.toFloatOrNull() ?: Float.MAX_VALUE
                    "Name" -> phone.nombre
                    "Software" -> phone.attributes["Software"]?.score ?: 0f
                    "Screen" -> phone.attributes["Screen"]?.score ?: 0f
                    "Camera" -> phone.attributes["Camera"]?.score ?: 0f
                    "Battery" -> phone.attributes["Battery"]?.score ?: 0f
                    "Build Quality" -> phone.attributes["Build_Quality"]?.score ?: 0f
                    "Speaker" -> phone.attributes["Speaker"]?.score ?: 0f
                    "Microphone" -> phone.attributes["Microphone"]?.score ?: 0f
                    "RAM" -> phone.attributes["RAM"]?.score ?: 0f
                    "Internal Memory" -> phone.attributes["Internal_Memory"]?.score ?: 0f
                    "CPU" -> phone.attributes["CPU"]?.score ?: 0f
                    "GPU" -> phone.attributes["GPU"]?.score ?: 0f
                    "Size" -> phone.attributes["Size"]?.score ?: 0f
                    "Reviews" -> phone.attributes["Reviews"]?.score ?: 0f
                    "User Opinions" -> phone.attributes["User_Opinions"]?.score ?: 0f
                    "Popularity" -> phone.attributes["Popularity"]?.score ?: 0f
                    else -> phone.nombre
                } as Comparable<Any>
            }.forEach { phone ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Name: ${phone.nombre} (Average Score: ${phone.attributes.values.map { it.score }.average()})", style = MaterialTheme.typography.bodyLarge)
                    phone.attributes.forEach { (key, attribute) ->
                        Text("$key: ${attribute.specification} (Score: ${attribute.score})")
                    }
                }
            }

            "Descending" -> phonesToShow.sortedByDescending { phone ->
                when (selectedSortParameter) {
                    "Score" -> phone.attributes.values.sumOf { (it.score * 1000).toInt() }
                    "Price" -> phone.attributes["Price"]?.specification?.replace(Regex("[^\\d.]"), "")?.toFloatOrNull() ?: Float.MAX_VALUE
                    "Name" -> phone.nombre
                    "Software" -> phone.attributes["Software"]?.score ?: 0f
                    "Screen" -> phone.attributes["Screen"]?.score ?: 0f
                    "Camera" -> phone.attributes["Camera"]?.score ?: 0f
                    "Battery" -> phone.attributes["Battery"]?.score ?: 0f
                    "Build Quality" -> phone.attributes["Build_Quality"]?.score ?: 0f
                    "Speaker" -> phone.attributes["Speaker"]?.score ?: 0f
                    "Microphone" -> phone.attributes["Microphone"]?.score ?: 0f
                    "RAM" -> phone.attributes["RAM"]?.score ?: 0f
                    "Internal Memory" -> phone.attributes["Internal_Memory"]?.score ?: 0f
                    "CPU" -> phone.attributes["CPU"]?.score ?: 0f
                    "GPU" -> phone.attributes["GPU"]?.score ?: 0f
                    "Size" -> phone.attributes["Size"]?.score ?: 0f
                    "Reviews" -> phone.attributes["Reviews"]?.score ?: 0f
                    "User Opinions" -> phone.attributes["User_Opinions"]?.score ?: 0f
                    "Popularity" -> phone.attributes["Popularity"]?.score ?: 0f
                    else -> phone.nombre
                } as Comparable<Any>
            }.forEach { phone ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Name: ${phone.nombre} (Average Score: ${phone.attributes.values.map { it.score }.average()})", style = MaterialTheme.typography.bodyLarge)
                    phone.attributes.forEach { (key, attribute) ->
                        Text("$key: ${attribute.specification} (Score: ${attribute.score})")
                    }
                }
            }
        }*/
        phoneViewModel.sortPhonesByAttributes(phonesToShow, selectedAttributes, sortOrder).forEach { phone ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    "Name: ${phone.nombre} (Average Score: ${
                        phone.attributes.values.map { it.score }.average()
                    })", style = MaterialTheme.typography.bodyLarge
                )
                phone.attributes.forEach { (key, attribute) ->
                    Text("$key: ${attribute.specification} (Score: ${attribute.score})")
                }
            }
        }
    }
}