package com.brokenbrains.fitness.ui.screens

import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    Column() {

    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun test() {
    var text by remember { mutableStateOf("") }
    fun handleTextChange(newText: String) {
        text = newText
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = ::handleTextChange
        )
    }
}