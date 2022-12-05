package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.ui.theme.YaleBlue3
import compose.icons.TablerIcons
import compose.icons.tablericons.Run

@Composable
fun FitnessIcon(
    modifier: Modifier = Modifier,
    iconSize: Dp = 50.dp,
    backgroundColor: Color = YaleBlue3,
    shapeColor: Color = Color.White,
    shape: Shape = CircleShape,
    onClick: () -> Unit = {},
    clickable: Boolean = true,
) {
    Surface(
        modifier = modifier.size(iconSize),
        shape = shape,
        color = backgroundColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = { onClick() }, enabled = clickable),

            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = TablerIcons.Run,
                contentDescription = "User avatar",
                modifier = Modifier.size(iconSize * 0.5f),
                tint = shapeColor,
            )

        }
    }
}