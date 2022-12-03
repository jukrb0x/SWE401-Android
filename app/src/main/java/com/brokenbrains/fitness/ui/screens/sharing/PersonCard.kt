package com.brokenbrains.fitness.ui.screens.sharing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.ui.screens.sharing.Styles.AlertStyle
import com.brokenbrains.fitness.ui.screens.sharing.Styles.LatestUpdateStyle
import com.brokenbrains.fitness.ui.screens.sharing.Styles.TimeStyle
import com.brokenbrains.fitness.ui.screens.sharing.Styles.TitleStyle
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronRight


data class PersonShareData(
    val id: String,
    val name: String,
    val avatar: String, // todo
    val latestUpdate: String,
    val alert: String? = null,
    val updateTime: String,
)

private object Styles {
    val TitleStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    val LatestUpdateStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Gray
    )

    val AlertStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black // todo: doesn't support dark schema in this way.
    )

    val TimeStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Gray
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonCard(modifier: Modifier = Modifier, personShareData: PersonShareData) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = { /*TODO*/ },
        colors = CardDefaults.elevatedCardColors( // TODO awful colors
            containerColor = Color(0XFFEDF6F9),
            contentColor = Color.Black
        )
    ) {
        Row(modifier = modifier.padding(10.dp)) {
            // avatar
            Surface(
                shape = CircleShape,
                modifier = modifier
                    .size(75.dp)
                    .align(Alignment.CenterVertically)
                    .padding(5.dp)
            ) {
                // generate random light color
                // TODO: remove this out (recomposing)
                val bgColor = Color(
                    (0..255).random(),
                    (0..255).random(),
                    (0..255).random()
                ).copy(alpha = 0.5f)

                Box(
                    modifier
                        .fillMaxSize()
                        .background(/*Color.LightGray*/bgColor),
                    contentAlignment = Alignment.Center
                ) {
                    // TODO: use avatar if avatar exists
                    val nameParts = personShareData.name.split(" ")
                    var name:String = nameParts[0][0].toString()
                    if(nameParts.size>1) {
                        name = "${nameParts[0][0]}${nameParts[1][0]}"
                    }
                    Text(
                        text = name,
                        modifier = modifier.align(Alignment.Center),
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight(700),
                        )
                    )
                }
            }

            Spacer(modifier.width(8.dp))

            // Info
            Column() {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(personShareData.name, style = TitleStyle)
                    Spacer(modifier = modifier.weight(1f))
                    Text(
                        personShareData.updateTime,
                        modifier = modifier.padding(horizontal = 5.dp),
                        style = TimeStyle
                    )
                    Icon(
                        modifier = modifier.size(20.dp),
                        imageVector = FeatherIcons.ChevronRight,
                        tint = Color.Gray,
                        contentDescription = "Go to sharing details"
                    )
                }
                Column() {
                    if (!personShareData.alert.isNullOrEmpty()) Text(
                        text = personShareData.alert,
                        style = AlertStyle,
                        maxLines = 1
                    )
                    Text(personShareData.latestUpdate, style = LatestUpdateStyle, maxLines = 1)
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SharingCardPreview() {
    PersonShareData(
        id = "1",
        name = "John",
        avatar = "",
        latestUpdate = "No updates",
        updateTime = "12:34"
    ).let {
        PersonCard(personShareData = it)
    }
}
