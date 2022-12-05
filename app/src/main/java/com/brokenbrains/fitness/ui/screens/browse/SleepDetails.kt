package com.brokenbrains.fitness.ui.screens.browse

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.components.TrendCard
import com.brokenbrains.fitness.ui.components.TrendCardData

@Composable
fun SleepDetails(navigateTo: (route: String) -> Unit, onBack: () -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue)
    ) {
        item {
            BrowseDetailsSectionTitle(title = "DATA")
            TrendCard(
                data = TrendCardData(
                    title = "Sleep Duration",
                    subtitle = "Sep 13 - Dec 5"
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            TrendCard(
                data = TrendCardData(
                    title = "Bedtime schdule",
                    subtitle = "Sep 13 - Dec 5"
                )
            )
        }
    }
}
