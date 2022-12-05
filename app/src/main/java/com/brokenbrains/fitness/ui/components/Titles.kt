package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.ui.theme.ColumnListSectionTitleStyle

@Composable
fun ColumnListSectionTitle(title: String) {
    Text(
        text = title,
        style = ColumnListSectionTitleStyle,
        modifier = Modifier.padding(5.dp)
    )
}
