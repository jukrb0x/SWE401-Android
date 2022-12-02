package com.brokenbrains.fitness.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

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