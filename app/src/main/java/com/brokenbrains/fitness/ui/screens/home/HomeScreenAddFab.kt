package com.brokenbrains.fitness.ui.screens.home

import androidx.compose.runtime.*
import com.brokenbrains.fitness.ActivityRoutes
import com.brokenbrains.fitness.MeasurementsRoutes
import com.brokenbrains.fitness.MedicationRoutes
import com.brokenbrains.fitness.ui.components.FabState
import com.brokenbrains.fitness.ui.components.MultiFloatingActionButton
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.listItems
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title

data class FabNavigation(val title: String, val route: String)

val fabNavs = listOf<FabNavigation>(
    FabNavigation("Activity", ActivityRoutes.AddActivity.route),
    FabNavigation("Measurement", MeasurementsRoutes.AddMeasurement.route),
    FabNavigation("Medication", MedicationRoutes.AddMedication.route),
)

@Composable
fun HomeScreenAddFab(navigateTo: (String) -> Unit) {
    var fabState by remember {
        mutableStateOf(FabState.Collapsed)
    }

    val dialogState = rememberMaterialDialogState()
    MultiFloatingActionButton(
        fabState = fabState,
        onFabStateChange = {
            fabState = it
            if (it == FabState.Expanded) {
                dialogState.show()
            }
        })

    MaterialDialog(
        dialogState = dialogState,
        onCloseRequest = {
            fabState = FabState.Collapsed
            it.hide()
        },
    ) {
        title("Add new")
        listItems(fabNavs.map { it.title }, onClick = { index, item ->
            fabState = FabState.Collapsed
            dialogState.hide()
            navigateTo(fabNavs[index].route)
        })
    }
}
