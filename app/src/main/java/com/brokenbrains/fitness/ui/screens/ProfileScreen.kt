package com.brokenbrains.fitness.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.brokenbrains.fitness.R
import com.brokenbrains.fitness.ui.components.TestHello


@Preview(showBackground = true)
@Composable
fun ProfileScreen() {
    val image = painterResource(id = R.drawable.ic_account)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = image, contentDescription = "User Account Image")
        //User name
        Text(text = "User Name")
        Button(onClick = { /* Do something! */ }) { Text("Button") }

        TestHello(name = "Profile Screen")
    }
}