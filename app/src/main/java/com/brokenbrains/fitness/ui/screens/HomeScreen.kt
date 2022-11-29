package com.brokenbrains.fitness.ui.screens


//import androidx.compose.material.TextField
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.ui.components.TestHello

@Composable
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen() {
    var text by remember { mutableStateOf("") }
    fun handleTextChange(newText: String) {
        text = newText
    }

    var text2 by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("example", TextRange(0, 7)))
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TestHello(name = "Home Screen")

        Box(/*Modifier.size(100.dp)*/) {
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.TopStart)
            ) { Text("WWW") }
            TextField(
                modifier = Modifier.align(Alignment.BottomEnd),
                value = text,
                onValueChange = ::handleTextChange
            )

        }
    }
}