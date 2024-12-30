package com.example.phonerecom

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun ModifyPhonePanel(navController: NavHostController, phoneViewModel: PhoneViewModel) {
    val selectedPhone by remember { mutableStateOf(phoneViewModel.getSelectedPhone()) }
    var phoneName by remember { mutableStateOf(selectedPhone?.name ?: "") }

    var software by remember { mutableStateOf(selectedPhone?.attributes?.get("Software")?.specification ?: "") }
    var softwareScore by remember { mutableStateOf(selectedPhone?.attributes?.get("Software")?.score?.toString() ?: "") }
    var screen by remember { mutableStateOf(selectedPhone?.attributes?.get("Screen")?.specification ?: "") }
    var screenScore by remember { mutableStateOf(selectedPhone?.attributes?.get("Screen")?.score?.toString() ?: "") }
    var camera by remember { mutableStateOf(selectedPhone?.attributes?.get("Camera")?.specification ?: "") }
    var cameraScore by remember { mutableStateOf(selectedPhone?.attributes?.get("Camera")?.score?.toString() ?: "") }
    var battery by remember { mutableStateOf(selectedPhone?.attributes?.get("Battery")?.specification ?: "") }
    var batteryScore by remember { mutableStateOf(selectedPhone?.attributes?.get("Battery")?.score?.toString() ?: "") }
    var buildQuality by remember { mutableStateOf(selectedPhone?.attributes?.get("Build_Quality")?.specification ?: "") }
    var buildQualityScore by remember { mutableStateOf(selectedPhone?.attributes?.get("Build_Quality")?.score?.toString() ?: "") }
    var speaker by remember { mutableStateOf(selectedPhone?.attributes?.get("Speaker")?.specification ?: "") }
    var speakerScore by remember { mutableStateOf(selectedPhone?.attributes?.get("Speaker")?.score?.toString() ?: "") }
    var microphone by remember { mutableStateOf(selectedPhone?.attributes?.get("Microphone")?.specification ?: "") }
    var microphoneScore by remember { mutableStateOf(selectedPhone?.attributes?.get("Microphone")?.score?.toString() ?: "") }
    var ram by remember { mutableStateOf(selectedPhone?.attributes?.get("RAM")?.specification ?: "") }
    var ramScore by remember { mutableStateOf(selectedPhone?.attributes?.get("RAM")?.score?.toString() ?: "") }
    var internalMemory by remember { mutableStateOf(selectedPhone?.attributes?.get("Internal_Memory")?.specification ?: "") }
    var internalMemoryScore by remember { mutableStateOf(selectedPhone?.attributes?.get("Internal_Memory")?.score?.toString() ?: "") }
    var cpu by remember { mutableStateOf(selectedPhone?.attributes?.get("CPU")?.specification ?: "") }
    var cpuScore by remember { mutableStateOf(selectedPhone?.attributes?.get("CPU")?.score?.toString() ?: "") }
    var gpu by remember { mutableStateOf(selectedPhone?.attributes?.get("GPU")?.specification ?: "") }
    var gpuScore by remember { mutableStateOf(selectedPhone?.attributes?.get("GPU")?.score?.toString() ?: "") }
    var size by remember { mutableStateOf(selectedPhone?.attributes?.get("Size")?.specification ?: "") }
    var sizeScore by remember { mutableStateOf(selectedPhone?.attributes?.get("Size")?.score?.toString() ?: "") }
    var reviews by remember { mutableStateOf(selectedPhone?.attributes?.get("Reviews")?.specification ?: "") }
    var reviewsScore by remember { mutableStateOf(selectedPhone?.attributes?.get("Reviews")?.score?.toString() ?: "") }
    var userOpinions by remember { mutableStateOf(selectedPhone?.attributes?.get("User_Opinion")?.specification ?: "") }
    var userOpinionsScore by remember { mutableStateOf(selectedPhone?.attributes?.get("User_Opinion")?.score?.toString() ?: "") }
    var popularity by remember { mutableStateOf(selectedPhone?.attributes?.get("Popularity")?.specification ?: "") }
    var popularityScore by remember { mutableStateOf(selectedPhone?.attributes?.get("Popularity")?.score?.toString() ?: "") }
    var price by remember { mutableStateOf(selectedPhone?.attributes?.get("Price")?.specification ?: "") }
    var priceScore by remember { mutableStateOf(selectedPhone?.attributes?.get("Price")?.score?.toString() ?: "") }
    var photoUrl by remember { mutableStateOf(selectedPhone?.photoUrl ?: "") }
    val comments by remember { mutableStateOf(selectedPhone?.comments ?: emptyList()) }
    //var attributes = remember { mutableStateMapOf<String, MutableState<PhoneAttribute>>() }
    //var comments = remember { mutableStateListOf<Comment>() }


/*
    selectedPhone?.attributes?.forEach { (key, attribute) ->
        attributes[key] = mutableStateOf(attribute)
    }*//*
    selectedPhone?.comments?.let { commentList ->
        comments.addAll(commentList)
    }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        IconButton(onClick = { navController.navigate("phone_deletion_panel") }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        OutlinedTextField(
            value = phoneName,
            onValueChange = { phoneName = it },
            label = { Text("Phone Name") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = software,
            onValueChange = { software = it },
            label = { Text("Software") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = softwareScore,
            onValueChange = { softwareScore = it },
            label = { Text("Software Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = screen,
            onValueChange = { screen = it },
            label = { Text("Screen") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = screenScore,
            onValueChange = { screenScore = it },
            label = { Text("Screen Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = camera,
            onValueChange = { camera = it },
            label = { Text("Camera") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = cameraScore,
            onValueChange = { cameraScore = it },
            label = { Text("Camera Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = battery,
            onValueChange = { battery = it },
            label = { Text("Battery") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = batteryScore,
            onValueChange = { batteryScore = it },
            label = { Text("Battery Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = buildQuality,
            onValueChange = { buildQuality = it },
            label = { Text("Build Quality") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = buildQualityScore,
            onValueChange = { buildQualityScore = it },
            label = { Text("Build Quality Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = speaker,
            onValueChange = { speaker = it },
            label = { Text("Speaker") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = speakerScore,
            onValueChange = { speakerScore = it },
            label = { Text("Speaker Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = microphone,
            onValueChange = { microphone = it },
            label = { Text("Microphone") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = microphoneScore,
            onValueChange = { microphoneScore = it },
            label = { Text("Microphone Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = ram,
            onValueChange = { ram = it },
            label = { Text("RAM") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = ramScore,
            onValueChange = { ramScore = it },
            label = { Text("RAM Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = internalMemory,
            onValueChange = { internalMemory = it },
            label = { Text("Internal Memory") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = internalMemoryScore,
            onValueChange = { internalMemoryScore = it },
            label = { Text("Internal Memory Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = cpu,
            onValueChange = { cpu = it },
            label = { Text("CPU") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = cpuScore,
            onValueChange = { cpuScore = it },
            label = { Text("CPU Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = gpu,
            onValueChange = { gpu = it },
            label = { Text("GPU") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = gpuScore,
            onValueChange = { gpuScore = it },
            label = { Text("GPU Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = size,
            onValueChange = { size = it },
            label = { Text("Size") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = sizeScore,
            onValueChange = { sizeScore = it },
            label = { Text("Size Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = reviews,
            onValueChange = { reviews = it },
            label = { Text("Reviews") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = reviewsScore,
            onValueChange = { reviewsScore = it },
            label = { Text("Reviews Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )/*
        OutlinedTextField(
            value = userOpinions,
            onValueChange = { userOpinions = it },
            label = { Text("User Opinions") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )*/
        OutlinedTextField(
            value = userOpinionsScore,
            onValueChange = { userOpinionsScore = it },
            label = { Text("User Opinions Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = popularity,
            onValueChange = { popularity = it },
            label = { Text("Popularity") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = popularityScore,
            onValueChange = { popularityScore = it },
            label = { Text("Popularity Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = priceScore,
            onValueChange = { priceScore = it },
            label = { Text("Price Score") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = photoUrl,
            onValueChange = { photoUrl = it },
            label = { Text("Photo URL") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        if (photoUrl.isNotEmpty()) {
            AsyncImage(
                model = photoUrl,
                contentDescription = phoneName,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
/*
        Text("Comments:", style = MaterialTheme.typography.bodyLarge)
        comments.forEachIndexed { index, comment ->
            OutlinedTextField(
                value = comment.author,
                onValueChange = { comments[index] = comment.copy(author = it) },
                label = { Text("Author") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = comment.content,
                onValueChange = { comments[index] = comment.copy(content = it) },
                label = { Text("Content") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = comment.score.toString(),
                onValueChange = { comments[index] = comment.copy(content = it) },
                label = { Text("Content") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
        }
        userOpinions = if(userOpinionsScore.toFloat()<=2) {
            "Muy Malo"
        }else if(userOpinionsScore.toFloat()<=4){
            "Malo"
        }else if(userOpinionsScore.toFloat()<=6){
            "Regular"
        }else if(userOpinionsScore.toFloat()<=8){
            "Bueno"
        }else if(userOpinionsScore.toFloat()<=10){
            "Muy Bueno"
        }else{
            "No hay opiniones"
        }*/
        Button(
            onClick = {
                val phoneToUpdate = Phone(
                    name = phoneName,
                    attributes = mapOf(
                        "Software" to PhoneAttribute(software, softwareScore.toFloatOrNull() ?: 0f),
                        "Screen" to PhoneAttribute(screen, screenScore.toFloatOrNull() ?: 0f),
                        "Camera" to PhoneAttribute(camera, cameraScore.toFloatOrNull() ?: 0f),
                        "Battery" to PhoneAttribute(battery, batteryScore.toFloatOrNull() ?: 0f),
                        "Build_Quality" to PhoneAttribute(buildQuality, buildQualityScore.toFloatOrNull() ?: 0f),
                        "Speaker" to PhoneAttribute(speaker, speakerScore.toFloatOrNull() ?: 0f),
                        "Microphone" to PhoneAttribute(microphone, microphoneScore.toFloatOrNull() ?: 0f),
                        "RAM" to PhoneAttribute(ram, ramScore.toFloatOrNull() ?: 0f),
                        "Internal_Memory" to PhoneAttribute(internalMemory, internalMemoryScore.toFloatOrNull() ?: 0f),
                        "CPU" to PhoneAttribute(cpu, cpuScore.toFloatOrNull() ?: 0f),
                        "GPU" to PhoneAttribute(gpu, gpuScore.toFloatOrNull() ?: 0f),
                        "Size" to PhoneAttribute(size, sizeScore.toFloatOrNull() ?: 0f),
                        "Reviews" to PhoneAttribute(reviews, reviewsScore.toFloatOrNull() ?: 0f),
                        "User_Opinion" to PhoneAttribute(userOpinions, userOpinionsScore.toFloatOrNull() ?: 0f),
                        "Popularity" to PhoneAttribute(popularity, popularityScore.toFloatOrNull() ?: 0f),
                        "Price" to PhoneAttribute(price, priceScore.toFloatOrNull() ?: 0f)

                    ),
                    comments = comments,
                    photoUrl = photoUrl

                )
                /*
                // Update phone details in the ViewModel
                val updatedPhone = selectedPhone?.copy(
                    name = phoneName,
                    attributes = attributes.mapValues { it.value.value }.toMap(),
                    photoUrl = photoUrl,
                    comments = comments.toList()
                )*/
                selectedPhone?.let { phoneViewModel.deletePhone(it.name) }
                phoneViewModel.updatePhone(phoneToUpdate)

                navController.navigate("phone_panel")
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Save Changes")
        }
    }
}