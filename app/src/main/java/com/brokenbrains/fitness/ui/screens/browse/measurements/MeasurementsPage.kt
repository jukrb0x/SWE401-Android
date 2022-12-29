package com.brokenbrains.fitness.ui.screens.browse.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brokenbrains.fitness.BrowseRoutes
import com.brokenbrains.fitness.data.model.measurement.MeasurementModel
import com.brokenbrains.fitness.data.model.measurement.MeasurementType
import com.brokenbrains.fitness.data.model.measurement.MeasurementViewModel
import com.brokenbrains.fitness.ui.components.ColumnListSectionTitle
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.components.TrendCard
import com.brokenbrains.fitness.ui.components.TrendCardData

@Composable
fun MeasurementsPage(navigateTo: (route: String) -> Unit, onBack: () -> Unit) {
    BrowsePage(
        title = BrowseRoutes.Measurements.title,
        navigateTo = navigateTo,
        onBack = onBack,
        onAdd = { /*TODO*/ }) {
        MeasurementsPageInternal(navigateTo = navigateTo, onBack = onBack)
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun MeasurementsPageInternal(navigateTo: (route: String) -> Unit, onBack: () -> Unit) {
    val viewModel = hiltViewModel<MeasurementViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle();

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = MainScreenHorizontalPaddingValue)
            .fillMaxSize()
    ) {
        item {
            ColumnListSectionTitle(title = "DATA")
            TrendCard(
                data = TrendCardData(
                    title = "Weight",
                    subtitle = "Last 7 records",
                    graphVal = state.MeasurementColumnarDataByType[MeasurementType.WEIGHT],
                    todayValue = state.MeasurementTodayByType[MeasurementType.WEIGHT]?.value,
                    todayUnit = "kg",
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            TrendCard(
                data = TrendCardData(
                    title = "Height",
                    subtitle = "Last 7 records",
                    graphVal = state.MeasurementColumnarDataByType[MeasurementType.HEIGHT],
                    todayValue = state.MeasurementTodayByType[MeasurementType.HEIGHT]?.value,
                    todayUnit = "cm",
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))

        }
        item{
            Button(onClick = {
                viewModel.addNewMeasurement(
                    MeasurementModel(
                        value = 1.0f,
                        startAt = System.currentTimeMillis(),
                        measurementType = MeasurementType.WEIGHT
                    )
                )

            }) {
                Text("TEST")
            }
            Divider()
        }

    }
}
