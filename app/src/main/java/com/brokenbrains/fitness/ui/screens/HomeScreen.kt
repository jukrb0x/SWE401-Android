package com.brokenbrains.fitness.ui.screens


//import androidx.compose.material.TextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
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
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen() {
    var text by remember { mutableStateOf("") }
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
        }
    }
}