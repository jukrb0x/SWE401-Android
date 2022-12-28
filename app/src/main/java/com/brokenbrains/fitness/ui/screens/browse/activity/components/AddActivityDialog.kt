package com.brokenbrains.fitness.ui.screens.browse.activity.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.model.activity.toReadableString
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerColors
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.listItems
import com.vanpra.composematerialdialogs.title
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun ActivitiesDialog(dialogState: MaterialDialogState, onDismiss: () -> Unit= {}, onActivitySelected: (ActivityType) -> Unit) {
    MaterialDialog(dialogState = dialogState, buttons = {
//                positiveButton("ok", onClick = { activitiesDialog.hide() })
    }) {
        title(text = "Select Activity Type")
        listItems(
            list = ActivityType.getStringList(),
        ) { index, text ->
            onActivitySelected(ActivityType.values()[index])
//            selectedActivity.value = ActivityType.values()[index].toReadableString()
//                    dialogState.hide()
        }
    }

}

@Composable
fun DatePickerDialog(dialogState: MaterialDialogState, onDismiss: () -> Unit = {}, onDateSelected:(LocalDate) -> Unit) {
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker(initialDate = LocalDate.now()) { date ->
            onDateSelected(date)
        }
    }
}

@Composable
fun TimePickerDialog(dialogState: MaterialDialogState, onDismiss: () -> Unit = {}, onDateSelected:(LocalTime) -> Unit, initialTime: LocalTime = LocalTime.now()) {
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        timepicker(initialTime = initialTime
        ){ date ->
            onDateSelected(date)
        }
    }
}
