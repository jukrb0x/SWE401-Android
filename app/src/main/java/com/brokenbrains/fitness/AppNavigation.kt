@file:OptIn(ExperimentalAnimationApi::class, ExperimentalAnimationApi::class)

package com.brokenbrains.fitness

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.model.activity.ActivityViewModel
import com.brokenbrains.fitness.ui.screens.HomeScreen
import com.brokenbrains.fitness.ui.screens.browse.BrowseScreen
import com.brokenbrains.fitness.ui.screens.browse.activity.ActivityDetailScreen
import com.brokenbrains.fitness.ui.screens.browse.activity.AddActivityScreen
import com.brokenbrains.fitness.ui.screens.browse.components.*
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
        val viewModel = hiltViewModel<ActivityViewModel>()
        ActivityPage(viewModel = viewModel, navigateTo = navTo, onBack = upPress)
    }

    composable(ActivityRoutes.AddActivity.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Start
            )
        },
        exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
        val navTo = { route: String -> navigateTo(route, from) }
        val vm = hiltViewModel<ActivityViewModel>();
        AddActivityScreen(viewModel = vm, navigateTo = navTo, onBack = upPress)
    }

    composable(
        "${ActivityRoutes.ActivityDetails.route}/{activityType}",
        arguments = listOf(navArgument("activityType") { type = NavType.StringType }),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Start
            )
        },
        exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
        val navTo = { route: String -> navigateTo(route, from) }
        val vm = hiltViewModel<ActivityViewModel>();
        val arguments = requireNotNull(from.arguments)
        val activityType = arguments.getString("activityType")
        ActivityDetailScreen(viewModel = vm, activityType = ActivityType.fromString(activityType!!)
            , navigateTo = navTo, onBack = upPress)
    }

}

fun NavGraphBuilder.BrowseMeasurementsScreenComposable(
    navigateTo: (route: String, from: NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
) {
    composable(BrowseRoutes.Measurements.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Start
            )
        },
        exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
        val navTo = { route: String -> navigateTo(route, from) }
        MeasurementsPage(navigateTo = navTo, onBack = upPress)
    }
}

fun NavGraphBuilder.BrowseVitalsScreenComposable(
    navigateTo: (route: String, from: NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
) {
    composable(BrowseRoutes.Vitals.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Start
            )
        },
        exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
        val navTo = { route: String -> navigateTo(route, from) }
        VitalsPage(navigateTo = navTo, onBack = upPress)
    }
}

fun NavGraphBuilder.BrowseSleepScreenComposable(
    navigateTo: (route: String, from: NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
) {
    composable(BrowseRoutes.Sleep.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Start
            )
        },
        exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
        val navTo = { route: String -> navigateTo(route, from) }
        SleepPage(navigateTo = navTo, onBack = upPress)
    }
}


fun NavGraphBuilder.BrowseMedicationScreenComposable(
    navigateTo: (route: String, from: NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
) {
    composable(BrowseRoutes.Medication.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Start
            )
        },
        exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
        val navTo = { route: String -> navigateTo(route, from) }
        MedicationPage(navigateTo = navTo, onBack = upPress)
    }
}
