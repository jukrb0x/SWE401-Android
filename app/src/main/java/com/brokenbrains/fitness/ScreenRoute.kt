package com.brokenbrains.fitness

import androidx.compose.runtime.Composable
import com.brokenbrains.fitness.ui.screens.HomeScreen


interface ScreenRoute {
    val route: String
    val screen: @Composable () -> Unit
}

object Home : ScreenRoute {
    override val route = "home"
    override val screen: @Composable () -> Unit = { HomeScreen() }
}

val TabRoutes = listOf(
    Home
)