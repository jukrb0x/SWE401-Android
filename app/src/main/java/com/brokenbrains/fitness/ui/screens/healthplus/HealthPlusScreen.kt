package com.brokenbrains.fitness.ui.screens.healthplus

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.brokenbrains.fitness.TabRoutes
import com.brokenbrains.fitness.UserRoutes
import com.brokenbrains.fitness.ui.components.MainScreenColumn
import com.brokenbrains.fitness.ui.components.MainScreenHeader

@Composable
fun HealthPlusScreen(navigateTo: (route: String) -> Unit) {
    MainScreenColumn() {
        MainScreenHeader(title = TabRoutes.HealthPlus.title, onAvatarPressed = { navigateTo(UserRoutes.Profile.route) })
        Text(text = "OK")
    }
}
