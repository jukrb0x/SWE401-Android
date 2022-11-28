package com.brokenbrains.fitness

import androidx.compose.runtime.Stable
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController

fun rememberAppState() {
//    return AppState()
}


@Stable // JB todo: see docs about @Stable
class AppState(
    val navController: NavHostController
) {

    // ----------------------
    // Navigation state SSOT
    // ----------------------

    val currentRoute: String? get() = navController.currentDestination?.route

    fun upPress() = navController.navigateUp()

    fun navigateToTabBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                // this will go back to the first destination and save state
                launchSingleTop = true
                restoreState = true
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }
}

/**
 * Reference to NavigationUI.kt
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-ui/src/main/java/androidx/navigation/ui/NavigationUI.kt
 */
private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}
