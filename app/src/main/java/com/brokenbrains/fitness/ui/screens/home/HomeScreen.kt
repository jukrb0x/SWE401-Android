package com.brokenbrains.fitness.ui.screens


//import androidx.compose.material.TextField
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.ui.components.*


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(navigateTo: (route: String) -> Unit) {
    MainScreenColumn(horizontalPadding = 0.dp) {
        Box(modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue)) {
            MainScreenHeader(
                title = /*TabRoutes.Home.title*/ "Fitness",
            )}
        LazyColumn(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = MainScreenHorizontalPaddingValue)
                .fillMaxWidth(),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            item {
                Text("display quick facts about your health") // TODO
                Spacer(modifier = Modifier.height(10.dp))
            }

            itemsIndexed(fakeData) { index, item ->
                TrendCard(
                    data = item
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