package com.brokenbrains.fitness.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import com.brokenbrains.fitness.ui.screens.HomeScreen

enum class MultiFloatingState {
    Expanded, Collapsed
}

@Composable
fun MultiFloatingActionButton(
    multiFloatingState: MultiFloatingState,
    onFabStateChange: (MultiFloatingState) -> Unit,

    ) {

    val transition =
        updateTransition(targetState = multiFloatingState, label = "multiFloatingState")

    val rotation by transition.animateFloat(label = "rotate") {
        if (it == MultiFloatingState.Expanded) 405f else 0f
    }

    FloatingActionButton(
        onClick = {
            onFabStateChange(
                if (multiFloatingState == MultiFloatingState.Expanded) MultiFloatingState.Collapsed
                else MultiFloatingState.Expanded
            )
        },
        modifier = Modifier.rotate(rotation)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AppFloatingActionButtonPreview() {
    var multiFloatingState by remember {
        mutableStateOf(MultiFloatingState.Collapsed)
    }


    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                multiFloatingState = multiFloatingState,
                onFabStateChange = {
                    multiFloatingState = it
                })
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            HomeScreen(navigateTo = {})
        }
    }
}