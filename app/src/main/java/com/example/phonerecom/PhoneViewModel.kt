package com.example.phonerecom

import androidx.compose.runtime.mutableStateListOf
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

    /*fun getPhonesByPriceRange(minPrice: Float, maxPrice: Float): List<Phone> {
        return dbHelper.getAllPhones().filter { phone ->
            val price = phone.attributes["Price"]?.specification?.removePrefix("$")?.toFloatOrNull()
            price != null && price in minPrice..maxPrice
        }
    }*/
    fun getPhonesByPriceRange(minPrice: Float, maxPrice: Float): List<Phone> {
        return dbHelper.getPhonesByPriceRange(minPrice, maxPrice)
    }
    /*fun getBestPhoneWithinBudget(budget: Float): Phone? {
        return phones
            .filter { it.attributes["Price"]?.specification?.toFloatOrNull()?.let { price -> price in (budget - 50)..(budget + 50) } == true }
            .maxByOrNull { it.attributes.values.sumOf { attribute -> attribute.score?.toFloat() ?: 0f } }
    }*/
    fun getBestPhoneByAttributeWithinBudget(attribute: String, budget: Float): Phone? {
        return dbHelper.getAllPhones()
            .filter { it.attributes["Price"]?.specification?.removePrefix("$")?.toFloatOrNull()?.let { price -> price in (budget - 50)..(budget + 50) } == true }
            .maxByOrNull { it.attributes[attribute]?.score ?: 0f }
    }

    fun getBestPhoneWithinBudget(price: Float): Phone? {
        return dbHelper.getAllPhones()
            .filter { it.attributes["Price"]?.specification?.removePrefix("$")?.toFloatOrNull()?.let { phonePrice -> phonePrice in (price - 50)..(price + 50) } == true }
            .maxWithOrNull(compareBy { it.attributes.values.sumOf { attribute -> attribute.score.toDouble() ?: 0.0 } })
    }
    fun getPhonesSortedAscending(attribute: String): List<Phone> {
        return phones.sortedBy { phone ->
            phone.attributes[attribute]?.specification?.removePrefix("$")?.toFloatOrNull() ?: Float.MAX_VALUE
        }
    }

    fun getPhonesSortedDescending(attribute: String): List<Phone> {
        return phones.sortedByDescending { phone ->
            phone.attributes[attribute]?.specification?.removePrefix("$")?.toFloatOrNull() ?: Float.MIN_VALUE
        }
    }

}