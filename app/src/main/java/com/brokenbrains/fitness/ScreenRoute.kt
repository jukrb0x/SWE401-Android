package com.brokenbrains.fitness

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector

object AppDestinations {
    const val MAIN_ROUTE = "main"
}

enum class TabRoutes(val route: String, val displayName: String, val icon: ImageVector) {
    Home("main/home", "Home", Icons.Filled.Home),
    HealthPlus("main/profile", "Profile", Icons.Filled.Person),
    Sharing("main/share", "Share", Icons.Filled.Share)
}