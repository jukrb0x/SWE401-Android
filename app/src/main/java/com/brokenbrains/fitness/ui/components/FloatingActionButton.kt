package com.brokenbrains.fitness.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.brokenbrains.fitness.ui.theme.YaleBlue1

@Composable
fun AppFloatingActionButton() {
    FloatingActionButton(
        onClick = {},
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
//            tint = MaterialTheme.colors.onPrimary // TODO: we use our color, set it later. better use a wrapper
        )
    }
}

@Preview
@Composable
fun AppFloatingActionButtonPreview() {
    AppFloatingActionButton()
}