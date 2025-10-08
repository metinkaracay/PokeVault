package com.example.pokevault.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokevault.presentation.ui.main.MainScreen
import com.example.pokevault.presentation.ui.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Routes.Splash.route) {
        composable(Routes.Splash.route) {
            SplashScreen(
                onNavigateToMain = {
                    navController.navigate(Routes.Main.route) {
                        popUpTo(Routes.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            Routes.Main.route
        ) {
            MainScreen()
        }
    }
}