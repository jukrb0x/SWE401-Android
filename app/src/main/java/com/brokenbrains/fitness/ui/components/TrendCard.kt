package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
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
import com.brokenbrains.fitness.ui.theme.Shapes
import compose.icons.FeatherIcons
import compose.icons.FontAwesomeIcons
import compose.icons.feathericons.ChevronRight
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ChevronRight

object Styles {
    val TitleStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.15.sp
    )
    val SubtitleStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Gray,
        letterSpacing = 0.8.sp
    )
}

data class TrendCardData(
//    val id: String,
    val title: String,
    val subtitle: String,
//    val graph: String, // todo
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendCard(data: TrendCardData, onClick: () -> Unit = {}) {
    ElevatedCard(
        onClick = { onClick() }, modifier = Modifier
            .fillMaxSize(),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
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
                    Row(modifier = Modifier.width(100.dp), verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "100",
                            style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "kg",
                            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal),
                            modifier = Modifier.offset(x = (2).dp)
                        )
                    }
                    Text(
                        text = "Today",
                        style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(Shapes.medium)
                        .height(60.dp)
                        .background(Color.LightGray)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Graph")
                }

            }

        }
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
        subtitle = "Last 7 days"
    )
    TrendCard(fakeData)
}
