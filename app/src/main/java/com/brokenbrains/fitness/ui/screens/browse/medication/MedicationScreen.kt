@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.brokenbrains.fitness.ui.screens.browse.measurements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brokenbrains.fitness.data.model.measurement.*
import com.brokenbrains.fitness.data.util.CalendarUtils
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.screens.browse.activity.components.ActivityNotFound
import com.brokenbrains.fitness.ui.screens.browse.components.BrowsePage
import compose.icons.FontAwesomeIcons
import compose.icons.TablerIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Ruler
import compose.icons.fontawesomeicons.solid.Weight
import compose.icons.tablericons.Bike
import compose.icons.tablericons.Walk
import java.time.format.DateTimeFormatter

@Composable
fun MedicationScreen(
    viewModel: MeasurementViewModel,
    measurementType: MeasurementType,
    navigateTo: (route: String) -> Unit,
    onBack: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val allMeasurements = uiState.allMeasurements
    BrowsePage(
        title = measurementType.toReadableString(),
        navigateTo = navigateTo,
        onBack = onBack
    ) {
        InsideColumn(measurementType = measurementType, allActivities = allMeasurements)
    }

}

@Composable
private fun InsideColumn(
    measurementType: MeasurementType,
    allActivities: List<MeasurementModel>
) {
    // filter type
    val filteredActivities = allActivities.filter { it.measurementType == measurementType }
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    if (filteredActivities.isEmpty()) {
        ActivityNotFound(message = "No ${measurementType.toReadableString()} records found")
    }
    LazyColumn {
        items(items = filteredActivities) { item ->
            val startDate = CalendarUtils.getDateTimeFromLong(item.startAt!!)!!.format(formatter)
//            val duration = item.getDuration()
            val icon = when (item.measurementType) {
                MeasurementType.HEIGHT -> FontAwesomeIcons.Solid.Ruler
                MeasurementType.WEIGHT -> FontAwesomeIcons.Solid.Weight
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
                        text = item.measurementType!!.toReadableString(),
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                ) {
                    Text(text = startDate)
                    Text(text = "${item.value} ${item.measurementType?.getUnitString()}")
//                    Text(text = "${duration.hours}:${duration.minutes}:${duration.seconds}")
                }
            }
            Divider()
        }


    }
}
