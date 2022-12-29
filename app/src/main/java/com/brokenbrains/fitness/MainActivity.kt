@file:JvmName("MainActivityKt")

package com.brokenbrains.fitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.brokenbrains.fitness.data.model.auth.AuthViewModel
import com.brokenbrains.fitness.ui.components.AppBottomBar
import com.brokenbrains.fitness.ui.components.AppScaffold
import com.brokenbrains.fitness.ui.screens.home.HomeScreenAddFab
import com.brokenbrains.fitness.ui.screens.sharing.AddFriendFabScreen
import com.brokenbrains.fitness.ui.screens.user.LoginScreen
import com.brokenbrains.fitness.ui.screens.user.ProfileScreen
import com.brokenbrains.fitness.ui.screens.user.SignupScreen
import com.brokenbrains.fitness.ui.theme.FitnessTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val authViewModel:AuthViewModel by viewModels<AuthViewModel>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessApp()
        }
    }
}


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
                        TabRoutes.Home.route -> HomeScreenAddFab(navigateTo = appState.navController::navigate)
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
        HomeScreenComposable(navigateTo)
        HealthPlusScreenComposable(navigateTo)
        SharingScreenComposable(navigateTo)
        BrowseScreenComposable(navigateTo, upPress)
    }

    // user related routes
    navigation(
        route = AppDestinations.USER_ROUTE, startDestination = UserRoutes.Login.route
    ) {
        // login and logout
        composable(AppDestinations.LOGIN_ROUTE) { from ->
            appState.login()
        }
        composable(AppDestinations.LOGOUT_ROUTE) { from ->
            appState.logout()
        }

        composable(UserRoutes.Login.route) { from ->
            val viewModel = hiltViewModel<AuthViewModel>()
            LoginScreen(viewModel, navigateTo = { route -> navigateTo(route, from) })
        }
        composable(UserRoutes.Register.route) { from ->
            val viewModel = hiltViewModel<AuthViewModel>()
            SignupScreen(viewModel, navigateTo = { route -> navigateTo(route, from) })
        }
        composable(UserRoutes.Profile.route){ from ->
            val viewModel = hiltViewModel<AuthViewModel>()
            ProfileScreen(viewModel=viewModel, navigateTo = { route -> navigateTo(route, from) }, onDismiss = upPress)
        }


    }

    // browse items
    navigation(
        route = AppDestinations.BROWSE_ROUTE, startDestination = TabRoutes.Browse.route
    ) {
        BrowseActivityScreenComposable(navigateTo, upPress)
        BrowseMeasurementsScreenComposable(navigateTo, upPress)
        BrowseVitalsScreenComposable(navigateTo, upPress)
        BrowseSleepScreenComposable(navigateTo, upPress)
        BrowseMedicationScreenComposable(navigateTo, upPress)
    }
}
