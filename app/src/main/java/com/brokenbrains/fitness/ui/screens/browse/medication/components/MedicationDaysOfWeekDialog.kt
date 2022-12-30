package com.brokenbrains.fitness.ui.screens.browse.medication.components

import androidx.compose.runtime.Composable
import com.brokenbrains.fitness.data.util.CalendarUtils
import com.vanpra.composematerialdialogs.*

@Composable
fun MedicationDaysOfWeekDialog(
    dialogState: MaterialDialogState,
    onDismiss: () -> Unit = {},
    onSelected: (List<CalendarUtils.DayOfWeek>) -> Unit
) {
    MaterialDialog(dialogState = dialogState, buttons = {
        positiveButton("OK", onClick = { onDismiss() })
        negativeButton("CANCEL", onClick = { onDismiss() })
    }) {
        title(text = "Select days of week")
        listItemsMultiChoice(
            list = CalendarUtils.DayOfWeek.getStringList()
        ) { it ->
            val a = it.map {
                CalendarUtils.DayOfWeek.values()[it]
            }
            onSelected(a.asReversed())
        }
    }

}
