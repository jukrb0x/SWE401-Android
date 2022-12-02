package com.brokenbrains.fitness.ui.screens.home

import androidx.compose.runtime.*
import com.brokenbrains.fitness.ui.components.MultiFloatingActionButton
import com.brokenbrains.fitness.ui.components.MultiFloatingState

@Composable
fun AddHealthDataFab() {
    var multiFloatingState by remember {
        mutableStateOf(MultiFloatingState.Collapsed)
    }

    MultiFloatingActionButton(
        multiFloatingState = multiFloatingState,
        onFabStateChange = {
            multiFloatingState = it
        })

}