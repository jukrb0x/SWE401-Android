package com.brokenbrains.fitness.ui.screens


//import androidx.compose.material.TextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.brokenbrains.fitness.TabRoutes
import com.brokenbrains.fitness.ui.components.MainScreenColumn
import com.brokenbrains.fitness.ui.components.MainScreenHeader
import com.brokenbrains.fitness.ui.components.TestHello

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(/*TODO test use*/ navigateTo: (route: String) -> Unit) {
    var text by remember { mutableStateOf("点下面的按钮") }
    fun handleTextChange(newText: String) {
        text = newText
    }

    MainScreenColumn {
        MainScreenHeader(TabRoutes.Home.title)
        Column(
            Modifier.align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TestHello(name = text.trim())
            TextField(
                value = text,
                onValueChange = ::handleTextChange
            )
            Button(onClick = { navigateTo("user/profile") }) {
                Text(text = "PROFILE")
            }

        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(navigateTo = {})
}