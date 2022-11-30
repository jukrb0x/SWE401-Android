package com.brokenbrains.fitness.ui.screens.healthplus

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.brokenbrains.fitness.TabRoutes
import com.brokenbrains.fitness.ui.components.MainScreenColumn
import com.brokenbrains.fitness.ui.components.MainScreenHeader

@Composable
fun HealthPlusScreen() {
    MainScreenColumn() {
        MainScreenHeader(title = TabRoutes.HealthPlus.title)
        Text(text = "OK")
    }
}
