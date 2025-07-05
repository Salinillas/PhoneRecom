package com.example.phonerecom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.phonerecom.ui.theme.PhoneRecomTheme
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val database = FirebaseFirestore.getInstance()
    lateinit var firebaseManager: FirebaseManager
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var phoneViewModel: PhoneViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //FirebaseApp.initializeApp(this)

        firebaseManager = FirebaseManager(database)

        // Initialize ViewModels after firebaseManager is ready
        loginViewModel =
            ViewModelProvider(
                this,
                LoginViewModelFactory(firebaseManager)
            )[LoginViewModel::class.java]
        phoneViewModel =
            ViewModelProvider(
                this,
                PhoneViewModelFactory(firebaseManager)
            )[PhoneViewModel::class.java]

        //loadInitialPhones()

        setContent {
            PhoneRecomTheme {
                val navController = rememberNavController()
                Scaffold { paddingValues ->
                    NavHost(
                        navController = navController,
                        //startDestination = "admin_panel",

                        startDestination = "login",
                        //startDestination = "user_panel",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("login") { LoginScreen(navController, loginViewModel) }
                        composable("admin_panel") { AdminPanel(navController, loginViewModel) }
                        composable("user_panel") { UserPanel(navController, loginViewModel) }
                        composable("phone_panel") { PhonePanel(navController) }
                        composable("phone_addition_panel") {
                            PhoneAdditionPanel(
                                navController,
                                phoneViewModel
                            )
                        }
                        composable("phone_deletion_panel") {
                            PhoneDeletionPanel(
                                navController,
                                phoneViewModel
                            )
                        }
                        composable("get_recommendation_panel") {
                            GetRecommendationPanel(
                                navController,
                                phoneViewModel
                            )
                        }
                        composable("View_Phones_Panel") {
                            ViewPhonesPanel(
                                navController,
                                phoneViewModel
                            )
                        }
                        composable("faq_panel") { FAQPanel(navController) }
                        composable("modify_phone_panel") {
                            ModifyPhonePanel(
                                navController,
                                phoneViewModel
                            )
                        }
                        composable("comment_phone_panel") {
                            CommentPhonePanel(
                                navController,
                                phoneViewModel
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadInitialPhones() {
        val initialPhones = listOf(
            Phone(
                name = "Samsung Galaxy S24",
                attributes = mapOf(
                    "Software" to PhoneAttribute("Android 14", 9.5f),
                    "Screen" to PhoneAttribute("6.2 inches", 10.0f),
                    "Camera" to PhoneAttribute("108 MP", 9.5f),
                    "Battery" to PhoneAttribute("4200 mAh", 9.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.5f),
                    "Microphone" to PhoneAttribute("Dual", 9.5f),
                    "RAM" to PhoneAttribute("12 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 10.0f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 3", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 750", 9.5f),
                    "Size" to PhoneAttribute("147 x 71 x 7.5 mm", 9.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$899", 9.75f)
                ),
                photoUrl = "https://images.samsung.com/is/image/samsung/assets/global/mkt/smartphones/galaxy-s24/gallery/galaxy-s24/S24-Color-Amber_Yellow_MO.jpg"
            ),
            Phone(
                name = "iPhone 16",
                attributes = mapOf(
                    "Software" to PhoneAttribute("iOS 18", 9.5f),
                    "Screen" to PhoneAttribute("6.2 inches", 9.5f),
                    "Camera" to PhoneAttribute("48 MP", 9.5f),
                    "Battery" to PhoneAttribute("3400 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.0f),
                    "CPU" to PhoneAttribute("A17 Bionic", 9.5f),
                    "GPU" to PhoneAttribute("Apple GPU", 9.5f),
                    "Size" to PhoneAttribute("147 x 71 x 7.5 mm", 9.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$999", 9.75f)
                )
            ),
            Phone(
                "Phone A", mapOf(
                    "Software" to PhoneAttribute("Android 11", 8.5f),
                    "Screen" to PhoneAttribute("6.5 inches", 9.0f),
                    "Camera" to PhoneAttribute("12 MP", 8.0f),
                    "Battery" to PhoneAttribute("4000 mAh", 7.5f),
                    "Build_Quality" to PhoneAttribute("Metal", 8.0f),
                    "Speaker" to PhoneAttribute("Stereo", 7.0f),
                    "Microphone" to PhoneAttribute("Dual", 7.5f),
                    "RAM" to PhoneAttribute("6 GB", 8.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 8.5f),
                    "CPU" to PhoneAttribute("Snapdragon 720G", 8.0f),
                    "GPU" to PhoneAttribute("Adreno 618", 7.5f),
                    "Size" to PhoneAttribute("160 x 75 x 8 mm", 7.0f),
                    "Reviews" to PhoneAttribute("Good", 8.0f),
                    "User_Opinion" to PhoneAttribute("Positive", 8.5f),
                    "Popularity" to PhoneAttribute("High", 9.0f),
                    "Price" to PhoneAttribute("$300", 8.0f)
                )
            ),
            Phone(
                "Phone B", mapOf(
                    "Software" to PhoneAttribute("iOS 14", 9.0f),
                    "Screen" to PhoneAttribute("5.8 inches", 8.5f),
                    "Camera" to PhoneAttribute("12 MP", 9.0f),
                    "Battery" to PhoneAttribute("3000 mAh", 7.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Mono", 6.5f),
                    "Microphone" to PhoneAttribute("Single", 7.0f),
                    "RAM" to PhoneAttribute("4 GB", 7.5f),
                    "Internal_Memory" to PhoneAttribute("64 GB", 7.0f),
                    "CPU" to PhoneAttribute("A13 Bionic", 9.5f),
                    "GPU" to PhoneAttribute("Apple GPU", 9.0f),
                    "Size" to PhoneAttribute("150 x 70 x 7 mm", 8.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$700", 7.5f)
                )
            ),
            Phone(
                "Phone C", mapOf(
                    "Software" to PhoneAttribute("Android 10", 7.5f),
                    "Screen" to PhoneAttribute("6.1 inches", 8.0f),
                    "Camera" to PhoneAttribute("16 MP", 8.5f),
                    "Battery" to PhoneAttribute("3500 mAh", 7.0f),
                    "Build_Quality" to PhoneAttribute("Plastic", 6.5f),
                    "Speaker" to PhoneAttribute("Mono", 6.0f),
                    "Microphone" to PhoneAttribute("Single", 6.5f),
                    "RAM" to PhoneAttribute("4 GB", 7.0f),
                    "Internal_Memory" to PhoneAttribute("64 GB", 7.5f),
                    "CPU" to PhoneAttribute("Snapdragon 665", 7.0f),
                    "GPU" to PhoneAttribute("Adreno 610", 6.5f),
                    "Size" to PhoneAttribute("155 x 75 x 8 mm", 7.0f),
                    "Reviews" to PhoneAttribute("Average", 7.0f),
                    "User_Opinion" to PhoneAttribute("Mixed", 6.5f),
                    "Popularity" to PhoneAttribute("Medium", 7.0f),
                    "Price" to PhoneAttribute("$200", 7.5f)
                )
            ),
            Phone(
                "Phone D", mapOf(
                    "Software" to PhoneAttribute("iOS 13", 8.5f),
                    "Screen" to PhoneAttribute("6.7 inches", 9.0f),
                    "Camera" to PhoneAttribute("12 MP", 9.0f),
                    "Battery" to PhoneAttribute("3700 mAh", 7.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 8.5f),
                    "Speaker" to PhoneAttribute("Stereo", 8.0f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("6 GB", 8.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 8.5f),
                    "CPU" to PhoneAttribute("A12 Bionic", 9.0f),
                    "GPU" to PhoneAttribute("Apple GPU", 8.5f),
                    "Size" to PhoneAttribute("160 x 75 x 7 mm", 8.0f),
                    "Reviews" to PhoneAttribute("Good", 8.5f),
                    "User_Opinion" to PhoneAttribute("Positive", 8.5f),
                    "Popularity" to PhoneAttribute("High", 8.5f),
                    "Price" to PhoneAttribute("$600", 8.0f)
                )
            ),
            Phone(
                "Phone E", mapOf(
                    "Software" to PhoneAttribute("Android 9", 6.5f),
                    "Screen" to PhoneAttribute("5.5 inches", 7.0f),
                    "Camera" to PhoneAttribute("8 MP", 6.5f),
                    "Battery" to PhoneAttribute("3000 mAh", 6.0f),
                    "Build_Quality" to PhoneAttribute("Plastic", 5.5f),
                    "Speaker" to PhoneAttribute("Mono", 5.0f),
                    "Microphone" to PhoneAttribute("Single", 5.5f),
                    "RAM" to PhoneAttribute("3 GB", 6.0f),
                    "Internal_Memory" to PhoneAttribute("32 GB", 6.5f),
                    "CPU" to PhoneAttribute("Snapdragon 450", 6.0f),
                    "GPU" to PhoneAttribute("Adreno 506", 5.5f),
                    "Size" to PhoneAttribute("150 x 70 x 8 mm", 6.0f),
                    "Reviews" to PhoneAttribute("Average", 6.0f),
                    "User_Opinion" to PhoneAttribute("Mixed", 5.5f),
                    "Popularity" to PhoneAttribute("Low", 6.0f),
                    "Price" to PhoneAttribute("$150", 6.5f)
                )
            ),
            Phone(
                "Phone F", mapOf(
                    "Software" to PhoneAttribute("iOS 12", 7.5f),
                    "Screen" to PhoneAttribute("5.8 inches", 8.0f),
                    "Camera" to PhoneAttribute("12 MP", 8.5f),
                    "Battery" to PhoneAttribute("3200 mAh", 7.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 8.0f),
                    "Speaker" to PhoneAttribute("Stereo", 7.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.0f),
                    "RAM" to PhoneAttribute("4 GB", 7.5f),
                    "Internal_Memory" to PhoneAttribute("64 GB", 7.5f),
                    "CPU" to PhoneAttribute("A11 Bionic", 8.0f),
                    "GPU" to PhoneAttribute("Apple GPU", 7.5f),
                    "Size" to PhoneAttribute("145 x 70 x 7 mm", 7.5f),
                    "Reviews" to PhoneAttribute("Good", 8.0f),
                    "User_Opinion" to PhoneAttribute("Positive", 8.0f),
                    "Popularity" to PhoneAttribute("High", 8.0f),
                    "Price" to PhoneAttribute("$500", 7.5f)
                )
            ),
            Phone(
                "Phone G", mapOf(
                    "Software" to PhoneAttribute("Android 11", 8.0f),
                    "Screen" to PhoneAttribute("6.4 inches", 8.5f),
                    "Camera" to PhoneAttribute("48 MP", 9.0f),
                    "Battery" to PhoneAttribute("4500 mAh", 8.0f),
                    "Build_Quality" to PhoneAttribute("Metal", 8.5f),
                    "Speaker" to PhoneAttribute("Stereo", 8.0f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 865", 9.0f),
                    "GPU" to PhoneAttribute("Adreno 650", 8.5f),
                    "Size" to PhoneAttribute("160 x 75 x 8 mm", 8.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$800", 8.5f)
                )
            ),
            Phone(
                "Phone H", mapOf(
                    "Software" to PhoneAttribute("iOS 15", 9.0f),
                    "Screen" to PhoneAttribute("6.1 inches", 9.0f),
                    "Camera" to PhoneAttribute("12 MP", 9.0f),
                    "Battery" to PhoneAttribute("3300 mAh", 7.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("6 GB", 8.5f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 8.5f),
                    "CPU" to PhoneAttribute("A14 Bionic", 9.5f),
                    "GPU" to PhoneAttribute("Apple GPU", 9.0f),
                    "Size" to PhoneAttribute("150 x 70 x 7 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$900", 8.5f)
                )
            ),
            Phone(
                "Phone I", mapOf(
                    "Software" to PhoneAttribute("Android 12", 8.5f),
                    "Screen" to PhoneAttribute("6.7 inches", 9.0f),
                    "Camera" to PhoneAttribute("64 MP", 9.5f),
                    "Battery" to PhoneAttribute("5000 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Metal", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("12 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 888", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 660", 9.0f),
                    "Size" to PhoneAttribute("165 x 75 x 8 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1000", 9.0f)
                )
            ),
            Phone(
                "Phone J", mapOf(
                    "Software" to PhoneAttribute("iOS 16", 9.5f),
                    "Screen" to PhoneAttribute("6.5 inches", 9.5f),
                    "Camera" to PhoneAttribute("12 MP", 9.5f),
                    "Battery" to PhoneAttribute("3500 mAh", 8.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("A15 Bionic", 9.5f),
                    "GPU" to PhoneAttribute("Apple GPU", 9.5f),
                    "Size" to PhoneAttribute("155 x 75 x 7 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1100", 9.0f)
                )
            ),
            Phone(
                "Phone K", mapOf(
                    "Software" to PhoneAttribute("Android 13", 9.0f),
                    "Screen" to PhoneAttribute("6.8 inches", 9.5f),
                    "Camera" to PhoneAttribute("108 MP", 9.5f),
                    "Battery" to PhoneAttribute("6000 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Metal", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("16 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("1 TB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 1", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 730", 9.5f),
                    "Size" to PhoneAttribute("170 x 80 x 8 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1200", 9.0f)
                )
            ),
            Phone(
                "Phone L", mapOf(
                    "Software" to PhoneAttribute("iOS 17", 9.5f),
                    "Screen" to PhoneAttribute("6.9 inches", 9.5f),
                    "Camera" to PhoneAttribute("12 MP", 9.5f),
                    "Battery" to PhoneAttribute("4000 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                    "CPU" to PhoneAttribute("A16 Bionic", 9.5f),
                    "GPU" to PhoneAttribute("Apple GPU", 9.5f),
                    "Size" to PhoneAttribute("160 x 75 x 7 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1300", 9.0f)
                )
            ),
            Phone(
                "Phone M", mapOf(
                    "Software" to PhoneAttribute("Android 11", 8.0f),
                    "Screen" to PhoneAttribute("6.3 inches", 8.5f),
                    "Camera" to PhoneAttribute("48 MP", 9.0f),
                    "Battery" to PhoneAttribute("4500 mAh", 8.0f),
                    "Build_Quality" to PhoneAttribute("Metal", 8.5f),
                    "Speaker" to PhoneAttribute("Stereo", 8.0f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 865", 9.0f),
                    "GPU" to PhoneAttribute("Adreno 650", 8.5f),
                    "Size" to PhoneAttribute("160 x 75 x 8 mm", 8.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$800", 8.5f)
                )
            ),
            Phone(
                "Samsung Galaxy S24", mapOf(
                    "Software" to PhoneAttribute("Android 14", 9.5f),
                    "Screen" to PhoneAttribute("6.2 inches", 10.0f),
                    "Camera" to PhoneAttribute("108 MP", 9.5f),
                    "Battery" to PhoneAttribute("4200 mAh", 9.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.5f),
                    "Microphone" to PhoneAttribute("Dual", 9.5f),
                    "RAM" to PhoneAttribute("12 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 10.0f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 3", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 750", 9.5f),
                    "Size" to PhoneAttribute("147 x 71 x 7.5 mm", 9.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$899", 9.75f)
                )
            ),

            Phone(
                "iPhone 16", mapOf(
                    "Software" to PhoneAttribute("iOS 18", 9.5f),
                    "Screen" to PhoneAttribute("6.2 inches", 9.5f),
                    "Camera" to PhoneAttribute("48 MP", 9.5f),
                    "Battery" to PhoneAttribute("3400 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.0f),
                    "CPU" to PhoneAttribute("A17 Bionic", 9.5f),
                    "GPU" to PhoneAttribute("Apple GPU", 9.5f),
                    "Size" to PhoneAttribute("147 x 72 x 7.8 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$999", 9.0f)
                )
            ),
            Phone(
                "Google Pixel 8", mapOf(
                    "Software" to PhoneAttribute("Android 14", 9.5f),
                    "Screen" to PhoneAttribute("6.4 inches", 9.75f),
                    "Camera" to PhoneAttribute("50 MP", 9.5f),
                    "Battery" to PhoneAttribute("4500 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.0f),
                    "CPU" to PhoneAttribute("Google Tensor G3", 9.0f),
                    "GPU" to PhoneAttribute("Mali-G710 MP8", 8.5f),
                    "Size" to PhoneAttribute("156 x 74 x 8.5 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$699", 8.5f)
                )
            ),
            Phone(
                "OnePlus 12", mapOf(
                    "Software" to PhoneAttribute("Android 14", 9.5f),
                    "Screen" to PhoneAttribute("6.8 inches", 9.5f),
                    "Camera" to PhoneAttribute("108 MP", 9.5f),
                    "Battery" to PhoneAttribute("5200 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("16 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 3", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 750", 9.5f),
                    "Size" to PhoneAttribute("164 x 75 x 8.5 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$899", 9.0f)
                )
            ),
            Phone(
                "Xiaomi 14 Pro", mapOf(
                    "Software" to PhoneAttribute("Android 14", 9.5f),
                    "Screen" to PhoneAttribute("6.75 inches", 9.0f),
                    "Camera" to PhoneAttribute("108 MP", 9.5f),
                    "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("12 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 3", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 750", 9.5f),
                    "Size" to PhoneAttribute("163 x 75 x 8.4 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$999", 9.0f)
                )
            ),
            Phone(
                "Sony Xperia 1 VI", mapOf(
                    "Software" to PhoneAttribute("Android 14", 9.5f),
                    "Screen" to PhoneAttribute("6.5 inches", 9.75f),
                    "Camera" to PhoneAttribute("48 MP", 9.5f),
                    "Battery" to PhoneAttribute("5100 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("12 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 3", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 750", 9.5f),
                    "Size" to PhoneAttribute("166 x 72 x 8.2 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1299", 9.0f)
                )
            ),
            Phone(
                "Oppo Find X6 Pro", mapOf(
                    "Software" to PhoneAttribute("Android 14", 9.5f),
                    "Screen" to PhoneAttribute("6.8 inches", 9.5f),
                    "Camera" to PhoneAttribute("108 MP", 9.5f),
                    "Battery" to PhoneAttribute("5200 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("16 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 3", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 750", 9.5f),
                    "Size" to PhoneAttribute("164 x 75 x 8.5 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1199", 9.0f)
                )
            ),
            Phone(
                "Huawei P70 Pro", mapOf(
                    "Software" to PhoneAttribute("HarmonyOS 4.0", 9.0f),
                    "Screen" to PhoneAttribute("6.7 inches", 9.0f),
                    "Camera" to PhoneAttribute("108 MP", 9.5f),
                    "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("12 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                    "CPU" to PhoneAttribute("Kirin 9000S", 9.5f),
                    "GPU" to PhoneAttribute("Mali-G78 MP24", 9.0f),
                    "Size" to PhoneAttribute("162 x 75 x 8.3 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1099", 9.0f)
                )
            ),
            Phone(
                "Asus ROG Phone 8", mapOf(
                    "Software" to PhoneAttribute("Android 14", 9.5f),
                    "Screen" to PhoneAttribute("6.8 inches", 9.5f),
                    "Camera" to PhoneAttribute("108 MP", 9.5f),
                    "Battery" to PhoneAttribute("6000 mAh", 9.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.5f),
                    "Microphone" to PhoneAttribute("Dual", 9.5f),
                    "RAM" to PhoneAttribute("16 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("1 TB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 3", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 750", 9.5f),
                    "Size" to PhoneAttribute("174 x 78 x 10.3 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1099", 9.0f)
                )
            ),
            Phone(
                "Nokia X60", mapOf(
                    "Software" to PhoneAttribute("Android 14", 9.0f),
                    "Screen" to PhoneAttribute("6.7 inches", 9.0f),
                    "Camera" to PhoneAttribute("108 MP", 9.5f),
                    "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("12 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("512 GB", 9.0f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 8.5f),
                    "GPU" to PhoneAttribute("Adreno 740", 8.5f),
                    "Size" to PhoneAttribute("165 x 76 x 8.7 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$799", 8.5f)
                )
            ),
            Phone(
                "Motorola Edge 3", mapOf(
                    "Software" to PhoneAttribute("Android 14", 9.0f),
                    "Screen" to PhoneAttribute("6.7 inches", 9.0f),
                    "Camera" to PhoneAttribute("108 MP", 9.5f),
                    "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("12 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("512 GB", 9.0f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 8.5f),
                    "GPU" to PhoneAttribute("Adreno 740", 8.5f),
                    "Size" to PhoneAttribute("165 x 76 x 8.7 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$799", 8.5f)
                )
            ),
            Phone(
                "Samsung Galaxy S23", mapOf(
                    "Software" to PhoneAttribute("Android 13", 9.5f),
                    "Screen" to PhoneAttribute("6.1 inches", 9.5f),
                    "Camera" to PhoneAttribute("50 MP", 9.5f),
                    "Battery" to PhoneAttribute("3900 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 740", 9.5f),
                    "Size" to PhoneAttribute("146.3 x 70.9 x 7.6 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$799", 9.0f)
                )
            ),
            Phone(
                "iPhone 15", mapOf(
                    "Software" to PhoneAttribute("iOS 17", 9.5f),
                    "Screen" to PhoneAttribute("6.1 inches", 9.5f),
                    "Camera" to PhoneAttribute("48 MP", 9.5f),
                    "Battery" to PhoneAttribute("3279 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("6 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                    "CPU" to PhoneAttribute("A16 Bionic", 9.5f),
                    "GPU" to PhoneAttribute("Apple GPU", 9.5f),
                    "Size" to PhoneAttribute("146.7 x 71.5 x 7.8 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$899", 9.0f)
                )
            ),
            Phone(
                "Google Pixel 7", mapOf(
                    "Software" to PhoneAttribute("Android 13", 9.5f),
                    "Screen" to PhoneAttribute("6.3 inches", 9.0f),
                    "Camera" to PhoneAttribute("50 MP", 9.5f),
                    "Battery" to PhoneAttribute("4355 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                    "CPU" to PhoneAttribute("Google Tensor G2", 9.0f),
                    "GPU" to PhoneAttribute("Mali-G710 MP7", 8.5f),
                    "Size" to PhoneAttribute("155.6 x 73.2 x 8.7 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$599", 8.5f)
                )
            ),
            Phone(
                "OnePlus 11", mapOf(
                    "Software" to PhoneAttribute("Android 13", 9.5f),
                    "Screen" to PhoneAttribute("6.7 inches", 9.5f),
                    "Camera" to PhoneAttribute("50 MP", 9.5f),
                    "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("12 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 740", 9.5f),
                    "Size" to PhoneAttribute("163.1 x 74.1 x 8.5 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$799", 9.0f)
                )
            ),
            Phone(
                "Xiaomi 13 Pro", mapOf(
                    "Software" to PhoneAttribute("Android 13", 9.5f),
                    "Screen" to PhoneAttribute("6.73 inches", 9.5f),
                    "Camera" to PhoneAttribute("50 MP", 9.5f),
                    "Battery" to PhoneAttribute("4820 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("12 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 740", 9.5f),
                    "Size" to PhoneAttribute("162.9 x 74.6 x 8.4 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$999", 9.0f)
                )
            ),
            Phone(
                "Sony Xperia 1 V", mapOf(
                    "Software" to PhoneAttribute("Android 13", 9.5f),
                    "Screen" to PhoneAttribute("6.5 inches", 9.5f),
                    "Camera" to PhoneAttribute("48 MP", 9.5f),
                    "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("12 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 740", 9.5f),
                    "Size" to PhoneAttribute("165 x 71 x 8.2 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1299", 9.0f)
                )
            ),
            Phone(
                "Oppo Find X5 Pro", mapOf(
                    "Software" to PhoneAttribute("Android 13", 9.5f),
                    "Screen" to PhoneAttribute("6.7 inches", 9.5f),
                    "Camera" to PhoneAttribute("50 MP", 9.5f),
                    "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("12 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 740", 9.5f),
                    "Size" to PhoneAttribute("163.7 x 73.9 x 8.5 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1199", 9.0f)
                )
            ),
            Phone(
                "Huawei P60 Pro", mapOf(
                    "Software" to PhoneAttribute("HarmonyOS 3.0", 9.0f),
                    "Screen" to PhoneAttribute("6.6 inches", 9.0f),
                    "Camera" to PhoneAttribute("50 MP", 9.5f),
                    "Battery" to PhoneAttribute("4815 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 8+ Gen 1", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 730", 9.0f),
                    "Size" to PhoneAttribute("161 x 74.5 x 8.3 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1099", 9.0f)
                )
            ),
            Phone(
                "Asus ROG Phone 7", mapOf(
                    "Software" to PhoneAttribute("Android 13", 9.5f),
                    "Screen" to PhoneAttribute("6.78 inches", 9.5f),
                    "Camera" to PhoneAttribute("50 MP", 9.5f),
                    "Battery" to PhoneAttribute("6000 mAh", 9.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.5f),
                    "Microphone" to PhoneAttribute("Dual", 9.5f),
                    "RAM" to PhoneAttribute("16 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 9.5f),
                    "GPU" to PhoneAttribute("Adreno 740", 9.5f),
                    "Size" to PhoneAttribute("173 x 77 x 10.3 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$999", 9.0f)
                )
            ),
            Phone(
                "Nokia X50", mapOf(
                    "Software" to PhoneAttribute("Android 13", 9.0f),
                    "Screen" to PhoneAttribute("6.67 inches", 9.0f),
                    "Camera" to PhoneAttribute("108 MP", 9.5f),
                    "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.0f),
                    "CPU" to PhoneAttribute("Snapdragon 778G", 8.5f),
                    "GPU" to PhoneAttribute("Adreno 642L", 8.5f),
                    "Size" to PhoneAttribute("164.9 x 76.2 x 8.7 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$699", 8.5f)
                )
            ),
            Phone(
                "Samsung Galaxy Note 20", mapOf(
                    "Software" to PhoneAttribute("Android 10", 8.5f),
                    "Screen" to PhoneAttribute("6.7 inches", 9.0f),
                    "Camera" to PhoneAttribute("64 MP", 9.0f),
                    "Battery" to PhoneAttribute("4300 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.0f),
                    "CPU" to PhoneAttribute("Exynos 990", 9.0f),
                    "GPU" to PhoneAttribute("Mali-G77 MP11", 8.5f),
                    "Size" to PhoneAttribute("161.6 x 75.2 x 8.3 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$999", 8.5f)
                )
            ),
            Phone(
                "iPhone 12", mapOf(
                    "Software" to PhoneAttribute("iOS 14", 9.0f),
                    "Screen" to PhoneAttribute("6.1 inches", 9.0f),
                    "Camera" to PhoneAttribute("12 MP", 9.0f),
                    "Battery" to PhoneAttribute("2815 mAh", 7.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("4 GB", 8.5f),
                    "Internal_Memory" to PhoneAttribute("64 GB", 8.5f),
                    "CPU" to PhoneAttribute("A14 Bionic", 9.5f),
                    "GPU" to PhoneAttribute("Apple GPU", 9.5f),
                    "Size" to PhoneAttribute("146.7 x 71.5 x 7.4 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$799", 8.5f)
                )
            ),
            Phone(
                "Google Pixel 5", mapOf(
                    "Software" to PhoneAttribute("Android 11", 9.0f),
                    "Screen" to PhoneAttribute("6.0 inches", 8.5f),
                    "Camera" to PhoneAttribute("12.2 MP", 9.0f),
                    "Battery" to PhoneAttribute("4080 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Aluminum", 8.5f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                    "CPU" to PhoneAttribute("Snapdragon 765G", 8.5f),
                    "GPU" to PhoneAttribute("Adreno 620", 8.0f),
                    "Size" to PhoneAttribute("144.7 x 70.4 x 8 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$699", 8.5f)
                )
            ),
            Phone(
                "OnePlus 8T", mapOf(
                    "Software" to PhoneAttribute("Android 11", 9.0f),
                    "Screen" to PhoneAttribute("6.55 inches", 9.0f),
                    "Camera" to PhoneAttribute("48 MP", 9.0f),
                    "Battery" to PhoneAttribute("4500 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("12 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 865", 9.0f),
                    "GPU" to PhoneAttribute("Adreno 650", 8.5f),
                    "Size" to PhoneAttribute("160.7 x 74.1 x 8.4 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$749", 8.5f)
                )
            ),
            Phone(
                "Xiaomi Mi 10", mapOf(
                    "Software" to PhoneAttribute("Android 10", 8.5f),
                    "Screen" to PhoneAttribute("6.67 inches", 9.0f),
                    "Camera" to PhoneAttribute("108 MP", 9.5f),
                    "Battery" to PhoneAttribute("4780 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                    "CPU" to PhoneAttribute("Snapdragon 865", 9.0f),
                    "GPU" to PhoneAttribute("Adreno 650", 8.5f),
                    "Size" to PhoneAttribute("162.5 x 74.8 x 9 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$699", 8.5f)
                )
            ),
            Phone(
                "Sony Xperia 5 II", mapOf(
                    "Software" to PhoneAttribute("Android 10", 8.5f),
                    "Screen" to PhoneAttribute("6.1 inches", 9.0f),
                    "Camera" to PhoneAttribute("12 MP", 9.0f),
                    "Battery" to PhoneAttribute("4000 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                    "CPU" to PhoneAttribute("Snapdragon 865", 9.0f),
                    "GPU" to PhoneAttribute("Adreno 650", 8.5f),
                    "Size" to PhoneAttribute("158 x 68 x 8 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$949", 8.5f)
                )
            ),
            Phone(
                "Oppo Reno 4 Pro", mapOf(
                    "Software" to PhoneAttribute("Android 10", 8.5f),
                    "Screen" to PhoneAttribute("6.5 inches", 9.0f),
                    "Camera" to PhoneAttribute("48 MP", 9.0f),
                    "Battery" to PhoneAttribute("4000 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                    "CPU" to PhoneAttribute("Snapdragon 720G", 8.5f),
                    "GPU" to PhoneAttribute("Adreno 618", 8.0f),
                    "Size" to PhoneAttribute("160.2 x 73.2 x 7.7 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$599", 8.5f)
                )
            ),
            Phone(
                "Huawei Mate 40 Pro", mapOf(
                    "Software" to PhoneAttribute("Android 10", 8.5f),
                    "Screen" to PhoneAttribute("6.76 inches", 9.0f),
                    "Camera" to PhoneAttribute("50 MP", 9.5f),
                    "Battery" to PhoneAttribute("4400 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("Kirin 9000", 9.0f),
                    "GPU" to PhoneAttribute("Mali-G78 MP24", 8.5f),
                    "Size" to PhoneAttribute("162.9 x 75.5 x 9.1 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1099", 8.5f)
                )
            ),
            Phone(
                "Asus Zenfone 7 Pro", mapOf(
                    "Software" to PhoneAttribute("Android 10", 8.5f),
                    "Screen" to PhoneAttribute("6.67 inches", 9.0f),
                    "Camera" to PhoneAttribute("64 MP", 9.0f),
                    "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 865+", 9.0f),
                    "GPU" to PhoneAttribute("Adreno 650", 8.5f),
                    "Size" to PhoneAttribute("165.1 x 77.3 x 9.6 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$799", 8.5f)
                )
            ),
            Phone(
                "Nokia 9 PureView", mapOf(
                    "Software" to PhoneAttribute("Android 9", 8.0f),
                    "Screen" to PhoneAttribute("5.99 inches", 8.5f),
                    "Camera" to PhoneAttribute("12 MP", 8.5f),
                    "Battery" to PhoneAttribute("3320 mAh", 7.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 8.5f),
                    "Speaker" to PhoneAttribute("Mono", 7.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.0f),
                    "RAM" to PhoneAttribute("6 GB", 8.5f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 8.5f),
                    "CPU" to PhoneAttribute("Snapdragon 845", 8.5f),
                    "GPU" to PhoneAttribute("Adreno 630", 8.0f),
                    "Size" to PhoneAttribute("155 x 75 x 8 mm", 8.0f),
                    "Reviews" to PhoneAttribute("Good", 8.5f),
                    "User_Opinion" to PhoneAttribute("Positive", 8.5f),
                    "Popularity" to PhoneAttribute("High", 8.5f),
                    "Price" to PhoneAttribute("$699", 8.0f)
                )
            ),
            Phone(
                "Samsung Galaxy S21", mapOf(
                    "Software" to PhoneAttribute("Android 11", 9.0f),
                    "Screen" to PhoneAttribute("6.2 inches", 9.0f),
                    "Camera" to PhoneAttribute("64 MP", 9.5f),
                    "Battery" to PhoneAttribute("4000 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                    "CPU" to PhoneAttribute("Exynos 2100", 9.0f),
                    "GPU" to PhoneAttribute("Mali-G78 MP14", 8.5f),
                    "Size" to PhoneAttribute("151.7 x 71.2 x 7.9 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$799", 8.5f)
                )
            ),
            Phone(
                "iPhone 13", mapOf(
                    "Software" to PhoneAttribute("iOS 15", 9.5f),
                    "Screen" to PhoneAttribute("6.1 inches", 9.5f),
                    "Camera" to PhoneAttribute("12 MP", 9.5f),
                    "Battery" to PhoneAttribute("3240 mAh", 8.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("4 GB", 8.5f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                    "CPU" to PhoneAttribute("A15 Bionic", 9.5f),
                    "GPU" to PhoneAttribute("Apple GPU", 9.5f),
                    "Size" to PhoneAttribute("146.7 x 71.5 x 7.7 mm", 9.0f),
                    "Reviews" to PhoneAttribute("Excellent", 9.5f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$799", 9.0f)
                )
            ),
            Phone(
                "Google Pixel 6", mapOf(
                    "Software" to PhoneAttribute("Android 12", 9.0f),
                    "Screen" to PhoneAttribute("6.4 inches", 9.0f),
                    "Camera" to PhoneAttribute("50 MP", 9.5f),
                    "Battery" to PhoneAttribute("4614 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                    "CPU" to PhoneAttribute("Google Tensor", 9.0f),
                    "GPU" to PhoneAttribute("Mali-G78 MP20", 8.5f),
                    "Size" to PhoneAttribute("158.6 x 74.8 x 8.9 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$599", 8.5f)
                )
            ),
            Phone(
                "OnePlus 9", mapOf(
                    "Software" to PhoneAttribute("Android 11", 9.0f),
                    "Screen" to PhoneAttribute("6.55 inches", 9.0f),
                    "Camera" to PhoneAttribute("48 MP", 9.0f),
                    "Battery" to PhoneAttribute("4500 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                    "CPU" to PhoneAttribute("Snapdragon 888", 9.0f),
                    "GPU" to PhoneAttribute("Adreno 660", 8.5f),
                    "Size" to PhoneAttribute("160 x 74.2 x 8.7 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$729", 8.5f)
                )
            ),
            Phone(
                "Xiaomi Mi 11", mapOf(
                    "Software" to PhoneAttribute("Android 11", 9.0f),
                    "Screen" to PhoneAttribute("6.81 inches", 9.5f),
                    "Camera" to PhoneAttribute("108 MP", 9.5f),
                    "Battery" to PhoneAttribute("4600 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                    "CPU" to PhoneAttribute("Snapdragon 888", 9.0f),
                    "GPU" to PhoneAttribute("Adreno 660", 8.5f),
                    "Size" to PhoneAttribute("164.3 x 74.6 x 8.1 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$749", 8.5f)
                )
            ),
            Phone(
                "Sony Xperia 1 III", mapOf(
                    "Software" to PhoneAttribute("Android 11", 9.0f),
                    "Screen" to PhoneAttribute("6.5 inches", 9.5f),
                    "Camera" to PhoneAttribute("12 MP", 9.0f),
                    "Battery" to PhoneAttribute("4500 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("12 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 888", 9.0f),
                    "GPU" to PhoneAttribute("Adreno 660", 8.5f),
                    "Size" to PhoneAttribute("165 x 71 x 8.2 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1299", 8.5f)
                )
            ),
            Phone(
                "Oppo Find X3 Pro", mapOf(
                    "Software" to PhoneAttribute("Android 11", 9.0f),
                    "Screen" to PhoneAttribute("6.7 inches", 9.5f),
                    "Camera" to PhoneAttribute("50 MP", 9.5f),
                    "Battery" to PhoneAttribute("4500 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("12 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 888", 9.0f),
                    "GPU" to PhoneAttribute("Adreno 660", 8.5f),
                    "Size" to PhoneAttribute("163.6 x 74 x 8.3 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$1149", 8.5f)
                )
            ),
            Phone(
                "Huawei P40 Pro", mapOf(
                    "Software" to PhoneAttribute("Android 10", 8.5f),
                    "Screen" to PhoneAttribute("6.58 inches", 9.0f),
                    "Camera" to PhoneAttribute("50 MP", 9.5f),
                    "Battery" to PhoneAttribute("4200 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.0f),
                    "CPU" to PhoneAttribute("Kirin 990 5G", 9.0f),
                    "GPU" to PhoneAttribute("Mali-G76 MP16", 8.5f),
                    "Size" to PhoneAttribute("158.2 x 72.6 x 9 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$999", 8.5f)
                )
            ),
            Phone(
                "Asus ROG Phone 5", mapOf(
                    "Software" to PhoneAttribute("Android 11", 9.0f),
                    "Screen" to PhoneAttribute("6.78 inches", 9.5f),
                    "Camera" to PhoneAttribute("64 MP", 9.0f),
                    "Battery" to PhoneAttribute("6000 mAh", 9.0f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 9.0f),
                    "Microphone" to PhoneAttribute("Dual", 9.0f),
                    "RAM" to PhoneAttribute("16 GB", 9.5f),
                    "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                    "CPU" to PhoneAttribute("Snapdragon 888", 9.0f),
                    "GPU" to PhoneAttribute("Adreno 660", 8.5f),
                    "Size" to PhoneAttribute("172.8 x 77.3 x 10.3 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$999", 8.5f)
                )
            ),
            Phone(
                "Nokia 8.3 5G", mapOf(
                    "Software" to PhoneAttribute("Android 10", 8.5f),
                    "Screen" to PhoneAttribute("6.81 inches", 9.0f),
                    "Camera" to PhoneAttribute("64 MP", 9.0f),
                    "Battery" to PhoneAttribute("4500 mAh", 8.5f),
                    "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                    "Speaker" to PhoneAttribute("Stereo", 8.5f),
                    "Microphone" to PhoneAttribute("Dual", 8.5f),
                    "RAM" to PhoneAttribute("8 GB", 9.0f),
                    "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                    "CPU" to PhoneAttribute("Snapdragon 765G", 8.5f),
                    "GPU" to PhoneAttribute("Adreno 620", 8.0f),
                    "Size" to PhoneAttribute("171.9 x 78.6 x 9 mm", 8.5f),
                    "Reviews" to PhoneAttribute("Excellent", 9.0f),
                    "User_Opinion" to PhoneAttribute("Very Positive", 9.5f),
                    "Popularity" to PhoneAttribute("Very High", 9.5f),
                    "Price" to PhoneAttribute("$699", 8.5f)
                )
            )
        )
        for (phone in initialPhones) {
            CoroutineScope(Dispatchers.IO).launch {
                firebaseManager.addPhone(phone)
            }
        }
    }
}
