package com.example.phonerecom
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun PhoneAdditionPanel(navController: NavHostController, phoneViewModel: PhoneViewModel) {
    var phoneName by remember { mutableStateOf("") }
    var software by remember { mutableStateOf("") }
    var softwareScore by remember { mutableStateOf("") }
    var screen by remember { mutableStateOf("") }
    var screenScore by remember { mutableStateOf("") }
    var camera by remember { mutableStateOf("") }
    var cameraScore by remember { mutableStateOf("") }
    var battery by remember { mutableStateOf("") }
    var batteryScore by remember { mutableStateOf("") }
    var buildQuality by remember { mutableStateOf("") }
    var buildQualityScore by remember { mutableStateOf("") }
    var speaker by remember { mutableStateOf("") }
    var speakerScore by remember { mutableStateOf("") }
    var microphone by remember { mutableStateOf("") }
    var microphoneScore by remember { mutableStateOf("") }
    var ram by remember { mutableStateOf("") }
    var ramScore by remember { mutableStateOf("") }
    var internalMemory by remember { mutableStateOf("") }
    var internalMemoryScore by remember { mutableStateOf("") }
    var cpu by remember { mutableStateOf("") }
    var cpuScore by remember { mutableStateOf("") }
    var gpu by remember { mutableStateOf("") }
    var gpuScore by remember { mutableStateOf("") }
    var size by remember { mutableStateOf("") }
    var sizeScore by remember { mutableStateOf("") }
    var reviews by remember { mutableStateOf("") }
    var reviewsScore by remember { mutableStateOf("") }
    var userOpinions by remember { mutableStateOf("") }
    var userOpinionsScore by remember { mutableStateOf("") }
    var popularity by remember { mutableStateOf("") }
    var popularityScore by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var priceScore by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { navController.navigate("phone_panel") }) {
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
        )
        OutlinedTextField(
            value = userOpinions,
            onValueChange = { userOpinions = it },
            label = { Text("User Opinions") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
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

        Button(
            onClick = {
                val phone = Phone(
                    id = 0,
                    nombre = phoneName,
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
                        "User_Opinions" to PhoneAttribute(userOpinions, userOpinionsScore.toFloatOrNull() ?: 0f),
                        "Popularity" to PhoneAttribute(popularity, popularityScore.toFloatOrNull() ?: 0f),
                        "Price" to PhoneAttribute(price, priceScore.toFloatOrNull() ?: 0f)
                    )
                )
                phoneViewModel.addPhone(phone)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Add Phone")
        }
    }
}