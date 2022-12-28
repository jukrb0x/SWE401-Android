package com.brokenbrains.fitness.ui.screens.browse.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.brokenbrains.fitness.ActivityRoutes
import com.brokenbrains.fitness.BrowseRoutes
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.model.activity.ActivityViewModel
import com.brokenbrains.fitness.ui.components.ColumnListSectionTitle
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.components.TrendCard
import com.brokenbrains.fitness.ui.components.TrendCardData
import com.brokenbrains.fitness.ui.components.trendcard.ColumnarData
import com.brokenbrains.fitness.ui.screens.sharing.ShareWithSomeoneModal
import com.brokenbrains.fitness.ui.theme.FitnessTheme

@Composable
fun ActivityPage(
    viewModel: ActivityViewModel,
    navigateTo: (route: String) -> Unit, onBack: () -> Unit
) {
    var addActivityModalVis by rememberSaveable { mutableStateOf(false) }

    BrowsePage(
        title = BrowseRoutes.Activity.title, navigateTo = navigateTo, onBack = onBack, onAdd = {
//            addActivityModalVis = true
            navigateTo(ActivityRoutes.AddActivity.route)
        }
    ) {

        ShareWithSomeoneModal(
            onDismiss = { addActivityModalVis = false },
            visibility = addActivityModalVis,
            onActionButtonPressed = { }
        )

        ActivityPageInternal(viewModel = viewModel, navigateTo = navigateTo, onBack = onBack)
    }
}

@Composable
private fun ActivityPageInternal(
    viewModel: ActivityViewModel,
    navigateTo: (route: String) -> Unit,
    onBack: () -> Unit
) {
//    val activities = viewModel.allActivities.collectAsState(initial = emptyList())
//    val last7daysGraphVals = remember { mutableStateListOf<ColumnarData>() } // bad
    val move = listOf(
        ActivityType.WALKING,
        ActivityType.RUNNING
    )
    val defaultGraphVals = listOf(
        ColumnarData(0f, "M"),
        ColumnarData(0f, "T"),
        ColumnarData(0f, "W"),
        ColumnarData(0f, "T"),
        ColumnarData(0f, "F"),
        ColumnarData(0f, "S"),
        ColumnarData(0f, "S")
    )

    var last7daysGraphVals by rememberSaveable { mutableStateOf(defaultGraphVals) }
    var allActivities = viewModel.allActivities

    LaunchedEffect(key1 = last7daysGraphVals) {
        // TODO
//        val last7activities = allActivities.takeLast(7)
//        allActivities.filter { move.contains(it.activityType) }.forEach { it ->
        // get last 7 item


        //            last7daysGraphVals = last7daysGraphVals.mapIndexed { index, columnarData ->
        //                if (index == it.date.dayOfWeek.value - 1) {
        //                    columnarData.copy(value = columnarData.value + it.duration)
        //                } else {
        //                    columnarData
        //                }
        //            }
//        }
//        last7daysGraphVals = viewModel.getLast7DaysOfActivityByTypes(move)
    }

//    LaunchedEffect(Unit) {
//
//
////        /*val list = */viewModel.getLast7DaysOfActivityByTypes( // FIXME: should wait for data to be loaded
//    )
////    .observeAsState()
////    observeAsState(initial = emptyList()){
////            last7daysGraphVals.addAll(it)
////        }
//    }
    // debug:
//    Text(text = "Activity Page")
//    Text(last7daysGraphVals.toString())
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = MainScreenHorizontalPaddingValue)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        item {
            ColumnListSectionTitle(title = "INSIGHTS")
            TrendCard(
                data = TrendCardData(
                    title = "Move",
                    subtitle = "Last 7 times",
                    graphVal = viewModel.getLast7DaysColumnarDataByType(ActivityType.WALKING),
                    todayValue = "0",
                    todayUnit = "min",
                ),
                onClick = {
                    navigateTo(ActivityRoutes.ActivityDetails.route + "/${ActivityType.WALKING}")
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))
            TrendCard(
                data = TrendCardData(
                    title = "Weight",
                    subtitle = "Last 7 times",
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            TrendCard(
                data = TrendCardData(
                    title = "Energy expended",
                    subtitle = "Last 7 days"
                )
            )
        }

        item {
            ColumnListSectionTitle(title = "OTHERS")
            TrendCard(
                data = TrendCardData(
                    title = "Running",
                    subtitle = "Last 7 times",
                ),
                onClick = {
                    navigateTo(ActivityRoutes.ActivityDetails.route + "/${ActivityType.RUNNING}")
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))

            TrendCard(
                data = TrendCardData(
                    title = "Swimming",
                    subtitle = "Last 7 times",
                ),
                onClick = {
                    navigateTo(ActivityRoutes.ActivityDetails.route + "/${ActivityType.SWIMMING}")
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))

            TrendCard(
                data = TrendCardData(
                    title = "Cycling",
                    subtitle = "Last 7 times",
                ),
                onClick = {
                    navigateTo(ActivityRoutes.ActivityDetails.route + "/${ActivityType.CYCLING}")
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))


        }
//        items()

    }
}


@Composable
@Preview(showBackground = true)
private fun BrowseActivityPreview() {
    FitnessTheme {
        ActivityPage(viewModel = hiltViewModel<ActivityViewModel>(), navigateTo = {}, onBack = {})
    }

}