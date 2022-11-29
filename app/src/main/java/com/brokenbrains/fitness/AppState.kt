package com.brokenbrains.fitness

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * Create the AppState Instance
 */
@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    AppState(navController)
}


/**
 * Holding UI related states as SSOT.
 */
@Stable // JB todo: see docs about @Stable
class AppState(
    val navController: NavHostController
) {

    // ----------------------
    // Navigation state SSOT
    // ----------------------

    val currentRoute: String? get() = navController.currentDestination?.route

    fun upPress() = navController.navigateUp()

    // responsible for the bottom bar navigation
    fun navigateToTabBottomRoute(route: String) {
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

    // --------------
    // Bottom Bar
    // --------------
    val bottomBarRoutes = TabRoutes.values(); // todo: change later
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 *
 * from: https://github.com/android/compose-samples/blob/main/Jetsnack/app/src/main/java/com/example/jetsnack/ui/JetsnackAppState.kt
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

/**
 * Reference to NavigationUI.kt
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-ui/src/main/java/androidx/navigation/ui/NavigationUI.kt
 */
private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}