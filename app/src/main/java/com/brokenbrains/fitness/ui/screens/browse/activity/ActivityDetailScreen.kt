@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.brokenbrains.fitness.ui.screens.browse.activity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import com.brokenbrains.fitness.data.model.activity.*
import com.brokenbrains.fitness.data.util.CalendarUtils
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.screens.browse.activity.components.ActivityNotFound
import com.brokenbrains.fitness.ui.screens.browse.components.BrowsePage
import compose.icons.TablerIcons
import compose.icons.tablericons.Bike
import compose.icons.tablericons.Run
import compose.icons.tablericons.Swimming
import compose.icons.tablericons.Walk
import java.time.format.DateTimeFormatter

@Composable
fun ActivityDetailScreen(
    viewModel: ActivityViewModel,
    activityType: ActivityType,
    navigateTo: (route: String) -> Unit,
    onBack: () -> Unit
) {

    val allActivities = viewModel._allActivities
    BrowsePage(
        title = activityType.toReadableString(),
        navigateTo = navigateTo,
        onBack = onBack
    ) {
        ActivityColumn(activityType = activityType, allActivities = allActivities)

        LazyColumn {
            item {
//                ActivityColumn(activityType = activityType, allActivities = allActivities)
            }

        }
    }
}

@Composable
private fun ActivityColumn(
    activityType: ActivityType,
    allActivities: List<ActivityModel>
) {
    // filter type
    val filteredActivities = allActivities.filter { it.activityType == activityType }
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    if (filteredActivities.isEmpty()) {
        ActivityNotFound()
    }
    LazyColumn {
        items(items = filteredActivities) { item ->
            val startDate = CalendarUtils.getDateTimeFromLong(item.startAt!!)!!.format(formatter)
            val duration = item.getDuration()
            val icon = when (item.activityType) {
                ActivityType.WALKING -> TablerIcons.Walk
                ActivityType.RUNNING -> TablerIcons.Run
                ActivityType.CYCLING -> TablerIcons.Bike
                ActivityType.SWIMMING -> TablerIcons.Swimming
                ActivityType.OTHER -> TablerIcons.Walk
                else -> {
                    TablerIcons.Walk
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MainScreenHorizontalPaddingValue),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(24.dp)
                    )
                    Text(
                        text = item.activityType!!.toReadableString(),
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                ) {
                    Text(text = startDate)
                    Text(text = "${duration.hours}:${duration.minutes}:${duration.seconds}")
                }
            }
            Divider()
        }


    }
}
