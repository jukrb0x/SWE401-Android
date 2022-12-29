package com.brokenbrains.fitness.ui.screens.browse.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import com.brokenbrains.fitness.ui.screens.browse.components.BrowsePage
import com.brokenbrains.fitness.ui.theme.FitnessTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ActivityPage(
    viewModel: ActivityViewModel,
    navigateTo: (route: String) -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(initialValue = ActivityUiState())

    BrowsePage(
        title = BrowseRoutes.Activity.title, navigateTo = navigateTo, onBack = onBack, onAdd = {
            navigateTo(ActivityRoutes.AddActivity.route)
        }
    ) {
//        val uiState by viewModel.uiState.collectAsState(initial = ActivityUiState())
        ActivityPageInternal(state = uiState, navigateTo = navigateTo, onBack = onBack)
    }
}

@Composable
private fun ActivityPageInternal(
    state: ActivityUiState,
    navigateTo: (route: String) -> Unit,
    onBack: () -> Unit
) {

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
                    subtitle = "Last 7 days",
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
                    subtitle = "Last 7 days",
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
                    subtitle = "Last 7 days",
                    graphVal = state.activityColumnarDataByType[ActivityType.RUNNING],
                    todayValue = state.activityTodayByType[ActivityType.RUNNING]?.value,
                    todayUnit = state.activityTodayByType[ActivityType.RUNNING]?.unit,
                ),
                onClick = {
                    navigateTo(ActivityRoutes.ActivityDetails.route + "/${ActivityType.RUNNING}")
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))

            TrendCard(
                data = TrendCardData(
                    title = "Swimming",
                    subtitle = "Last 7 days",
                    graphVal = state.activityColumnarDataByType[ActivityType.SWIMMING],
                    todayValue = state.activityTodayByType[ActivityType.SWIMMING]?.value,
                    todayUnit = state.activityTodayByType[ActivityType.SWIMMING]?.unit,
                ),
                onClick = {
                    navigateTo(ActivityRoutes.ActivityDetails.route + "/${ActivityType.SWIMMING}")
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))

            TrendCard(
                data = TrendCardData(
                    title = "Cycling",
                    subtitle = "Last 7 days",
                    graphVal = state.activityColumnarDataByType[ActivityType.CYCLING],
                    todayValue = state.activityTodayByType[ActivityType.CYCLING]?.value,
                    todayUnit = state.activityTodayByType[ActivityType.CYCLING]?.unit,
                ),
                onClick = {
                    navigateTo(ActivityRoutes.ActivityDetails.route + "/${ActivityType.CYCLING}")
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))

            TrendCard(data = TrendCardData(
                title = "Other workouts",
                subtitle = "Last 7 days",
                graphVal = state.activityColumnarDataByType[ActivityType.OTHER],
                todayValue = state.activityTodayByType[ActivityType.OTHER]?.value,
                todayUnit = state.activityTodayByType[ActivityType.OTHER]?.unit,
            ),
                onClick = {
                    navigateTo(ActivityRoutes.ActivityDetails.route + "/${ActivityType.OTHER}")
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))

        }
    }
}


@Composable
@Preview(showBackground = true)
private fun BrowseActivityPreview() {
    FitnessTheme {
        ActivityPage(viewModel = hiltViewModel<ActivityViewModel>(), navigateTo = {}, onBack = {})
    }

}