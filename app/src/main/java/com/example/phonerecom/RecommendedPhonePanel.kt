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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
/*
@Composable
fun RecommendedPhonePanel(navController: NavHostController, phoneViewModel: PhoneViewModel, /*phone: Phone?*/) {
    phone?.let {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Recommended Phone: ${phone.nombre}", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(top = 16.dp))
            phone.attributes.forEach { (key, attribute) ->
                Text("$key: ${attribute.specification} (Score: ${attribute.score})")
            }
        }
    }
}*/