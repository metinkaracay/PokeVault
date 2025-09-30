package com.example.pokevault.presentation.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Explore : Routes("explore")
}
