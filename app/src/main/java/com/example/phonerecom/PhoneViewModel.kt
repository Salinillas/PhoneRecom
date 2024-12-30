package com.example.phonerecom

import androidx.activity.result.launch
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class PhoneViewModel(private val firebaseManager: FirebaseManager) : ViewModel() {
    private val phones = mutableStateListOf<Phone>()
    private val selectedPhone = mutableStateOf<Phone?>(null)
    init {
        viewModelScope.launch {
            loadPhonesFromDatabase()
        }
    }
    private suspend fun loadPhonesFromDatabase() {
        phones.clear()
        phones.addAll(firebaseManager.getAllPhones())
    }
    fun getCurrentUser(): User? {
        return firebaseManager.getCurrentUser()
    }
    fun getSelectedPhone(): Phone? {
        return selectedPhone.value
    }
    fun setSelectedPhone(phone: Phone?) {
        selectedPhone.value = phone
    }
    fun getAllPhones(): List<Phone> {
        return phones
    }
    fun getPhone(phoneName: String): Phone? {
        for (phone in phones) {
            if (phone.name == phoneName) {
                return phone
            }
        }
        return null
    }
    fun phoneExists(phoneName: String): Boolean {
        for (phone in phones) {
            if (phone.name == phoneName) {
                return true
            }
        }
        return false
    }
    fun deletePhone(phoneName: String) {
        var success = false
        viewModelScope.launch {
            success = firebaseManager.deletePhone(phoneName)
            if (success) {
                phones.removeIf { it.name == phoneName }
            }
        }
    }
    fun updatePhone(phone: Phone) {
        viewModelScope.launch {
            firebaseManager.updatePhone(phone)
            loadPhonesFromDatabase()
        }
    }
    fun addPhone(phone: Phone): Boolean {
        var success = false
        viewModelScope.launch {
            success = firebaseManager.addPhone(phone)
            if (success) {
                phones.add(phone)
            }
        }.invokeOnCompletion {
            return@invokeOnCompletion
        }
        return success
    }
/*
    fun getBestPhoneByBudget(budget: Float): Phone? {
        return phones
            .filter { phone ->
                val price = phone.attributes["Price"]?.specification?.replace(Regex("[^\\d.]"), "")?.toFloatOrNull()
                price != null && price in (budget - 50)..(budget + 50)
            }
            .maxByOrNull { phone ->
                phone.attributes.values.map { it.score }.average()
            }
    }*/

/*
fun getBestPhoneByBudget(budget: Float): Phone? {
    return phones
        .filter { phone ->
            val priceAttribute = phone.attributes["Price"]
            val price = phone.attributes["Price"]?.specification?.replace(Regex("[^\\d.]"), "")?.toFloatOrNull()
            //val price = priceAttribute?.specification?.replace(Regex("[^\\d.]"), "")?.toFloatOrNull()
            price != null && price in (budget - 50)..(budget + 50)
        }
        .maxByOrNull { phone ->
            phone.attributes.values.map { it.score }.average()
        }
}*/
/*
    fun getBestPhoneBySelectedAttributesWithinBudget(selectedAttributes: List<String>, minBudget: Float, maxBudget: Float): Phone? {
        return phones
            .filter { phone ->
                val price = phone.attributes["Price"]?.specification?.replace(Regex("[^\\d.]"), "")?.toFloatOrNull()
                price != null && price in (minBudget)..(maxBudget)
            }
            .maxByOrNull { phone ->
                selectedAttributes.sumOf { attribute ->
                    phone.attributes[attribute]?.score?.toDouble() ?: 0.0
                }
            }
    }*/

    fun getBestPhoneByBudget(budget: Float): List<Phone> {
        return phones
            .filter { phone ->
                val price = phone.attributes["Price"]?.specification?.replace(Regex("[^\\d.]"), "")?.toFloatOrNull()
                price != null && price in (budget - 50)..(budget + 50)
            }
            .sortedByDescending { phone ->
                phone.attributes.values.map { it.score }.average()
            }
            .take(3) // Tomar los primeros 3 elementos de la lista
    }


        fun getBestPhoneBySelectedAttributesWithinBudget(selectedAttributes: List<String>, minBudget: Float, maxBudget: Float): List<Phone> {
            return phones
                .filter { phone ->
                    val price = phone.attributes["Price"]?.specification?.replace(Regex("[^\\d.]"), "")?.toFloatOrNull()
                    price != null && price in (minBudget)..(maxBudget)
                }
                .sortedByDescending { phone ->
                    selectedAttributes.sumOf { attribute ->
                        phone.attributes[attribute]?.score?.toDouble() ?: 0.0
                    }
                }
                .take(3) // Tomar los primeros 3 elementos de la lista
        }


    /*
    fun getBestPhoneBySelectedAttributesWithinBudget(selectedAttributes: List<String>, minBudget: Float, maxBudget: Float): Phone? {
        return phones
            .filter { phone ->
                val price = phone.attributes["Price"]?.specification?.replace(Regex("[^\\d.]"), "")?.toFloatOrNull()
                price != null && price in (minBudget)..(maxBudget)
            }
            .maxByOrNull { phone ->
                selectedAttributes.sumOf { attribute ->
                    phone.attributes[attribute]?.score?.toDouble() ?: 0.0
                }
            }
    }*/
/*
    fun sortPhonesByAttributes(phones: List<Phone>, attributes: List<String>, order: String): List<Phone> {
        return when (order) {
            "Ascending" -> phones.sortedBy { phone ->
                attributes.sumOf { attribute ->
                    phone.attributes[attribute]?.score?.toDouble() ?: 0.0
                }
            }
            "Descending" -> phones.sortedByDescending { phone ->
                attributes.sumOf { attribute ->
                    phone.attributes[attribute]?.score?.toDouble() ?: 0.0
                }
            }
            "Avg Score" -> phones.sortedByDescending { phone ->
                phone.attributes.values.map { it.score }.average()
            }
            else -> phones.sortedBy { phone -> phone.name }
        }
    }*/
    fun sortPhonesByAttributes(phones: List<Phone>, attributes: List<String>, order: String): List<Phone> {
        return when (order) {
            "Ascending" -> phones.sortedBy { phone ->
                attributes.sumOf { attribute ->
                    phone.attributes[attribute]?.score?.toDouble() ?: 0.0
                }
            }
            "Descending" -> phones.sortedByDescending { phone ->
                attributes.sumOf { attribute ->
                    phone.attributes[attribute]?.score?.toDouble() ?: 0.0
                }
            }
            "Avg Score" -> phones.sortedByDescending { phone ->
                phone.attributes.values.map { it.score }.average()
            }
            else -> phones.sortedBy { phone -> phone.name }
        }
    }
    fun sortPhonesByName(searchString: String): List<Phone> {
        return phones.filter { it.name.contains(searchString, ignoreCase = true) }
            .sortedBy { it.name }
    }


}