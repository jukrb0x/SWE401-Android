@file:OptIn(ExperimentalAnimationApi::class)

package com.brokenbrains.fitness

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.brokenbrains.fitness.ui.screens.HomeScreen
import com.brokenbrains.fitness.ui.screens.browse.BrowseScreen
import com.brokenbrains.fitness.ui.screens.browse.activity.AddActivityScreen
import com.brokenbrains.fitness.ui.screens.browse.components.ActivityPage
import com.brokenbrains.fitness.ui.screens.healthplus.HealthPlusScreen
import com.brokenbrains.fitness.ui.screens.sharing.SharingScreen
import com.google.accompanist.navigation.animation.composable


// ----------------------------
// TAB ROUTES
// ----------------------------

fun NavGraphBuilder.HomeScreenComposable(
    navigateTo: (route: String, from: NavBackStackEntry) -> Unit,
) {
    composable(TabRoutes.Home.route) { from ->
//            val viewModel = hiltViewModel<HomeViewModel>()
        HomeScreen(navigateTo = { route -> navigateTo(route, from) })
    }

}

fun NavGraphBuilder.HealthPlusScreenComposable(
    navigateTo: (route: String, from: NavBackStackEntry) -> Unit,
) {
    composable(TabRoutes.HealthPlus.route) { from ->
        HealthPlusScreen(navigateTo = { route -> navigateTo(route, from) })
    }
}

fun NavGraphBuilder.SharingScreenComposable(
    navigateTo: (route: String, from: NavBackStackEntry) -> Unit,
) {
    composable(TabRoutes.Sharing.route) { from ->
        SharingScreen(navigateTo = { route -> navigateTo(route, from) })
    }
}

fun NavGraphBuilder.BrowseScreenComposable(
    navigateTo: (route: String, from: NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
) {
    composable(TabRoutes.Browse.route) { from ->
        BrowseScreen(navigateTo = { route -> navigateTo(route, from) }, onBack = upPress)
    }
}

// ----------------
// Browse
// ----------------

fun NavGraphBuilder.BrowseActivityScreenComposable(
    navigateTo: (route: String, from: NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
) {
    composable(BrowseRoutes.Activity.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Start
            )
        },
        exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
        val navTo = { route: String -> navigateTo(route, from) }
        ActivityPage(navigateTo = navTo, onBack = upPress)
    }

    composable(ActivityRoutes.AddActivity.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Start
            )
        },
        exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
        val navTo = { route: String -> navigateTo(route, from) }
        AddActivityScreen(navigateTo = navTo, onBack = upPress)
    }
}