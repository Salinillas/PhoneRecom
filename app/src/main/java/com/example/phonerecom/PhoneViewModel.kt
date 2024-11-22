package com.example.phonerecom

import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel


class PhoneViewModel(private val dbHelper: DatabaseHelper) : ViewModel() {
    private val phones = mutableStateListOf<Phone>()
    init {
        loadPhonesFromDatabase()
    }

    private fun loadPhonesFromDatabase() {
        phones.clear()
        phones.addAll(dbHelper.getAllPhones())
    }

    fun getAllPhones(): List<Phone> {
        return phones
    }

    fun deletePhone(phoneId: Int) {
        dbHelper.deletePhone(phoneId)
        phones.removeAll { it.id == phoneId }
    }

    fun addPhone(phone: Phone): Boolean {
        if (phones.any { it.nombre == phone.nombre }) {
            return false
        }
        val phoneId = dbHelper.addPhone(phone)
        if (phoneId != -1L) {
            phones.add(phone.copy(id = phoneId.toInt()))
            return true
        }
        return false
    }

    /*
    fun getPhonesByPriceRange(minPrice: Float, maxPrice: Float): List<Phone> {
        return dbHelper.getPhonesByPriceRange(minPrice, maxPrice)
    }

    fun getBestPhoneByAttributeWithinBudget(attribute: String, budget: Float): Phone? {
        return dbHelper.getAllPhones()
            .filter { it.attributes["Price"]?.specification?.removePrefix("$")?.toFloatOrNull()?.let { price -> price in (budget - 50)..(budget + 50) } == true }
            .maxByOrNull { it.attributes[attribute]?.score ?: 0f }
    }
    fun getBestPhoneBySelectedAttributesWithinBudget(selectedAttributes: List<String>, budget: Float): Phone? {
        return phones
            .filter { phone ->
                val price = phone.attributes["Price"]?.specification?.removePrefix("$")?.toFloatOrNull()
                price != null && price in (budget - 50)..(budget + 50)
            }
            .maxByOrNull { phone ->
                selectedAttributes.sumOf { attribute ->
                    phone.attributes[attribute]?.score?.toDouble() ?: 0.0
                }
            }
    }*/
    fun getBestPhoneByBudget(budget: Float): Phone? {
        return phones
            .filter { phone ->
                val price = phone.attributes["Price"]?.specification?.replace(Regex("[^\\d.]"), "")?.toFloatOrNull()
                price != null && price in (budget - 50)..(budget + 50)
            }
            .maxByOrNull { phone ->
                phone.attributes.values.map { it.score }.average()
            }
    }

    fun getBestPhoneBySelectedAttributesWithinBudget(selectedAttributes: List<String>, minBudget: Float,maxBudget:Float ): Phone? {
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
    }

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
            else -> phones.sortedBy {  phone -> phone.nombre }
        }
    }
}