package com.brokenbrains.fitness.ui.screens.browse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.brokenbrains.fitness.ui.components.FitnessTopAppBar
import com.brokenbrains.fitness.ui.theme.FitnessTheme
import compose.icons.FeatherIcons
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
        FitnessTopAppBar(title = title, onBack = onBack, actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = FeatherIcons.Plus,
                    contentDescription = "Add New Item"
                )
            }
        })

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