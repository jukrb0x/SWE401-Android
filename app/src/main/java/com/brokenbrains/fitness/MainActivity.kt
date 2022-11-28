package com.brokenbrains.fitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
        // | change later
        // v
        val currentScreen: ScreenRoute by remember { mutableStateOf(Home) }
        // A surface container using the 'background' color from the theme
        Scaffold(
            bottomBar = {
//                BottomBar(
//                    allScreens = TabRoutes,
//                    onTabSelected = { screen -> currentScreen = screen },
//                    currentScreen = currentScreen
//                )
            }
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                currentScreen.screen()
            }
        }
    }
}