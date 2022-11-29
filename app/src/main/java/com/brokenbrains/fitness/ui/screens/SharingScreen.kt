package com.brokenbrains.fitness.ui.screens

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.brokenbrains.fitness.TabRoutes
import com.brokenbrains.fitness.ui.components.MainScreenColumn
import com.brokenbrains.fitness.ui.components.MainScreenHeader

@Composable
@Preview(showBackground = true)
fun SharingScreen() {
    MainScreenColumn {
        MainScreenHeader(title = TabRoutes.Sharing.title)
        Button(onClick = { /*TODO*/ }) {
            Text(text = "OKK")
        }
    }
}
