@file:OptIn(ExperimentalMaterial3Api::class)

package com.brokenbrains.fitness.ui.screens.browse

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.BrowseRoutes
import com.brokenbrains.fitness.TabRoutes
import com.brokenbrains.fitness.ui.components.MainScreenColumn
import com.brokenbrains.fitness.ui.components.MainScreenHeader
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.theme.FitnessTheme
import compose.icons.FeatherIcons
import compose.icons.FontAwesomeIcons
import compose.icons.feathericons.Activity
import compose.icons.feathericons.Moon
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Pills
import compose.icons.fontawesomeicons.solid.RulerHorizontal
import compose.icons.fontawesomeicons.solid.Running

interface BrowseItemState {
    val title: String
    val icon: @Composable () -> Unit
    val route: String
}

@Composable
fun BrowseItemState.BrowseItem(navigateTo: (route: String) -> Unit = {}) {
    ListItem(
        modifier = Modifier.clickable(onClick = { navigateTo(this.route) }),
        headlineText = { Text(this.title) },
        leadingContent = { this.icon() }
    )
}

@Composable
fun BrowseScreen(navigateTo: (route: String) -> Unit, onBack: () -> Unit) {
    MainScreenColumn(horizontalPadding = 0.dp) {
        Box(modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue)) {
            MainScreenHeader(
                title = TabRoutes.Browse.title, navigateTo = navigateTo
            )
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            for (browseItem in BrowseItems) {
                browseItem.BrowseItem(navigateTo)
            }
            Divider()
            object : BrowseItemState {
                override val title: String = "Medication"
                override val icon: @Composable () -> Unit = {
                    Icon(
                        FontAwesomeIcons.Solid.Pills,
                        modifier = Modifier.size(24.dp),
                        contentDescription = "Medication",
                    )
                }
                override val route: String = BrowseRoutes.Medication.route
            }.BrowseItem(navigateTo)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BrowseScreenPreview() {
    FitnessTheme {
        BrowseScreen(navigateTo = {}, onBack = {})
    }
}

data class BrowseItemData(
    override val title: String,
    override val icon: @Composable () -> Unit,
    override val route: String,
) : BrowseItemState

val BrowseItems: List<BrowseItemData> = listOf(
    BrowseItemData(
        title = "Activity",
        icon = {
            Icon(
                FontAwesomeIcons.Solid.Running,
                modifier = Modifier.size(24.dp),
                contentDescription = "Localized description",
            )
        },
        route = BrowseRoutes.Activity.route,
    ),
    BrowseItemData(
        title = "Measurements",
        icon = {
            Icon(
                FontAwesomeIcons.Solid.RulerHorizontal,
                modifier = Modifier.size(24.dp),
                contentDescription = "Measurements",
            )
        },
        route = BrowseRoutes.Measurements.route,
    ),
    BrowseItemData(
        title = "Vitals",
        icon = {
            Icon(
                FeatherIcons.Activity,
                contentDescription = "Localized description",
            )
        },
        route = BrowseRoutes.Vitals.route,
    ),
    BrowseItemData(
        title = "Sleep",
        icon = {
            Icon(
                FeatherIcons.Moon,
                contentDescription = "sleep",
            )
        },
        route = BrowseRoutes.Sleep.route,
    )
)