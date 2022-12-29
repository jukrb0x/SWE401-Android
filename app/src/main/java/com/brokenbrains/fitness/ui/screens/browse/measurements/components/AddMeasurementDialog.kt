package com.brokenbrains.fitness.ui.screens.browse.measurements.components

import androidx.compose.runtime.Composable
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.model.measurement.MeasurementType
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.listItems
import com.vanpra.composematerialdialogs.title

@Composable
fun AddMeasurementDialog(dialogState: MaterialDialogState, onDismiss: () -> Unit= {}, onSelected: (MeasurementType) -> Unit) {
    MaterialDialog(dialogState = dialogState, buttons = {
//                positiveButton("ok", onClick = { activitiesDialog.hide() })
    }) {
        title(text = "Select Body Measurement")
        listItems(
            list = MeasurementType.getStringList(),
        ) { index, text ->
            onSelected(MeasurementType.values()[index])
        }
    }

}
