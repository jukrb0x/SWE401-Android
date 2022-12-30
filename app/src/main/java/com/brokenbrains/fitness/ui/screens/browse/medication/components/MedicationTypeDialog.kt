package com.brokenbrains.fitness.ui.screens.browse.medication.components

import androidx.compose.runtime.Composable
import com.brokenbrains.fitness.data.model.medication.MedicationType
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.listItems
import com.vanpra.composematerialdialogs.title


@Composable
fun MedicationTypeDialog(dialogState: MaterialDialogState, onDismiss: () -> Unit= {}, onSelected: (MedicationType) -> Unit) {
    MaterialDialog(dialogState = dialogState, buttons = {
//                positiveButton("ok", onClick = { activitiesDialog.hide() })
    }) {
        title(text = "Select Medication Type")
        listItems(
            list = MedicationType.getStringList(),
        ) { index, text ->
            onSelected(MedicationType.values()[index])
        }
    }

}
