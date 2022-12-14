package com.brokenbrains.fitness.ui.screens


//import androidx.compose.material.TextField
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brokenbrains.fitness.ActivityRoutes
import com.brokenbrains.fitness.BrowseRoutes
import com.brokenbrains.fitness.MeasurementsRoutes
import com.brokenbrains.fitness.data.model.HomeViewModel
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.model.activity.ActivityViewModel
import com.brokenbrains.fitness.data.model.measurement.MeasurementType
import com.brokenbrains.fitness.data.model.measurement.MeasurementViewModel
import com.brokenbrains.fitness.data.model.medication.MedicationViewModel
import com.brokenbrains.fitness.data.model.medication.getUnitString
import com.brokenbrains.fitness.data.util.CalendarUtils
import com.brokenbrains.fitness.ui.components.*
import com.brokenbrains.fitness.ui.screens.home.ElevatedMedicationNotificationCard
import com.brokenbrains.fitness.ui.screens.home.MedNotificationData
import java.time.format.DateTimeFormatterBuilder

var fakeMedNotificationData = MedNotificationData(
    title = "Time to take medication",
    subtitle = "You have 2 pills of Bohan left",
    overline = "Today at 12:00 PM",
)

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
fun HomeScreen(viewModel: HomeViewModel, navigateTo: (route: String) -> Unit) {
    val activityViewModel = hiltViewModel<ActivityViewModel>();
    val activityState by activityViewModel.uiState.collectAsStateWithLifecycle()

    val measurementViewModel = hiltViewModel<MeasurementViewModel>();
    val measurementState by measurementViewModel.uiState.collectAsStateWithLifecycle()


    val medicationViewModel = hiltViewModel<MedicationViewModel>();
    val medicationState by medicationViewModel.uiState.collectAsStateWithLifecycle()

    val data: List<TrendCardData> = listOf(
        TrendCardData(
            title = "Walking",
            graphVal = activityState.activityColumnarDataByType[ActivityType.WALKING],
            todayValue = activityState.activityTodayByType[ActivityType.WALKING]?.value,
            todayUnit = activityState.activityTodayByType[ActivityType.WALKING]?.unit,
            navRoute = ActivityRoutes.ActivityDetails.route + "/${ActivityType.WALKING}",
        ),
        TrendCardData(
            title = "Sleep time",
            subtitle = "Dec 29 - Dec 30",
            todayValue = "7",
            todayUnit = "hr",
        ),
        TrendCardData(
            title = "Weight",
            subtitle = "Latest",
            graphVal = measurementState.measurementColumnarDataByType[MeasurementType.WEIGHT],
            todayValue = measurementState.measurementTodayByType[MeasurementType.WEIGHT]?.value,
            todayUnit = measurementState.measurementTodayByType[MeasurementType.WEIGHT]?.unit,
            navRoute = MeasurementsRoutes.MeasurementDetails.route + "/${MeasurementType.WEIGHT}",
        ),
        TrendCardData(
            title = "Heart rate",
            subtitle = "today",
            todayValue = "72",
            todayUnit = "bpm",
        ),
        TrendCardData(
            title = "Exercise",
            subtitle = "Last 7 days",
            graphVal = activityState.exercisePointsLast7Days.columnarDataList,
            todayValue = activityState.exercisePointsLast7Days.totalEnergyExpand.toString(),
            todayUnit = "Pts",
        )
    )

    MainScreenColumn(horizontalPadding = 0.dp) {
        Box(modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue)) {
            MainScreenHeader(
                title = "Fitness",
                navigateTo = navigateTo,
            )
        }

        LazyColumn(
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            item {
                if (medicationState.recentHalfHourMedication.title !== null
                    && medicationState.recentHalfHourMedication.daysOfWeek !== null
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    val medToTake = medicationState.recentHalfHourMedication
                    ElevatedMedicationNotificationCard(
                        modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue),
                        data = MedNotificationData(
                            title = "Time to take medication",
                            subtitle = "You have ${medToTake.dose} ${medToTake.medicationType?.getUnitString()} of ${medToTake.title} ",
                            overline = "Today at ${
                                CalendarUtils.getDateTimeFromLong(medToTake.startAt!!)?.format(
                                    DateTimeFormatterBuilder().appendPattern("HH:mm").toFormatter()
                                )
                            }",
                        ),
                        onClick = {
                            navigateTo(BrowseRoutes.Medication.route)
                        }
                    )
                }

            }

            item {
                Box(
                    modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue),
                ) {
                    ColumnListSectionTitle(title = "TRENDS")

                }
            }
            itemsIndexed(data) { index, item ->
                TrendCard(
                    data = item,
                    modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue),
                    onClick = {
                        if(item.navRoute !== null) {
                            navigateTo(item.navRoute)
                        }
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
@Preview("dark theme", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HomeScreenPreview() {
    HomeScreen(navigateTo = {}, viewModel = hiltViewModel<HomeViewModel>())
}