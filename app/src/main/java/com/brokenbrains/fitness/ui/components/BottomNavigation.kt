package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.TabRoutes


@Composable
fun AppBottomBar(
    navigateTo: (String) -> Unit,
    currentRoute: String?,
    tabRoutes: Array<TabRoutes>
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp, 18.dp, 0.dp, 0.dp))
            .shadow(10.dp)
    ) {
        NavigationBar(
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp, 18.dp, 0.dp, 0.dp))
                .height(75.dp)
                .shadow(10.dp)
        ) {
            Row(Modifier.background(Color.Transparent)){

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