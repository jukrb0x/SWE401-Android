package com.brokenbrains.fitness.ui.screens.home

import androidx.compose.runtime.*
import com.brokenbrains.fitness.ui.components.FabState
import com.brokenbrains.fitness.ui.components.MultiFloatingActionButton

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