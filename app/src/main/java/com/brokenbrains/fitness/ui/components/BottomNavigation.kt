package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.TabRoutes
import com.brokenbrains.fitness.ui.theme.YaleBlue4


@Composable
fun AppBottomBar(
    navigateTo: (String) -> Unit,
    currentRoute: String?,
    tabRoutes: Array<TabRoutes>
) {
    Box(
        modifier = Modifier
    ) {
        /*NavigationBar*/BottomNavigation(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp, 18.dp, 0.dp, 0.dp)),
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 10.dp
    ) {
        Row(Modifier.background(Color.Transparent)) {

            tabRoutes.forEach {
                /*NavigationBarItem*/BottomNavigationItem(
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
                selectedContentColor = YaleBlue4,
                unselectedContentColor = Color.Gray,
            )
            }
        }

    }
    }
}

@Composable
private fun BottomBarItem() {
    TODO()
}


@Preview(showBackground = true)
@Composable
private fun BottomBarNavPreview() {
    AppBottomBar(navigateTo = {}, currentRoute = "main/home", tabRoutes = TabRoutes.values())
}