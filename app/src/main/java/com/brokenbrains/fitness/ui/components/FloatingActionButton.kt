package com.brokenbrains.fitness.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.brokenbrains.fitness.ui.screens.HomeScreen
import com.brokenbrains.fitness.ui.theme.YaleBlue4

enum class FabState {
    Expanded, Collapsed
}

val FabState.isExpanded
    get() = this == FabState.Expanded

private val fabRotateDegrees = mapOf(
    FabState.Expanded to 135f,
    FabState.Collapsed to 0f
)

@Composable
fun NormalFloatingActionButton(
    fabState: FabState,
    onFabStateChange: (FabState) -> Unit,
) {
    val transition =
        updateTransition(targetState = fabState, label = "FloatingState")

    val rotation by transition.animateFloat(label = "rotate") {
        fabRotateDegrees[it] ?: 0f
    }

    FloatingActionButton(
        modifier = Modifier.rotate(rotation),
        onClick = {
            onFabStateChange(
                if (fabState == FabState.Expanded) FabState.Collapsed
                else FabState.Expanded
            )
        },
        backgroundColor = YaleBlue4,
        contentColor = Color.White,
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }
    )
}

@Composable
fun MultiFloatingActionButton(
    fabState: FabState,
    onFabStateChange: (FabState) -> Unit,
) {

    val transition =
        updateTransition(targetState = fabState, label = "multiFloatingState")

    val rotation by transition.animateFloat(label = "rotate") {
        fabRotateDegrees[it] ?: 0f
    }

    FloatingActionButton(
        onClick = {
            onFabStateChange(
                if (fabState == FabState.Expanded) FabState.Collapsed
                else FabState.Expanded
            )
        },
        modifier = Modifier.rotate(rotation),
        backgroundColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint = YaleBlue4
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AppFloatingActionButtonPreview() {
    var fabState by remember {
        mutableStateOf(FabState.Collapsed)
    }


    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                fabState = fabState,
                onFabStateChange = {
                    fabState = it
                })
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            HomeScreen(navigateTo = {})
        }
    }
}