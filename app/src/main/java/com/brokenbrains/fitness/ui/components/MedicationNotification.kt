package com.brokenbrains.fitness.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.ui.screens.fakeMedNotificationData
import com.brokenbrains.fitness.ui.theme.FitnessTheme
import com.brokenbrains.fitness.ui.theme.warning_yellow
import compose.icons.FeatherIcons
import compose.icons.FontAwesomeIcons
import compose.icons.feathericons.ArrowUpRight
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Pills

data class MedNotificationData(
    val title: String = "",
    val subtitle: String = "",
    val overline: String = "",
)

enum class DividerType {
    None,
    Top,
    Bottom,
    Both,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationNotificationCard(
    modifier: Modifier = Modifier,
    data: MedNotificationData,
    onClick: () -> Unit,
    showDivider: DividerType = DividerType.None,
    backgroundColor: Color = Color.White,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (showDivider == DividerType.Both || showDivider == DividerType.Top) Divider()
        ListItem(
            modifier = Modifier.clickable(onClick = { onClick() }),
            colors = ListItemDefaults.colors(
                containerColor = backgroundColor,
            ),
            headlineText = { Text(data.title) },
            overlineText = { Text(data.overline) },
            supportingText = { Text(data.subtitle) },
            trailingContent = {
                Icon(
                    FeatherIcons.ArrowUpRight,
                    contentDescription = data.subtitle,
                    tint = Color.Gray
                )
            },
            leadingContent = {
                Icon(
                    FontAwesomeIcons.Solid.Pills,
                    contentDescription = data.title,
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        if (showDivider == DividerType.Both || showDivider == DividerType.Bottom) Divider()
    }
}

@Composable
fun ElevatedMedicationNotificationCard(
    modifier: Modifier = Modifier, data: MedNotificationData,
    onClick: () -> Unit,
    showDivider: DividerType = DividerType.None,
    backgroundColor: Color = warning_yellow,
    elevation: Int = 5
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(elevation.dp, (elevation+2).dp),
    ) {
        MedicationNotificationCard(
            data = data,
            onClick = { onClick() },
            showDivider = showDivider,
            backgroundColor = backgroundColor,
        )
    }

}


@Composable
@Preview
fun MedicationNotificationCardPreview() {
    FitnessTheme {
//        ElevatedCard(
//            modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue),
//        ) {
        MedicationNotificationCard(
            data = fakeMedNotificationData,
            onClick = { /*TODO*/ },
        )
//        }
    }
}
