package com.brokenbrains.fitness.ui.screens.browse

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.components.TrendCard
import com.brokenbrains.fitness.ui.components.TrendCardData
import com.brokenbrains.fitness.ui.theme.FitnessTheme

var DetailsSectionTitle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)

@Composable
fun BrowseDetailsSectionTitle(title: String) {
    Text(
        text = title,
        style = DetailsSectionTitle,
        modifier = Modifier.padding(5.dp)
    )
}

@Composable
fun ActivityDetails(navigateTo: (route: String) -> Unit, onBack: () -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue)
    ) {
        item {
            BrowseDetailsSectionTitle(title = "INSIGHTS")
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
        ActivityDetails(navigateTo = {}, onBack = {})
    }

}