package com.brokenbrains.fitness.ui.screens.browse.activity

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.brokenbrains.fitness.ActivityRoutes
import com.brokenbrains.fitness.ui.screens.browse.components.BrowsePage

@Composable
fun AddActivityScreen(navigateTo: (route: String) -> Unit, onBack: () -> Unit) {
    BrowsePage(
        title = ActivityRoutes.AddActivity.name
        , navigateTo = navigateTo, onBack = onBack) {
        Text("ok")
    }
}