package com.brokenbrains.fitness.ui.screens


//import androidx.compose.material.TextField
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.UserRoutes
import com.brokenbrains.fitness.ui.components.MainScreenColumn
import com.brokenbrains.fitness.ui.components.MainScreenHeader
import com.brokenbrains.fitness.ui.components.TrendCard

val MainScreenHorizontalPaddingValue: Dp = 16.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(navigateTo: (route: String) -> Unit) {
    var text by remember { mutableStateOf("test") }
    fun handleTextChange(newText: String) {
        text = newText
    }

    MainScreenColumn(horizontalPadding = 0.dp) {
        Box(modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue)) {
            MainScreenHeader(
                title = /*TabRoutes.Home.title*/ "Fitness",
                onAvatarPressed = { navigateTo(UserRoutes.Profile.route) })
        }
        LazyColumn(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = MainScreenHorizontalPaddingValue)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text("Hello, ${text.trim()}")
                TextField(
                    value = text,
                    onValueChange = ::handleTextChange
                )

                TrendCard()

            }
        }
    }
}


@Composable
@Preview(showBackground = true)
@Preview("dark theme", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HomeScreenPreview() {
    HomeScreen(navigateTo = {})
}