package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.brokenbrains.fitness.TabRoutes

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
                    icon = { Icon(it.icon, contentDescription = it.title) },
                    label = { Text(it.title) },
                    selected = currentRoute == it.route,
                    onClick = { navigateTo(it.route) }
                )
            }
        }
    }
}

@Composable
private fun BottomBarItem() {

}


@Preview
@Composable
private fun BottomBarNavPreview() {
    AppBottomBar(navigateTo = {}, currentRoute = "main/home", tabRoutes = TabRoutes.values())
}