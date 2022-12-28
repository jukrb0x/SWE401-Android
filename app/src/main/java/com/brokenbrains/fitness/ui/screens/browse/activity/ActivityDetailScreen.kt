package com.brokenbrains.fitness.ui.screens.browse.activity

import androidx.compose.runtime.Composable
import com.brokenbrains.fitness.ActivityRoutes
import com.brokenbrains.fitness.data.model.activity.ActivityViewModel
import com.brokenbrains.fitness.ui.screens.browse.components.BrowsePage

@Composable
fun ActivityDetailScreen(
    viewModel: ActivityViewModel,
    navigateTo: (route: String) -> Unit,
    onBack: () -> Unit
) {
    BrowsePage(
        title = ActivityRoutes.ActivityDetails.title,
        navigateTo = navigateTo,
        onBack = onBack
    ) {


    }


}