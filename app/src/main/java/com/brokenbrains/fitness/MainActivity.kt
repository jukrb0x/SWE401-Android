package com.brokenbrains.fitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
//        val currentScreen: ScreenRoute by remember { mutableStateOf(Home) } // todo remove this
        val navController = rememberNavController()
        val appState = rememberAppState();
        Scaffold(
            bottomBar = {
                Row {
                    BottomNavigation {
                        TabRoutes.forEach {
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        it.icon,
                                        contentDescription = null
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
                startDestination = Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                // todo: change later
                composable(route = Home.route) {
                    HomeScreen()
                }
                composable(route = HealthPlus.route){
                    ProfileScreen()
                }
                composable(route=Sharing.route){
                    ProfileScreen()
                }
            }
        }
    }
}

// todo
private fun NavGraphBuilder.appNavGraph( // custom name..

) {
    navigation(
        startDestination = Home.route,
        route = Home.route
    ) {
        composable(route = Home.route) {
            HomeScreen()
        }
    }

}
