package com.brokenbrains.fitness.ui.screens.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.AppDestinations
import com.brokenbrains.fitness.R
import com.brokenbrains.fitness.UserRoutes
import com.brokenbrains.fitness.data.model.auth.AuthViewModel
import com.brokenbrains.fitness.ui.components.FitnessIcon
import com.brokenbrains.fitness.ui.theme.YaleBlue3
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(viewModel: AuthViewModel, navigateTo: (String) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = navigateTo) {
        coroutineScope.launch {
            delay(1000)
            if (viewModel.currentUser != null) { // if FireAuth has a user
                navigateTo(AppDestinations.LOGIN_ROUTE) // login action
            } else {
                navigateTo(UserRoutes.Login.route) // login screen
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            FitnessIcon(
                iconSize = 100.dp,
                modifier = Modifier.padding(top = 40.dp),
                backgroundColor = YaleBlue3
            )
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = YaleBlue3
            )

        }
    }
}