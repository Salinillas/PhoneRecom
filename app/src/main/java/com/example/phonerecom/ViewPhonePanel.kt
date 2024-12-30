package com.example.phonerecom


import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage


@Composable
fun ViewPhonesPanel(navController: NavHostController, phoneViewModel: PhoneViewModel) {
    var phones by remember { mutableStateOf(listOf<Phone>()) }
    var phonesToShow by remember { mutableStateOf(listOf<Phone>()) }
    var sortOrder by remember { mutableStateOf("Ascending") }
    var minPrice by remember { mutableStateOf("") }
    var selectedPhone by remember { mutableStateOf<Phone?>(null) }
    var maxPrice by remember { mutableStateOf("") }
    val context = LocalContext.current
    var expandedSortOrder by remember { mutableStateOf(false) }
    var selectedAttributes by remember { mutableStateOf(listOf<String>()) }
    val sortParameters = listOf("Software", "Screen", "Camera",
        "Battery", "Build_Quality", "Speaker", "Microphone", "RAM", "Internal_Memory",
        "CPU", "GPU", "Size", "Reviews", "User_Opinion", "Popularity", "Price")

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

        MultiSelectDropdown(
            options = sortParameters,
            selectedOptions = selectedAttributes,
            onSelectionChange = { selectedAttributes = it }
        )
        Box {
            OutlinedTextField(
                value = sortOrder,
                onValueChange = { sortOrder = it },
                label = { Text("Sort Type") },
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
                val sortOrders = listOf("Ascending","Descending","Avg Score")

                sortOrders.forEach { parameter ->
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
                if (min != null && max != null && min <= max) {
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
            Text("Filter")
        }

        if(sortOrder == "Avg Score"){
            Toast.makeText(context, "Sorting by Avg Score ingnores the other search parameters", Toast.LENGTH_SHORT).show()
        }
        phoneViewModel.sortPhonesByAttributes(phonesToShow, selectedAttributes, sortOrder).forEach { phone ->
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
                HorizontalDivider()
            }
        }
    }
}