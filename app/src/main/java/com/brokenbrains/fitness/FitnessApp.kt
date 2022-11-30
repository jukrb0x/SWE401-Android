package com.brokenbrains.fitness

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.brokenbrains.fitness.ui.components.AppBottomBar
import com.brokenbrains.fitness.ui.screens.HomeScreen
import com.brokenbrains.fitness.ui.screens.ProfileScreen
import com.brokenbrains.fitness.ui.screens.SharingScreen
import com.brokenbrains.fitness.ui.screens.healthplus.HealthPlusScreen
import com.brokenbrains.fitness.ui.theme.FitnessTheme


@Composable
@Preview(showBackground = true)
fun FitnessApp() {
    val appState = rememberAppState()
    FitnessTheme {
        Scaffold(
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    AppBottomBar(
                        navigateTo = appState::navigateToTabBottomRoute,
                        currentRoute = appState.currentRoute,
                        tabRoutes = appState.bottomBarRoutes
                    )
                }
            },
        ) { innerPadding ->
            NavHost(
                navController = appState.navController,
                startDestination = AppDestinations.MAIN_ROUTE,
                modifier = Modifier.padding(innerPadding)
            ) {
                appNavGraph(navigateTo = appState::navigateTo, appState::upPress)
            }
        }
    }
}

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
        composable(TabRoutes.Sharing.route) { from->
            SharingScreen(navigateTo = { route -> navigateTo(route, from) })
        }
    }

    // user related routes
    navigation(
        route = AppDestinations.USER_ROUTE,
        startDestination = UserRoutes.Profile.route
    ) {
        composable(UserRoutes.Profile.route) {
            ProfileScreen()
        }
    }
}
