package com.example.pokevault.presentation.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            modifier = Modifier.align(androidx.compose.ui.Alignment.Center),
            onClick = { onNavigateToLogin() }
        ) {
            androidx.compose.material3.Text(
                text = "Go to Login"
            )
        }
    }
}