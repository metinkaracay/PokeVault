package com.example.pokevault.presentation.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Explore : Routes("explore")
    object Main : Routes("main")
    object Compare : Routes("compare")
    object Settings : Routes("settings")
}
