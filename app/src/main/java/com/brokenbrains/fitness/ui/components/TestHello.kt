package com.brokenbrains.fitness.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun TestHello(
    name: String
) {
    Text(text = "Hello $name!")
}
