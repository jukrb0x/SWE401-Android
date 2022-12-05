package com.brokenbrains.fitness.ui.screens.browse

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.brokenbrains.fitness.ui.components.TrendCard
import com.brokenbrains.fitness.ui.components.TrendCardData
import com.brokenbrains.fitness.ui.theme.FitnessTheme

@Composable
fun BrowseActivity(navigateTo: (route: String) -> Unit, onBack: () -> Unit) {
    BrowseDetails(title = "Activity", navigateTo = navigateTo, onBack = onBack) {
        Column() {
            TrendCard(
                data = TrendCardData(
                    title = "Weight",
                    subtitle = "Last 7 days"
                )
            )

        }
    }
}


@Composable
@Preview
private fun BrowseActivityPreview() {
    FitnessTheme {
        BrowseScreen(navigateTo = {}, onBack = {})
    }

}