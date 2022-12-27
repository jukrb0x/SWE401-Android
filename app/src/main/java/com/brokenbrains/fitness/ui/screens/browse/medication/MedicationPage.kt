package com.brokenbrains.fitness.ui.screens.browse.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.BrowseRoutes
import com.brokenbrains.fitness.ui.components.ColumnListSectionTitle
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.components.TrendCard
import com.brokenbrains.fitness.ui.components.TrendCardData


@Composable
fun MedicationPage(navigateTo: (route: String) -> Unit, onBack: () -> Unit) {
    BrowsePage(
        title = BrowseRoutes.Medication.title, navigateTo = navigateTo, onBack = onBack, onAdd = {}
    ) {
        MedicationPageInternal(navigateTo = navigateTo, onBack = onBack)
    }
}

@Composable
private fun MedicationPageInternal(navigateTo: (route: String) -> Unit, onBack: () -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue).fillMaxSize()
    ) {
        item {
            ColumnListSectionTitle(title = "TRACKING")
            TrendCard(
                data = TrendCardData(
                    title = "XXXX",
                    subtitle = "Sep 13 - Dec 5"
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            TrendCard(
                data = TrendCardData(
                    title = "CCCC",
                    subtitle = "Sep 13 - Dec 5"
                )
            )
        }
    }
}
