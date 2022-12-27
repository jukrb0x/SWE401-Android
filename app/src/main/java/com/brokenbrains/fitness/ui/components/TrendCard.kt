package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.ui.theme.OceanGreen1
import com.brokenbrains.fitness.ui.theme.Shapes
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronRight

object Styles {
    val TitleStyle = TextStyle(
        fontSize = 16.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.15.sp
    )
    val SubtitleStyle = TextStyle(
        fontSize = 12.sp, fontWeight = FontWeight.Normal, color = Color.Gray, letterSpacing = 0.8.sp
    )
}

data class Columnar(
    val value: Float, // percentage in 0...1
    val label: String // M, T, W, T, F, S, S
)

data class TrendCardData(
    val title: String,
    val subtitle: String = "Last 7 days",
    val graphVal: List<Columnar> = listOf(
        Columnar(0f, "M"),
        Columnar(0f, "T"),
        Columnar(0f, "W"),
        Columnar(0f, "T"),
        Columnar(0f, "F"),
        Columnar(0f, "S"),
        Columnar(0f, "S")
    ),
    val unit: String = "kg",
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendCard(
    modifier: Modifier = Modifier, data: TrendCardData, onClick: () -> Unit = {}, elevation: Int = 1
) {

    ElevatedCard(
        onClick = { onClick() },
        modifier = modifier.fillMaxSize(),
        elevation = CardDefaults.elevatedCardElevation(elevation.dp, (elevation + 2).dp),
//        colors = CardDefaults.elevatedCardColors( // TODO awful colors
//            containerColor = Neutral1,
//            contentColor = Color.Black
//        )

    ) {
        Column(modifier = Modifier.padding(13.dp)) {
            Row {
                Text(text = data.title, style = Styles.TitleStyle)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = FeatherIcons.ChevronRight,
                    tint = Color.Gray,
                    contentDescription = "Go to trend details"
                )
            }
            Row {
                Text(text = data.subtitle, style = Styles.SubtitleStyle)
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Row(
                        modifier = Modifier.width(100.dp), verticalAlignment = Alignment.Bottom
                    ) {
                        var valueToday = data.graphVal.last().value.toString()
                        Text(
                            text = valueToday,
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = data.unit,
                            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal),
                            modifier = Modifier.offset(x = (2).dp)
                        )
                    }
                    Text(
                        text = "Today", // latest data
                        style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(Shapes.medium)
                        .height(60.dp)
                        .background(Color./*LightGray*/Transparent)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        data.graphVal.forEach { i ->
                            TrendCardColumnar(data = i)
                        }
                    }
                }

            }

        }
    }


}

@Composable
fun TrendCardColumnar(data: Columnar) {
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
/*

@Composable
fun TrendCardHeading(){ // todo: if abstraction is necessary
    Row {
        Text(text = "Weight", style = Styles.TitleStyle)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = Icons.Filled.ArrowForward,
            tint = Color.Gray,
            contentDescription = "Go to trend details"
        )
    }
    Row {
        Text(text = "Last 7 days", style = Styles.SubtitleStyle)
    }
}

*/

@Composable
@Preview(showBackground = true)
fun TrendCardPreview() {
    val fakeData = TrendCardData(
        title = "Weight",
        subtitle = "Last 7 days",
        graphVal = listOf(
            Columnar(0.28f, "M"),
            Columnar(0.28f, "T"),
            Columnar(0.38f, "W"),
            Columnar(0.28f, "T"),
            Columnar(0.90f, "F"),
            Columnar(1f, "S"),
            Columnar(0.75f, "S")
        ),
    )
    TrendCard(data = fakeData)
}
