package com.brokenbrains.fitness.ui.screens.healthplus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.R

data class HealthInfoItemData(
    val id: String,
    val title: String,
    val type: String,
    val description: String,
    val timeSet: String,

    )

object HealthItemStyles {
    val TitleStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
    )

    val DescriptionStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Gray
    )

    val timeSetStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF3D96C0)
    )

    val typeStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Gray
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthInfoItem(modifier: Modifier = Modifier, healthInfoItemData: HealthInfoItemData) {
    val articleIC = painterResource(R.drawable.ic_article)
    val videoIC = painterResource(R.drawable.ic_video)
    val visbilityOffIC = painterResource(R.drawable.ic_visibility_off)
    val timeSetIC = painterResource(R.drawable.ic_alarm)
    var painterNow: Painter? = null
    var colorNow: Color? = null
    when (healthInfoItemData.type) {
        "Article" -> {
            painterNow = articleIC
            colorNow = Color(0xFF3D96C0)
        }
        "Video" -> {
            painterNow = videoIC
            colorNow = Color(0xFF3D96C0)
        }
        "Hidden" -> {
            painterNow = visbilityOffIC
            colorNow = Color(0xFF3D96C0)
        }
    }
    if (healthInfoItemData.type == "Article") {
        painterNow = articleIC
        colorNow = Color(0xFFF86132)
    } else if (healthInfoItemData.type == "Video") {
        painterNow = videoIC
        colorNow = Color(0xFF16A093)
    } else {
        painterNow = visbilityOffIC
    }

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp),
        //.clip(RoundedCornerShape(20.dp))
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = { /*TODO*/ },
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFE5E5E5),
        )
    ) {
        Row(
            modifier = modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = painterNow,
                contentDescription = "Type index",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxSize(),
                colorFilter = colorNow?.let { ColorFilter.tint(it) }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = healthInfoItemData.title,
                    style = HealthItemStyles.TitleStyle,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 5.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    letterSpacing = 0.25.sp
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = healthInfoItemData.description,
                    style = HealthItemStyles.DescriptionStyle,
                    modifier = Modifier.padding(start = 5.dp, end = 10.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(1.dp))
                Row() {
                    Image(
                        painter = timeSetIC,
                        contentDescription = "Time IC",
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(22.dp)
                            .align(Alignment.CenterVertically),
                        colorFilter = ColorFilter.tint(Color(0xFF3D96C0))
                    )
                    Text(
                        text = healthInfoItemData.timeSet,
                        style = HealthItemStyles.timeSetStyle,
                        modifier = Modifier.padding(1.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = healthInfoItemData.type,
                        style = HealthItemStyles.typeStyle,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun HealthInfoItemPreview() {
    HealthInfoItemData(
        id = "1",
        title = "Article 1",
        type = "Article",
        description = "The Tao that can be trodden is not the enduring and unchanging Tao. The name that can be named is not the enduring and unchanging name.",
        timeSet = "42 mins ago",
    ).let { HealthInfoItem(healthInfoItemData = it) }
}

val HealtInfoItemList: List<HealthInfoItemData> = listOf(
    HealthInfoItemData(
        id = "1",
        title = "Article 1",
        type = "Article",
        description = "The Tao that can be trodden is not the enduring and unchanging Tao. The name that can be named is not the enduring and unchanging name.",
        timeSet = "42 mins ago",
    ),
    HealthInfoItemData(
        id = "2",
        title = "Video 1",
        type = "Video",
        description = "The Tao that can be trodden is not the enduring and unchanging Tao. The name that can be named is not the enduring and unchanging name.",
        timeSet = "40 mins ago",
    ), HealthInfoItemData(
        id = "3",
        title = "Guide-book 1",
        type = "GuideBook",
        description = "The Tao that can be trodden is not the enduring and unchanging Tao. The name that can be named is not the enduring and unchanging name.",
        timeSet = "35 mins ago",
    ), HealthInfoItemData(
        id = "4",
        title = "Article 2",
        type = "Article",
        description = "The Tao that can be trodden is not the enduring and unchanging Tao. The name that can be named is not the enduring and unchanging name.",
        timeSet = "57 mins ago",
    ), HealthInfoItemData(
        id = "5",
        title = "Video 2",
        type = "Article",
        description = "The Tao that can be trodden is not the enduring and unchanging Tao. The name that can be named is not the enduring and unchanging name.",
        timeSet = "42 mins ago",
    ), HealthInfoItemData(
        id = "6",
        title = "Article 3",
        type = "Article",
        description = "The Tao that can be trodden is not the enduring and unchanging Tao. The name that can be named is not the enduring and unchanging name.",
        timeSet = "42 mins ago",
    )
)
