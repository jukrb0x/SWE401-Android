package com.brokenbrains.fitness.ui.screens


//import androidx.compose.material.TextField
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.ui.components.*
import com.brokenbrains.fitness.ui.screens.home.ElevatedMedicationNotificationCard
import com.brokenbrains.fitness.ui.screens.home.MedNotificationData

var fakeMedNotificationData = MedNotificationData(
    title = "Time to take medication",
    subtitle = "You have 2 pills of Bohan left",
    overline = "Today at 12:00 PM",
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(navigateTo: (route: String) -> Unit) {
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            item {
                Text("TODO display quick facts about your health") // TODO
                Spacer(modifier = Modifier.height(10.dp))
                ElevatedMedicationNotificationCard(
                    modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue),
                    data = fakeMedNotificationData,
                    onClick = {}
                )
            }

            itemsIndexed(fakeData) { index, item ->
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

val fakeData: List<TrendCardData> = listOf(
    TrendCardData(
        title = "Weight",
        subtitle = "Last week",
    ),
    TrendCardData(
        title = "Sleep time",
        subtitle = "Last week",
    ),
    TrendCardData(
        title = "Steps",
        subtitle = "2 Dec",
    ),
    TrendCardData(
        title = "Heart rate",
        subtitle = "2 Dec",
    ),
)

@Composable
@Preview(showBackground = true)
@Preview("dark theme", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HomeScreenPreview() {
    HomeScreen(navigateTo = {})
}