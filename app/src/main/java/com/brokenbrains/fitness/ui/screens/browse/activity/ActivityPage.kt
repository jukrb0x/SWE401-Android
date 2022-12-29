package com.brokenbrains.fitness.ui.screens.browse.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brokenbrains.fitness.ActivityRoutes
import com.brokenbrains.fitness.BrowseRoutes
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.model.activity.ActivityUiState
import com.brokenbrains.fitness.data.model.activity.ActivityViewModel
import com.brokenbrains.fitness.ui.components.ColumnListSectionTitle
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.components.TrendCard
import com.brokenbrains.fitness.ui.components.TrendCardData
import com.brokenbrains.fitness.ui.components.trendcard.ColumnarData
import com.brokenbrains.fitness.ui.screens.sharing.ShareWithSomeoneModal
import com.brokenbrains.fitness.ui.theme.FitnessTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
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


        val uiState by viewModel.uiState.collectAsStateWithLifecycle(lifecycle = LocalLifecycleOwner.current.lifecycle)
        ActivityPageInternal(state = uiState, navigateTo = navigateTo, onBack = onBack)
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun ActivityPageInternal(
    state: ActivityUiState,
    navigateTo: (route: String) -> Unit,
    onBack: () -> Unit
) {
//    val activities = viewModel.allActivities.collectAsState(initial = emptyList())
//    val last7daysGraphVals = remember { mutableStateListOf<ColumnarData>() } // bad
    val move = listOf(
        ActivityType.WALKING,
        ActivityType.RUNNING
    )

//    Text(text = "Size is " + state.ColumnarDataByType[ActivityType.WALKING]?.size.toString())
//    Text(text = state.ColumnarDataByType[ActivityType.WALKING].toString())
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
                    graphVal = state.activityColumnarDataByType[ActivityType.WALKING],
                    todayValue = state.activityTodayByType[ActivityType.WALKING]?.value,
                    todayUnit = state.activityTodayByType[ActivityType.WALKING]?.unit,
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
                    graphVal = state.activityColumnarDataByType[ActivityType.RUNNING] ,
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
                    graphVal = state.activityColumnarDataByType[ActivityType.SWIMMING],
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
                    graphVal = state.activityColumnarDataByType[ActivityType.CYCLING] ,
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