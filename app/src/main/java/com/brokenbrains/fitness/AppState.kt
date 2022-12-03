package com.brokenbrains.fitness

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

/**
 * Create the AppState Instance
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberAppState(
//    navController: NavHostController = rememberNavController(),
    navController: NavHostController = rememberAnimatedNavController(),
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

    fun upPress() = navController.navigateUp() // back button

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

    // general navigation method, use with caution
    fun navigateTo(route: String, from: NavBackStackEntry) {
        // prevent duplicated navigation events
        if (from.lifecycleIsResumed()) {
            navController.navigate(route)
        }
    }


/* TODO
    fun navigateToXXX(){

    }
*/

    // --------------
    // Bottom Navigation Bar
    // --------------
    val bottomBarRoutes = TabRoutes.values(); // todo: change later

    // Secure the current route is available and is in the bottom bar routes
    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes.map { it.route }

    private val floatingActionRoutes = bottomBarRoutes.filter { it.route !== TabRoutes.HealthPlus.route }
    val shouldShowFloatingActionButton: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in floatingActionRoutes.map { it.route }

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
