package com.brokenbrains.fitness.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import com.skydoves.cloudy.Cloudy


enum class CustomDialogPosition {
    BOTTOM, TOP
}

/**
 * Custom Screen Placement Modifier for Dialog
 * @see: https://stackoverflow.com/a/70446279/8737623
 */
fun Modifier.customPosition(pos: CustomDialogPosition) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints);
    layout(constraints.maxWidth, constraints.maxHeight) {
        when (pos) {
            CustomDialogPosition.BOTTOM -> {
                placeable.place(0, constraints.maxHeight - placeable.height, 10f)
            }
            CustomDialogPosition.TOP -> {
                placeable.place(0, 0, 10f)
            }
        }
    }
}

// dialog background modifier


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FullScreenDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    visibility: Boolean = true,
    isAnimated: Boolean = false,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    content: @Composable () -> Unit
) {
    if (visibility) {
        if (isAnimated) {
            AnimatedVisibility(
                visible = visibility, enter = slideInVertically() + expandVertically(
                    expandFrom = Alignment.Top
                ) + fadeIn(initialAlpha = 0.3f),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()

            ) {
                Dialog(
                    onDismissRequest = onDismissRequest,
                    properties = properties
                ) {
                    Box(modifier = modifier.fillMaxSize()) {
                        content()
                    }
                }
            }
        } else {
            Dialog(
                onDismissRequest = onDismissRequest,
                properties = properties
            ) {
                Box(modifier = modifier.fillMaxSize()) {
                    content()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogTopBar(onDismiss: () -> Unit, title: String, actions: @Composable () -> Unit = {}) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onDismiss) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close $title"
                )
            }
        },
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
        },
        actions = {
            if (actions != {}) actions()
        },
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    visibility: Boolean = true,
    isAnimated: Boolean = false,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    content: @Composable () -> Unit
) {
    if (visibility) {
        if (isAnimated) {
            AnimatedVisibility(
                visible = visibility, enter = slideInVertically() + expandVertically(
                    expandFrom = Alignment.Top
                ) + fadeIn(initialAlpha = 0.3f),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()

            ) {
                Dialog(
                    onDismissRequest = onDismissRequest,
                    properties = properties,
                ) {
                    content()
                }
            }
        } else {
            Dialog(
                onDismissRequest = onDismissRequest,
                properties = properties
            ) {
                content()
            }
        }
    }
}


// -----------------
// Preview
// -----------------
@Preview
@Composable
fun DialogPreview() {
    FullScreenDialog(visibility = true, onDismissRequest = {}) {
        DialogTopBar(onDismiss = {}, title = "Profile", actions = {
            IconButton(
                onClick = { /* TODO */ },
                enabled = /*resetEnabled*/false
            ) {
                val alpha = if (/*resetEnabled*/false) {
                    ContentAlpha.high
                } else {
                    ContentAlpha.disabled
                }
                CompositionLocalProvider(LocalContentAlpha provides alpha) {
                    Text(
                        text = "Edit",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        })
    }
}

@Preview
@Composable
fun DialogNoActionPreview() {
    FullScreenDialog(visibility = true, onDismissRequest = {}) {
        DialogTopBar(onDismiss = {}, title = "Profile")
    }
}

@Composable
@Preview
fun CustomDialogPreview() {
    CustomDialog(visibility = true, onDismissRequest = {}) {
        Card(
            modifier = Modifier
                .width(300.dp)
//                .alpha(ContentAlpha.medium),
        ) {
            Column() {
                Text(text = "Hello")
                TextField(value = "test", onValueChange = {})
            }
        }
    }
}