package com.brokenbrains.fitness.ui.screens.sharing

import androidx.compose.foundation.layout.*
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
import com.brokenbrains.fitness.ui.components.Avatar
import com.brokenbrains.fitness.ui.components.getInitials
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
        fontWeight = FontWeight(650)
    )
    val LatestUpdateStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Gray
    )

    val AlertStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight(650),
        color = Color.Black // todo: doesn't support dark schema in this way.
    )

    val TimeStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Gray
    )
}

fun navigateToPerson(id: String) {
    // todo
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PersonCard(modifier: Modifier = Modifier, personShareData: PersonShareData) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(1.dp, 2.dp),
        onClick = { navigateToPerson(personShareData.id) },
        colors = CardDefaults.elevatedCardColors( // TODO awful colors
            containerColor = Color/*(0XFFEDF6F9)*/.White,
            contentColor = Color.Black
        )
    ) {
        Row(modifier = modifier.padding(10.dp)) {
            // generate random light color for avatar
            val bgColor = Color(
                (0..255).random(),
                (0..255).random(),
                (0..255).random()
            ).copy(alpha = 0.5f)

            Avatar(
                modifier = Modifier.padding(5.dp),
                avatarSize = 60.dp,
                nameInitials = getInitials(personShareData.name),
                backgroundColor = bgColor,
                clickable = true,
                onClick = { navigateToPerson(personShareData.id) }
            )
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
