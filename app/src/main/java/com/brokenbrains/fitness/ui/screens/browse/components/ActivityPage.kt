package com.brokenbrains.fitness.ui.screens.browse.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.ui.components.*
import com.brokenbrains.fitness.ui.theme.FitnessTheme



@Composable
fun ActivityPage(navigateTo: (route: String) -> Unit, onBack: () -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue)
    ) {
        item {
            ColumnListSectionTitle(title = "INSIGHTS")
            TrendCard(
                data = TrendCardData(
                    title = "Move",
                    subtitle = "Last 7 days"
                )
            )
            TrendCard(
                data = TrendCardData(
                    title = "Weight",
                    subtitle = "Last 7 days"
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

    }
}


@Composable
@Preview(showBackground = true)
private fun BrowseActivityPreview() {
    FitnessTheme {
        ActivityPage(navigateTo = {}, onBack = {})
    }

}