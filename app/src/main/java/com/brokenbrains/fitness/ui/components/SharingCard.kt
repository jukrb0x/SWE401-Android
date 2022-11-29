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

@Composable
fun SharingCard(/*modifier: Modifier*/) {
    Card() {
        Row(Modifier.padding(5.dp)) {
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .size(30.dp)
                    .clip(shape = CircleShape)
                    .background(Color.Blue)
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Blue)
                ) {
                    // avatar
                }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column() {
                Text("Title")
                Text("Subtitle")
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SharingCardPreview() {
    SharingCard()
}