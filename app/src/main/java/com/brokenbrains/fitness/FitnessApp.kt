package com.brokenbrains.fitness

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
import com.brokenbrains.fitness.ui.screens.healthplus.HealthPlusScreen
import com.brokenbrains.fitness.ui.screens.home.AddHealthDataFab
import com.brokenbrains.fitness.ui.screens.sharing.AddFriendFabScreen
import com.brokenbrains.fitness.ui.screens.sharing.SharingScreen
import com.brokenbrains.fitness.ui.theme.FitnessTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview(showBackground = true)
fun FitnessApp() {
    val appState = rememberAppState()

    FitnessTheme {
        AppScaffold(
            backgroundColor = Color.White.copy(alpha = 0.0f), // not really working
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    AppBottomBar(
                        navigateTo = appState::navigateToTabBottomRoute,
                        currentRoute = appState.currentRoute,
                        tabRoutes = appState.bottomBarRoutes
                    )
                }
            },
            floatingActionButton = {
                if (appState.shouldShowFloatingActionButton) {
                    when (appState.currentRoute) {
                        TabRoutes.Home.route -> AddHealthDataFab()
                        TabRoutes.Sharing.route -> AddFriendFabScreen()
                    }
                }
            }
        ) { innerPadding ->
            Column() {
                AnimatedNavHost(
                    navController = appState.navController,
                    startDestination = AppDestinations.MAIN_ROUTE,
                    modifier = Modifier.padding(innerPadding),
                    enterTransition = { fadeIn(animationSpec = tween(1)) },
                    exitTransition = { fadeOut(animationSpec = tween(1)) },
                ) {
                    appNavGraph(navigateTo = appState::navigateTo, appState::upPress)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.appNavGraph( // custom name..
    navigateTo: (route: String, from: NavBackStackEntry) -> Unit,
    upPress: () -> Unit
) {
    // todo: default transition between tabs should be off
    // main route for tabs
    navigation(
        route = AppDestinations.MAIN_ROUTE,
        startDestination = TabRoutes.Home.route
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
    }

    // user related routes
/*
    navigation(
        route = AppDestinations.USER_ROUTE,
        startDestination = UserRoutes.Profile.route
    ) {

    }
*/
}
