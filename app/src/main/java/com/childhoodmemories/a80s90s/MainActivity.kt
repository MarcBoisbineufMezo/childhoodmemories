package com.childhoodmemories.a80s90s

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.childhoodmemories.a80s90s.ui.features.getStarted.GetStartedScreen
import com.childhoodmemories.a80s90s.ui.features.home.HomeScreen
import com.childhoodmemories.a80s90s.ui.theme.Memories80s90sTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Memories80s90sTheme {
                BuildNavGraph(navController)
            }
        }
    }

    // TODO: Implement navigation graph in external class
    @Composable
    private fun BuildNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
        NavHost(navController = navController, startDestination = Screen.GetStarted.route) {
            composable(Screen.GetStarted.route) {
                GetStartedScreen(
                    modifier = modifier,
                    navController = navController
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(navController)
            }
            //                composable("profile") { ProfileScreen(navController) }
        }
    }
}

enum class Screen(val route: String) {
    GetStarted("getStarted"),
    Home("home"),
    Profile("profile")
}


