package com.brokenbrains.fitness.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.brokenbrains.fitness.ScreenRoute
import com.brokenbrains.fitness.TabRoutes

@Composable
fun AppBottomBar(
    navigateTo: (String) -> Unit,
    currentRoute: String,
    tabRoutes: List<ScreenRoute>
) {


}

@Composable
private fun BottomBarItem(){

}


@Preview
@Composable
private fun BottomBarNavPreview(){
    AppBottomBar(navigateTo = {}, currentRoute = "home", tabRoutes = TabRoutes)
}