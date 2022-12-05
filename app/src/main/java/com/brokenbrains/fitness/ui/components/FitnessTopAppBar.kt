package com.brokenbrains.fitness.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronLeft

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessTopAppBar(
    title: String,
    onBack: () -> Unit,
    actions: @Composable () -> Unit = {}
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = FeatherIcons.ChevronLeft,
                    contentDescription = "Go Back"
                )
            }
        },
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
        },
        actions = {
            if (actions != {}) actions()
        },
    )
}