package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.TabRoutes
import com.brokenbrains.fitness.ui.theme.OceanGreen4
import com.brokenbrains.fitness.ui.theme.YaleBlue3


@Composable
fun AppBottomBar(
    navigateTo: (String) -> Unit,
    currentRoute: String?,
    tabRoutes: Array<TabRoutes>
) {
    Row {
        NavigationBar() {
            tabRoutes.forEach {
                NavigationBarItem(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = it.title,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    label = { Text(it.title) },
                    selected = currentRoute == it.route,
                    onClick = { navigateTo(it.route) },
                    alwaysShowLabel = false,

                )
            }
        }
    }
}

@Composable
private fun BottomBarItem() {
    TODO()
}


@Preview
@Composable
private fun BottomBarNavPreview() {
    AppBottomBar(navigateTo = {}, currentRoute = "main/home", tabRoutes = TabRoutes.values())
}