package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendCard(onClick: () -> Unit = {}) {
    ElevatedCard(onClick = {onClick()}, modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Text(text = "TrendCard")

    }
        
}

@Composable
@Preview(showBackground = true)
fun TrendCardPreview() {
    TrendCard()
}
