package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class SharingCardData(
    val name: String,
    val description: String,
    val avatar: String, // todo
    val color: Color
)

@Composable
fun SharingCard(modifier: Modifier = Modifier) {
    Card() {
        Row(modifier.padding(5.dp)) {
            Surface(
                shape = CircleShape,
                modifier = modifier
                    .size(30.dp)
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
            ) {
                Box(
                    modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                ) {
                    // avatar
                }
            }
            Spacer(modifier.width(5.dp))
            Column() {
                Text("Name")
                Text("Subtitle", modifier = modifier.size(10.dp))
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SharingCardPreview() {
    SharingCard()
}