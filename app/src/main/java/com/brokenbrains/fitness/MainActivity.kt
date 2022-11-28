package com.brokenbrains.fitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        var currentScreen: ScreenRoute by remember { mutableStateOf(Home) }
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                Row(Modifier.padding(16.dp)) {
                    TabRoutes.forEach {
                        Button(
                            onClick = { currentScreen = it },
//                            modifier = Modifier.clip(Shapes.large)
                        ) {
                            Text(text = it.route)
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            }
        ) {
            // A surface container using the 'background' color from the theme
            NavHost(navController = navController, startDestination = currentScreen.route) {
                // todo: use a nav graph to navigate between screens
                composable(route = Home.route) {
                    HomeScreen()
                }
                composable(route = Profile.route) {
                    ProfileScreen()
                }
            }
        }
    }
}

// todo
private fun NavGraphBuilder.appNavGraph(

) {

}
