package com.brokenbrains.fitness.ui.screens.browse

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.TabRoutes
import com.brokenbrains.fitness.ui.components.MainScreenColumn
import com.brokenbrains.fitness.ui.components.MainScreenHeader
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.theme.FitnessTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseScreen(navigateTo: (route: String) -> Unit) {
    MainScreenColumn(horizontalPadding = 0.dp) {
        Box(modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue)) {
            MainScreenHeader(
                title = TabRoutes.Browse.title,
            )
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            ListItem(
                headlineText = { Text("Two line list item with trailing") },
                supportingText = { Text("Secondary text") },
                trailingContent = { Text("meta") },
                leadingContent = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Localized description",
                    )
                }
            )
            ListItem(
                modifier = Modifier.clickable(onClick = { }),
                headlineText = { Text("Two line list item with trailing") },
                overlineText = { Text("OVERLINE") },
                supportingText = { Text("Secondary text") },
                trailingContent = { Text("meta") },
                leadingContent = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Localized description",
                    )
                }
            )
            Divider()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BrowseScreenPreview() {
    FitnessTheme{
        BrowseScreen(navigateTo = {})
    }
}
