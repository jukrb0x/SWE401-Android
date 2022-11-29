package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MainScreenColumn(
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 16.dp,
    verticalPadding: Dp = 0.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(modifier.padding(horizontal = horizontalPadding)) {
        Column(
            modifier = modifier.fillMaxSize(),
            content = content
        )
    }
}
