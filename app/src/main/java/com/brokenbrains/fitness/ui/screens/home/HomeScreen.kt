package com.brokenbrains.fitness.ui.screens


//import androidx.compose.material.TextField
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brokenbrains.fitness.data.model.HomeViewModel
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.model.activity.ActivityViewModel
import com.brokenbrains.fitness.ui.components.*
import com.brokenbrains.fitness.ui.screens.home.ElevatedMedicationNotificationCard
import com.brokenbrains.fitness.ui.screens.home.MedNotificationData

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

    val data: List<TrendCardData> = listOf(
        TrendCardData(
            title = "Walking",
            graphVal = activityState.activityColumnarDataByType[ActivityType.WALKING],
            todayValue = activityState.activityTodayByType[ActivityType.WALKING]?.value,
            todayUnit = activityState.activityTodayByType[ActivityType.WALKING]?.unit,
        ),
        TrendCardData(
            title = "Sleep time",
            subtitle = "Last week",
        ),
        TrendCardData(
            title = "Weight",
            subtitle = "2 Dec",
        ),
        TrendCardData(
            title = "Heart rate",
            subtitle = "2 Dec",
        ),
        TrendCardData(
            title = "Exercise",
            subtitle = "2 Dec",
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
                Spacer(modifier = Modifier.height(10.dp))
                ElevatedMedicationNotificationCard(
                    modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue),
                    data = fakeMedNotificationData,
                    onClick = {}
                )
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
                    modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue)
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