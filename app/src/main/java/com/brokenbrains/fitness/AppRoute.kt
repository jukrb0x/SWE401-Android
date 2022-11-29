package com.brokenbrains.fitness

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.HandHoldingHeart

object AppDestinations {
    const val MAIN_ROUTE = "main"
}

enum class TabRoutes(val route: String, val title: String, val icon: ImageVector) {
    Home("main/home", "Home", Icons.Filled.Home),
    HealthPlus(
        "main/healthplus",
        "Health+",
        FontAwesomeIcons.Solid.HandHoldingHeart
    ),
    Sharing("main/share", "Share", Icons.Filled.Share)
}