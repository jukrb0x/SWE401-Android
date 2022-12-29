@file:OptIn(ExperimentalAnimationApi::class, ExperimentalAnimationApi::class,
    ExperimentalAnimationApi::class
)

package com.brokenbrains.fitness

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.brokenbrains.fitness.data.model.HomeViewModel
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.model.activity.ActivityViewModel
import com.brokenbrains.fitness.data.model.measurement.MeasurementType
import com.brokenbrains.fitness.data.model.measurement.MeasurementViewModel
import com.brokenbrains.fitness.data.model.medication.MedicationType
import com.brokenbrains.fitness.data.model.medication.MedicationViewModel
import com.brokenbrains.fitness.ui.screens.HomeScreen
import com.brokenbrains.fitness.ui.screens.browse.BrowseScreen
import com.brokenbrains.fitness.ui.screens.browse.activity.ActivityDetailScreen
import com.brokenbrains.fitness.ui.screens.browse.activity.ActivityPage
import com.brokenbrains.fitness.ui.screens.browse.activity.AddActivityScreen
import com.brokenbrains.fitness.ui.screens.browse.components.MeasurementsPage
import com.brokenbrains.fitness.ui.screens.browse.components.SleepPage
import com.brokenbrains.fitness.ui.screens.browse.components.VitalsPage
import com.brokenbrains.fitness.ui.screens.browse.measurements.AddMeasurementScreen
import com.brokenbrains.fitness.ui.screens.browse.measurements.MeasurementDetailScreen
import com.brokenbrains.fitness.ui.screens.browse.measurements.MedicationScreen
import com.brokenbrains.fitness.ui.screens.browse.medication.AddMedicationScreen
import com.brokenbrains.fitness.ui.screens.healthplus.ArticleWebViewer
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
        val viewModel = hiltViewModel<HomeViewModel>()
        HomeScreen(navigateTo = { route -> navigateTo(route, from) }, viewModel = viewModel)
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
        ActivityDetailScreen(viewModel = vm,
            activityType = ActivityType.fromString(activityType!!),
            navigateTo = navTo,
            onBack = { /*activityViewModel.refresh();*/upPress() })
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
        val viewModel = hiltViewModel<MeasurementViewModel>()
        MeasurementsPage(viewModel = viewModel, navigateTo = navTo, onBack = upPress)
    }

    composable(MeasurementsRoutes.AddMeasurement.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Start
            )
        },
        exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
        val navTo = { route: String -> navigateTo(route, from) }
        val vm = hiltViewModel<MeasurementViewModel>();
        AddMeasurementScreen(viewModel = vm, navigateTo = navTo, onBack = upPress)
    }

    composable(
        "${MeasurementsRoutes.MeasurementDetails.route}/{measurementType}",
        arguments = listOf(navArgument("measurementType") { type = NavType.StringType }),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Start
            )
        },
        exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
        val navTo = { route: String -> navigateTo(route, from) }
        val vm = hiltViewModel<MeasurementViewModel>();
        val arguments = requireNotNull(from.arguments)
        val activityType = arguments.getString("measurementType")
        MeasurementDetailScreen(viewModel = vm,
            measurementType = MeasurementType.fromString(activityType!!),
            navigateTo = navTo,
            onBack = { upPress() })
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
        val vm = hiltViewModel<MedicationViewModel>()
        MedicationScreen(viewModel = vm , navigateTo = navTo, onBack = upPress)
    }

    composable(MedicationRoutes.AddMedication.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Start
            )
        },
        exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
        val navTo = { route: String -> navigateTo(route, from) }
        val vm = hiltViewModel<MedicationViewModel>();
        AddMedicationScreen(viewModel = vm, navigateTo = navTo, onBack = upPress)
    }

}

fun NavGraphBuilder.ArticleWebViewScreenComposable(
    navigateTo: (route: String, from: NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
) {
    composable(
        AppDestinations.ARTICLE_ROUTE,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Start
            )
        },
        exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
        val navTo = { route: String -> navigateTo(route, from) }
        ArticleWebViewer(navTo, upPress)
    }
}