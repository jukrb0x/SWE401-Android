package com.brokenbrains.fitness.ui.screens.browse.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.brokenbrains.fitness.ActivityRoutes
import com.brokenbrains.fitness.BrowseRoutes
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.viewmodel.ActivityViewModel
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

        // TODO: remove me
        val s = viewModel.allActivities.collectAsState(initial = emptyList())
        Row {
            Text(s.value.toString())
        }

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
    val last7daysGraphVals = remember { mutableStateListOf<ColumnarData>() }
    LaunchedEffect(Unit) {
        val list = viewModel.getLast7DaysOfActivityByTypes( // FIXME: should wait for data to be loaded
            listOf(
                ActivityType.WALKING,
                ActivityType.RUNNING
            )
        )
        last7daysGraphVals.addAll(list)
    }
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
                    graphVal = last7daysGraphVals
                )
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