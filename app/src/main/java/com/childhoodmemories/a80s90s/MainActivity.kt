package com.childhoodmemories.a80s90s

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.childhoodmemories.a80s90s.data.database.MemoSharedPreferences
import com.childhoodmemories.a80s90s.ui.features.addMemory.AddMemoryScreen
import com.childhoodmemories.a80s90s.ui.features.getStarted.GetStartedScreen
import com.childhoodmemories.a80s90s.ui.features.home.HomeScreen
import com.childhoodmemories.a80s90s.ui.features.profile.ProfileScreen
import com.childhoodmemories.a80s90s.ui.theme.Memories80s90sTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MemoSharedPreferences.context = this
        requestPermissions()
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
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Profile.route) { ProfileScreen(navController) }
            composable(Screen.AddMemory.route) { AddMemoryScreen(navController) }
        }
    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(READ_MEDIA_IMAGES, READ_EXTERNAL_STORAGE, READ_MEDIA_VIDEO),
                    READ_MEDIA_IMAGES_REQUEST_CODE
                )
            }
        }
    }

    companion object {
        private const val READ_MEDIA_IMAGES_REQUEST_CODE = 1
    }

}

enum class Screen(val route: String) {
    GetStarted("getStarted"),
    Home("home"),
    Profile("profile"),
    AddMemory("addMemory"),
}


