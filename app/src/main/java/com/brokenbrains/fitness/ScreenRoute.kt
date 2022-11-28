package com.brokenbrains.fitness

import androidx.compose.runtime.Composable
import com.brokenbrains.fitness.ui.screens.HomeScreen
import com.brokenbrains.fitness.ui.screens.ProfileScreen


interface ScreenRoute {
    val route: String
    val screen: @Composable () -> Unit
}

object Home : ScreenRoute {
    override val route = "home"
    override val screen: @Composable () -> Unit = { HomeScreen() }
}

object Profile : ScreenRoute {
    override val route = "profile"
    override val screen: @Composable () -> Unit = { ProfileScreen() }
}
val TabRoutes = listOf(
    Home, Profile
)