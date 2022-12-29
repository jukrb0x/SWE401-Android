package com.brokenbrains.fitness

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.Home
import com.brokenbrains.fitness.AppDestinations.BROWSE_ROUTE
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
    const val LOGOUT_ROUTE = "logout"
    const val LOGIN_ROUTE = "login"
    const val BROWSE_ROUTE = "browse"
    const val ARTICLE_ROUTE = "article"
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
    Splash("$USER_ROUTE/splash", "Splash"),
    Login("$USER_ROUTE/login", "Login"),
    Register("$USER_ROUTE/register", "Register"),
    Profile("$USER_ROUTE/profile", "Profile")
//    ShareSettings("$USER_ROUTE/share_settings", "Share Settings")
}

enum class BrowseRoutes(val route: String, val title: String) {
    Activity("$BROWSE_ROUTE/activity", "Activity"),
    Measurements("$BROWSE_ROUTE/measurements", "Measurements"),
    Vitals("$BROWSE_ROUTE/vitals", "Vitals"),
    Sleep("$BROWSE_ROUTE/sleep", "Sleep"),
    Medication("$BROWSE_ROUTE/medication", "Medication"),;
}

enum class ActivityRoutes(val route: String, val title: String) {
    AddActivity("${BrowseRoutes.Activity.route}/add", "Add Activity"),
    ActivityDetails("${BrowseRoutes.Activity.route}/details", "Activity Details"),
}
enum class MeasurementsRoutes(val route: String, val title: String) {
    AddMeasurement("${BrowseRoutes.Measurements.route}/add", "Add Measurement"),
    MeasurementDetails("${BrowseRoutes.Measurements.route}/details", "Measurement Details"),
}

enum class MedicationRoutes(val route: String, val title: String) {
    AddMedication("${BrowseRoutes.Medication.route}/add", "Add Medication"),
//    MedicationDetails("${BrowseRoutes.Medication.route}/details", "Medication Details"),
}