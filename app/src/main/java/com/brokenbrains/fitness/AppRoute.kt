package com.brokenbrains.fitness

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector
import com.brokenbrains.fitness.AppDestinations.MAIN_ROUTE
import com.brokenbrains.fitness.AppDestinations.USER_ROUTE
import compose.icons.FeatherIcons
import compose.icons.FontAwesomeIcons
import compose.icons.feathericons.List
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.HandHoldingHeart

/**
 *  the router navigation is defined in [AppState].
 */

object AppDestinations {
    const val MAIN_ROUTE = "main"
    const val USER_ROUTE = "user"
}

enum class TabRoutes(val route: String, val title: String, val icon: ImageVector) {
    Home("$MAIN_ROUTE/home", "Home", Icons.Filled.Home),
    HealthPlus(
        "$MAIN_ROUTE/healthplus",
        "Health+",
        FontAwesomeIcons.Solid.HandHoldingHeart
    ),
    Sharing("$MAIN_ROUTE/sharing", "Sharing", Icons.Filled.Share),
    Browse("$MAIN_ROUTE/browse", "Browse", FeatherIcons.List),
}


enum class UserRoutes(val route: String, val title: String) {
//    Profile("$USER_ROUTE/profile", "Profile"),
//    Settings("$USER_ROUTE/settings", "Settings")
//    ShareSettings("$USER_ROUTE/share_settings", "Share Settings")
}