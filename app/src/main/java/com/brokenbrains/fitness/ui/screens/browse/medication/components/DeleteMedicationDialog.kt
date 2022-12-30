package com.brokenbrains.fitness.ui.screens.browse.medication.components

import androidx.compose.runtime.Composable
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.title

@Composable
fun DeleteMedicationDialog(
    dialogState: MaterialDialogState,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit

) {
    MaterialDialog(dialogState = dialogState, buttons = {
        positiveButton("Ok", onClick = { onConfirm() })
        negativeButton("Cancel", onClick = { onDismiss() })
    }) {
        title(text = "Confirm to delete?")
    }
}
