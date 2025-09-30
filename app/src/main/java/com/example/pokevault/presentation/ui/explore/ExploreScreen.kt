package com.example.pokevault.presentation.ui.explore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ExploreScreen() {
    Box (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Explore Screen",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}