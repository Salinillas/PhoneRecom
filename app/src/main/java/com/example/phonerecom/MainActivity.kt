package com.example.phonerecom
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.phonerecom.ui.theme.PhoneRecomTheme

class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels { LoginViewModelFactory(DatabaseHelper(this)) }
    private val phoneViewModel: PhoneViewModel by viewModels { PhoneViewModelFactory(DatabaseHelper(this))}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhoneRecomTheme {
                val navController = rememberNavController()
                Scaffold { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("login") { LoginScreen(navController, viewModel) }
                        composable("admin_panel") { AdminPanel(navController, viewModel) }
                        composable("user_panel") { UserPanel(navController, viewModel) }
                        composable("phone_panel") { PhonePanel(navController) }
                        composable("phone_addition_panel") { PhoneAdditionPanel(navController, phoneViewModel) }
                        composable("phone_deletion_panel") { PhoneDeletionPanel(navController, phoneViewModel) }
                        composable("get_recommendation_panel") { GetRecommendationPanel(navController, phoneViewModel) }
                        composable("View_Phones_Panel") { ViewPhonesPanel(navController,phoneViewModel) }
                        composable("faq_panel") { FAQPanel(navController) }

                    }
                }
            }
        }
    }
}