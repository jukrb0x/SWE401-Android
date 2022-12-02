package com.brokenbrains.fitness.ui.screens.home

import androidx.compose.runtime.*
import com.brokenbrains.fitness.ui.components.MultiFloatingActionButton
import com.brokenbrains.fitness.ui.components.FabState

@Composable
fun AddHealthDataFab() {
    var fabState by remember {
        mutableStateOf(FabState.Collapsed)
    }

    MultiFloatingActionButton(
        fabState = fabState,
        onFabStateChange = {
            fabState = it
        })

}