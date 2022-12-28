package com.brokenbrains.fitness

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.model.activity.toReadableString
import com.brokenbrains.fitness.data.model.auth.AuthViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

/**
 * Create the AppState Instance
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberAppState(
    navController: NavHostController = rememberAnimatedNavController(),
): AppState {
    val authViewModel: AuthViewModel = hiltViewModel();
    return remember(navController) {
        AppState(navController, authViewModel)
    }
}


/**
 * Holding UI related states as SSOT.
 */
@Stable // JB todo: see docs about @Stable
class AppState(
    val navController: NavHostController,
    val authViewModel: AuthViewModel
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

    fun login() {
        // todo: add login logics
        // navigate to home, and clear the back stack
        navController.navigate(AppDestinations.MAIN_ROUTE) {
            popUpTo(0) { // pop up to the first destination in the graph
                saveState = true
            }
        }
    }

    fun logout() {
        authViewModel.logout()
        navController.navigate(UserRoutes.Login.route) {
            popUpTo(/*findStartDestination(navController.graph).id*/0) {
                saveState = true
            }
        }
    }

    fun navigateToActivityDetails(activityType: ActivityType, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        // because snack detail screen can hold the navigation event for the same one
        if (from.lifecycleIsResumed()) {
            navController.navigate("${ActivityRoutes.ActivityDetails}/${activityType.toReadableString()}")
        }
    }

    // --------------
    // Bottom Navigation Bar
    // --------------
    val bottomBarRoutes = TabRoutes.values();

    // Secure the current route is available and is in the bottom bar routes
    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes.map { it.route }

    private val floatingActionRoutes =
        bottomBarRoutes.filter { it.route !== TabRoutes.HealthPlus.route }
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
