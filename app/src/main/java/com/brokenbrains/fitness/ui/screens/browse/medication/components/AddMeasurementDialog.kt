package com.brokenbrains.fitness.ui.screens.browse.medication.components

import androidx.compose.runtime.Composable
import com.brokenbrains.fitness.data.util.CalendarUtils
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.listItemsSingleChoice
import com.vanpra.composematerialdialogs.title

@Composable
fun MedicationDaysOfWeekDialog(
    dialogState: MaterialDialogState,
    onDismiss: () -> Unit = {},
    onSelected: (CalendarUtils.DayOfWeek) -> Unit
) {
    MaterialDialog(dialogState = dialogState, buttons = {
        positiveButton("OK", onClick = { onDismiss() })
        negativeButton("CANCEL", onClick = { onDismiss() })
    }) {
        title(text = "Select days of week")
        listItemsSingleChoice(
            list = CalendarUtils.DayOfWeek.getStringList()
        ) {
            onSelected(CalendarUtils.DayOfWeek.values()[it])
        }
    }

}
