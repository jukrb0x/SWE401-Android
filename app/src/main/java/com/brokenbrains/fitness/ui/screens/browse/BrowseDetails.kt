package com.brokenbrains.fitness.ui.screens.browse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.brokenbrains.fitness.ui.theme.FitnessTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronLeft
import compose.icons.feathericons.ChevronRight
import compose.icons.feathericons.Plus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseDetails(
    title: String,
    navigateTo: (String) -> Unit,
    onBack: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(topBar = {
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
                    modifier = Modifier/*.fillMaxWidth()*/,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6
                )
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = FeatherIcons.Plus,
                        contentDescription = "Add New Item"
                    )
                }
            },
        )
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun BrowseDetailsPreview() {
    FitnessTheme {
        BrowseDetails(navigateTo = {}, onBack = {}, content = {}, title = "okkk")
    }
}