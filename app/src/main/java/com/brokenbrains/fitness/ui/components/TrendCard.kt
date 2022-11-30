package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendCard(onClick: () -> Unit = {}) {
    ElevatedCard(
        onClick = { onClick() }, modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "TrendCard")

    }

}

@Composable
@Preview(showBackground = true)
fun TrendCardPreview() {
    TrendCard()
}
