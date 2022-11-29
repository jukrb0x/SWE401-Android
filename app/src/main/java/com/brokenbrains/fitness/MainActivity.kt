package com.brokenbrains.fitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.brokenbrains.fitness.ui.screens.HomeScreen
import com.brokenbrains.fitness.ui.screens.ProfileScreen
import com.brokenbrains.fitness.ui.theme.FitnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun App() {
    FitnessTheme {
        val appState = rememberAppState();
        Scaffold(
            bottomBar = {
                Row {
                    BottomNavigation {
                        appState.bottomBarRoutes.forEach {
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        it.icon,
                                        contentDescription = null,
                                    )
                                },
                                label = { Text(it.displayName) },
                                selected = appState.currentRoute == it.route,
                                onClick = {
                                    appState.navigateToTabBottomRoute(it.route)
                                }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            // todo A surface container using the 'background' color from the theme
            NavHost(
                navController = appState.navController,
                startDestination = AppDestinations.MAIN_ROUTE,
                modifier = Modifier.padding(innerPadding)
            ) {
                appNavGraph(appState::upPress)
            }
        }
    }
}

private fun NavGraphBuilder.appNavGraph( // custom name..
    upPress: () -> Unit
) {
    navigation(
        route = AppDestinations.MAIN_ROUTE,
        startDestination = TabRoutes.Home.route
    ) {
        composable(TabRoutes.Home.route) {
            HomeScreen()
        }
        composable(TabRoutes.HealthPlus.route) {
            ProfileScreen()
        }
        composable(TabRoutes.Sharing.route) {
            ProfileScreen()
        }
    }
}
