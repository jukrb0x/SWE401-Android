package com.brokenbrains.fitness.ui.components.trendcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.ui.theme.OceanGreen1


data class ColumnarData(
    val value: Float, // percentage in 0...1
    val label: String // M, T, W, T, F, S, S
)

@Composable
fun TrendCardColumnar(data: ColumnarData) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .width(20.dp)
                .height((data.value * 40).dp)
                .clip(RoundedCornerShape(5.dp))
                .background(OceanGreen1)
        )
        Text(
            text = data.label, style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
        )
    }

}
