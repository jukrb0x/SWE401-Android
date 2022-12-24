package com.brokenbrains.fitness

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.brokenbrains.fitness.ui.components.AppBottomBar
import com.brokenbrains.fitness.ui.components.AppScaffold
import com.brokenbrains.fitness.ui.screens.HomeScreen
import com.brokenbrains.fitness.ui.screens.browse.*
import com.brokenbrains.fitness.ui.screens.healthplus.HealthPlusScreen
import com.brokenbrains.fitness.ui.screens.home.AddHealthDataFab
import com.brokenbrains.fitness.ui.screens.sharing.AddFriendFabScreen
import com.brokenbrains.fitness.ui.screens.sharing.SharingScreen
import com.brokenbrains.fitness.ui.screens.user.LoginScreen
import com.brokenbrains.fitness.ui.screens.user.RegisterScreen
import com.brokenbrains.fitness.ui.theme.FitnessTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview(showBackground = true)
fun FitnessApp() {
    val appState = rememberAppState()

    FitnessTheme {
        AppScaffold(backgroundColor = Color.White.copy(alpha = 0.0f), // not really working
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    AppBottomBar(
                        navigateTo = appState::navigateToTabBottomRoute,
                        currentRoute = appState.currentRoute,
                        tabRoutes = appState.bottomBarRoutes
                    )
                }
            }, floatingActionButton = {
                if (appState.shouldShowFloatingActionButton) {
                    when (appState.currentRoute) {
                        TabRoutes.Home.route -> AddHealthDataFab()
                        TabRoutes.Sharing.route -> AddFriendFabScreen(navigateTo = appState.navController::navigate)
                    }
                }
            }) { innerPadding ->
            Column() {
                AnimatedNavHost(
                    navController = appState.navController,
                    startDestination = AppDestinations.USER_ROUTE,
                    modifier = Modifier.padding(innerPadding),
                    // turn off animated transition of navigation
                    enterTransition = { fadeIn(animationSpec = tween(1)) },
                    exitTransition = { fadeOut(animationSpec = tween(1)) },
                ) {
                    appNavGraph(navigateTo = appState::navigateTo, appState::upPress, appState)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.appNavGraph( // custom name..
    navigateTo: (route: String, from: NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    appState: AppState
) {
    // main route for tabs
    navigation(
        route = AppDestinations.MAIN_ROUTE, startDestination = TabRoutes.Home.route
    ) {
        composable(TabRoutes.Home.route) { from ->
            HomeScreen(navigateTo = { route -> navigateTo(route, from) })
        }
        composable(TabRoutes.HealthPlus.route) { from ->
            HealthPlusScreen(navigateTo = { route -> navigateTo(route, from) })
        }
        composable(TabRoutes.Sharing.route) { from ->
            SharingScreen(navigateTo = { route -> navigateTo(route, from) })
        }
        composable(TabRoutes.Browse.route) { from ->
            BrowseScreen(navigateTo = { route -> navigateTo(route, from) }, onBack = upPress)
        }
    }

    // user related routes
    navigation(
        route = AppDestinations.USER_ROUTE, startDestination = UserRoutes.Login.route
    ) {
        // login and logout
        composable(AppDestinations.LOGIN_ROUTE) { from ->
            appState.login()
//            LoginScreen(navigateTo = { route -> navigateTo(route, from) })
        }
        composable(AppDestinations.LOGOUT_ROUTE) { from ->
            appState.logout()
        }

        composable(UserRoutes.Login.route) { from ->
            LoginScreen(navigateTo = { route -> navigateTo(route, from) })
        }
        composable(UserRoutes.Register.route) { from ->
            RegisterScreen(navigateTo = { route -> navigateTo(route, from) })
        }

    }

    // TODO: the routes are way too nested.. use another architecture
    // browse items
    navigation(
        route = AppDestinations.BROWSE_ROUTE, startDestination = TabRoutes.Browse.route
    ) {
        composable(BrowseRoutes.Activity.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Start
                )
            },
            exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
            val navTo = { route: String -> navigateTo(route, from) }
            BrowseDetails(
                title = BrowseRoutes.Activity.title, navigateTo = navTo, onBack = upPress
            ) {
                ActivityDetails(navigateTo = navTo, onBack = upPress)
            }
        }

        composable(BrowseRoutes.Measurements.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Start
                )
            },
            exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
            val navTo = { route: String -> navigateTo(route, from) }
            BrowseDetails(
                title = BrowseRoutes.Measurements.title, navigateTo = navTo, onBack = upPress
            ) {
                MeasurementsDetails(navigateTo = navTo, onBack = upPress)
            }
        }

        composable(BrowseRoutes.Vitals.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Start
                )
            },
            exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
            val navTo = { route: String -> navigateTo(route, from) }
            BrowseDetails(
                title = BrowseRoutes.Vitals.title, navigateTo = navTo, onBack = upPress
            ) {
                VitalsDetails(navigateTo = navTo, onBack = upPress)
            }
        }

        composable(BrowseRoutes.Sleep.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Start
                )
            },
            exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
            val navTo = { route: String -> navigateTo(route, from) }
            BrowseDetails(
                title = BrowseRoutes.Sleep.title, navigateTo = navTo, onBack = upPress
            ) {
                SleepDetails(navigateTo = navTo, onBack = upPress)
            }
        }

        composable(BrowseRoutes.Medication.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Start
                )
            },
            exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }) { from ->
            val navTo = { route: String -> navigateTo(route, from) }
            BrowseDetails(
                title = BrowseRoutes.Medication.title, navigateTo = navTo, onBack = upPress
            ) {
                MedicationDetails(navigateTo = navTo, onBack = upPress)
            }
        }
    }
}
