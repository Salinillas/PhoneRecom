package com.example.phonerecom

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseManager(private val database: FirebaseFirestore) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    //private val phonesRef: DatabaseReference = database.getReference("phones")
    //private val nextPhoneIdRef: DatabaseReference = database.getReference("nextPhoneId")
    //private val usersRef: DatabaseReference = database.getReference("users")



        suspend fun registerUser(email: String, password: String, role: String): Boolean {
            return try {
                val userCredential = auth.createUserWithEmailAndPassword(email, password).await()
                val user = userCredential.user ?: return false

                val userData = User(username = email, password = password, role = role)
                database.collection("users").document(user.uid).set(userData).await()
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        suspend fun loginUser(email: String, password: String): Boolean {
            return try {
                auth.signInWithEmailAndPassword(email, password).await()
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        fun logoutUser() {
            auth.signOut()
        }

        fun getCurrentUser(): User? {
            val currentUser = auth.currentUser
            return if (currentUser != null) {
                // Retrieve additional user data from Firestore if needed
                User(username = currentUser.email ?: "", password = "", role = "user")
            } else {
                null
            }
        }


    suspend fun addUser(user: User): Boolean {
        return try {
            val userMap = hashMapOf(
                "username" to user.username,
                "password" to user.password,
                "role" to user.role
            )
            database.collection("users").document(user.username).set(userMap).await()
            true
        } catch (e: Exception) {
            false
        }
    }
    suspend fun getAllUsers(): List<User> {
        return try {
            val querySnapshot = database.collection("users").get().await()
            val users = mutableListOf<User>()
            for (document in querySnapshot.documents) {
                val username = document.getString("username") ?: ""
                val password = document.getString("password") ?: ""
                val role = document.getString("role") ?: ""
                val user = User(username, password, role)
                users.add(user)
            }
            users
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getUser(username: String): User? {
        return try {
            val document = database.collection("users").document(username).get().await()
            if (document.exists()) {
                val password = document.getString("password") ?: ""
                val role = document.getString("role") ?: ""
                User(username, password, role)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateUser(user: User): Boolean {
        return try {
            val userMap = hashMapOf(
                "username" to user.username,
                "password" to user.password,
                "role" to user.role
            )
            database.collection("users").document(user.username).set(userMap).await()
            true
        } catch (e: Exception) {
            false
        }
    }


    suspend fun deleteUser(username: String): Boolean {
        return try {
            database.collection("users").document(username).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun userExists(username: String): Boolean {
        return try {
            val document = database.collection("users").document(username).get().await()
            document.exists()
        } catch (e: Exception) {
            false
        }
    }
    // Check user role
    suspend fun getUserRole(): String? {
        val user = auth.currentUser ?: return null
        return try {
            val documentSnapshot = database.collection("users").document(user.uid).get().await()
            documentSnapshot.getString("role")
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    suspend fun addPhone(phone: Phone): Boolean {
        return try {
            val phoneMap = hashMapOf(
                "name" to phone.name,
                "attributes" to phone.attributes,
                "photoUrl" to phone.photoUrl,
            )
            database.collection("phones").document(phone.name).set(phoneMap).await()
            true
        } catch (e: Exception) {
            false
        }
    }
/*
    // --- Read (Get All) ---
    suspend fun getAllPhones(): List<Phone> {
        return try {
            val querySnapshot = database.collection("phones").get().await()
            val phones = mutableListOf<Phone>()
            for (document in querySnapshot.documents) {
                val id = document.getLong("id")?.toInt() ?: 0
                val name = document.getString("name") ?: ""
                val attributes = (document.get("attributes") as? Map<String, PhoneAttribute> ?: emptyMap()) as MutableMap<String, PhoneAttribute>
                val phone = Phone(id, name, attributes)
                phones.add(phone)
            }
            phones
        } catch (e: Exception) {
            emptyList()
        }
    }*/





    suspend fun getAllPhones(): List<Phone> {
        return try {
            val querySnapshot = database.collection("phones").get().await()
            val phones = mutableListOf<Phone>()
            for (document in querySnapshot.documents) {
                val name = document.getString("name") ?: ""
                val attributesMap = document.get("attributes") as? Map<String, Map<String, Any>> ?: emptyMap()
                val attributes = attributesMap.mapValues { entry ->
                    val specification = entry.value["specification"] as? String ?: ""
                    val score = (entry.value["score"] as? Double)?.toFloat() ?: 0f
                    PhoneAttribute(specification, score)
                }.toMutableMap()
                val photoUrl = document.getString("photoUrl") ?: ""
                val comments = (document.get("comments") as? List<Map<String, Any>>)?.map { commentMap ->
                    Comment(
                        author = commentMap["author"] as? String ?: "",
                        content = commentMap["content"] as? String ?: "",
                        score = (commentMap["score"] as? Double)?.toFloat() ?: 0f
                    )
                } ?: emptyList()

                val phone = Phone(name, attributes, photoUrl, comments)
                phones.add(phone)
            }
            phones
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }







    // --- Read (Get One by ID) ---
    suspend fun getPhone(phoneName: String): Phone? {
        return try {
            val document = database.collection("phones").document(phoneName).get().await()
            if (document.exists()) {
                val name = document.getString("name") ?: ""
                val attributes = document.get("attributes") as? Map<String, PhoneAttribute> ?: emptyMap()
                Phone(name, attributes)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updatePhone(phone: Phone): Boolean {
        return try {
            val userOpinionsScore = if (phone.comments.isNotEmpty()) {
                phone.comments.map { it.score }.average().toFloat()
            } else {
                0f
            }
            val userOpinions = when {
                userOpinionsScore <= 2 -> "Muy Malo"
                userOpinionsScore <= 4 -> "Malo"
                userOpinionsScore <= 6 -> "Regular"
                userOpinionsScore <= 8 -> "Bueno"
                userOpinionsScore <= 10 -> "Muy Bueno"
                else -> "No hay opiniones"
            }
            val updatedAttributes = phone.attributes.toMutableMap()
            updatedAttributes["User_Opinion"] = PhoneAttribute(userOpinions, userOpinionsScore)

            val phoneMap = hashMapOf(
                "name" to phone.name,
                "attributes" to updatedAttributes,
                "photoUrl" to phone.photoUrl,
                "comments" to phone.comments.map { comment ->
                    hashMapOf(
                        "author" to comment.author,
                        "content" to comment.content,
                        "score" to comment.score
                    )
                }
            )
            database.collection("phones").document(phone.name).set(phoneMap).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

/*
    suspend fun updateUserOpinion(phone: Phone){
        val userOpinionsScore = if (phone.comments.isNotEmpty()) {
            phone.comments.map { it.score }.average().toFloat()
        } else {
            0f
        }
        val userOpinions = when {
            userOpinionsScore <= 2 -> "Muy Malo"
            userOpinionsScore <= 4 -> "Malo"
            userOpinionsScore <= 6 -> "Regular"
            userOpinionsScore <= 8 -> "Bueno"
            userOpinionsScore <= 10 -> "Muy Bueno"
            else -> "No hay opiniones"
        }
        val updatedAttributes = phone.attributes.toMutableMap()
        updatedAttributes["User Opinion"] = PhoneAttribute(userOpinions, userOpinionsScore)
        database.collection("phones").document(phone.name).set(phoneMap).await()
    }*/


    // --- Delete ---
    suspend fun deletePhone(phoneName: String): Boolean {
        return try {
            database.collection("phones").document(phoneName).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }

    // --- Exists (Check if a phone exists by ID) ---
    suspend fun phoneExists(phoneName: String): Boolean {
        return try {
            val document = database.collection("phones").document(phoneName).get().await()
            document.exists()
        } catch (e: Exception) {
            false
        }
    }
}
