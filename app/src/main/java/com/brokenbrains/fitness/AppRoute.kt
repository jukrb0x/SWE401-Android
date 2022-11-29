package com.brokenbrains.fitness

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.HandHoldingHeart

/**
 *  the router navigation is defined in [AppState].
 */

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
    Sharing("main/share", "Sharing", Icons.Filled.Share)
}