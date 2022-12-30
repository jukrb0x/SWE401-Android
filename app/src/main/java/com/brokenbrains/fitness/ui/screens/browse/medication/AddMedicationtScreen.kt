package com.brokenbrains.fitness.ui.screens.browse.medication

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.brokenbrains.fitness.MedicationRoutes
import com.brokenbrains.fitness.data.model.activity.ActivityViewModel
import com.brokenbrains.fitness.data.model.medication.*
import com.brokenbrains.fitness.data.util.CalendarUtils
import com.brokenbrains.fitness.data.util.CalendarUtils.toReadableStringShort
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.screens.browse.activity.AddActivityScreen
import com.brokenbrains.fitness.ui.screens.browse.activity.components.TimePickerDialog
import com.brokenbrains.fitness.ui.screens.browse.components.BrowsePage
import com.brokenbrains.fitness.ui.screens.browse.medication.components.MedicationDaysOfWeekDialog
import com.brokenbrains.fitness.ui.screens.browse.medication.components.MedicationTypeDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatterBuilder

private var selectTextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)

@Composable
fun AddMedicationScreen(
    viewModel: MedicationViewModel,
    navigateTo: (route: String) -> Unit, onBack: () -> Unit
) {
    // screen states
    val medicationName = rememberSaveable { mutableStateOf("") }
    val medicationType = rememberSaveable { mutableStateOf(MedicationType.PILLS) }
    val dosageInput = rememberSaveable { mutableStateOf("") }
    val dosage = rememberSaveable { mutableStateOf(0f) }


    // -------
    // Toast
    // -------
    val toast = object {
        val isShow = remember { mutableStateOf(false) }
        val message = remember { mutableStateOf("") }
    }
    if (toast.isShow.value) {
        Toast.makeText(
            LocalContext.current,
            toast.message.value,
            Toast.LENGTH_SHORT
        ).show()
        toast.isShow.value = false
    }

    // ----------
    // Dialogs State
    // ----------
    val startDate = rememberSaveable { mutableStateOf(LocalDate.now()) }
    val startTime = rememberSaveable { mutableStateOf(LocalTime.now()) }
    // we only care about the time: hour and minute
    val startDateTime = rememberSaveable { mutableStateOf(startDate.value.atTime(startTime.value)) }

    val DaysOfWeekDialog = rememberMaterialDialogState()
    val medicationTypeDialog = rememberMaterialDialogState()
    val startDateDialog = rememberMaterialDialogState()
    val startTimePickerDialog = rememberMaterialDialogState()
    val selectedDaysOfWeek = rememberSaveable { mutableStateOf(listOf<CalendarUtils.DayOfWeek>()) }


    fun handleAddMedication(): Boolean {
        // convert input to float, if failed, raise up a toast
//        if(dosage.value.toFloatOrNull() == null) {
//            toast.isShow.value = true
//            toast.message.value = "Invalid dosage"
//            return false
//        }
        if (medicationName.value.isEmpty()) {
            toast.message.value = "Medication name is required"
            toast.isShow.value = true
            return false
        }
        if (selectedDaysOfWeek.value.isEmpty()) {
            toast.message.value = "Days of week is required"
            toast.isShow.value = true
            return false
        }
        try {
            dosage.value = dosageInput.value.toFloat()
        } catch (e: Exception) {
            toast.message.value = "Invalid dosage"
            toast.isShow.value = true
            return false
        }
        if (dosage.value <= 0) {
            toast.message.value = "Dosage is required"
            toast.isShow.value = true
            return false
        }


        viewModel.addNewMedication(
            MedicationModel(
                title = medicationName.value.trim(),
                medicationType = medicationType.value,
                dose = dosage.value.toString(),
                daysOfWeek = selectedDaysOfWeek.value.toString(),
                startAt = startDateTime.value.toEpochSecond(ZoneOffset.UTC)
            )
        )
        return true;
    }


    BrowsePage(
        title = MedicationRoutes.AddMedication.title, navigateTo = navigateTo, onBack = onBack,
        onAdd = {
            if (handleAddMedication()) {
                onBack()
            }
        }
    ) {

        val textFieldColors = TextFieldDefaults.textFieldColors(
//            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            RowItem(modifier = Modifier.clickable(
                onClick = { medicationTypeDialog.show() }
            ), label = "Medication Name") {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp),
                    colors = textFieldColors,
                    value = medicationName.value,
                    singleLine = true,
                    onValueChange = { medicationName.value = it },
                    placeholder = {
                        Text(
                            "Enter the value",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                        )
                    },
                    textStyle = TextStyle.Default.copy(textAlign = TextAlign.End),
                )

            }
            Divider()

            RowItem(
                modifier = Modifier.clickable(onClick = { DaysOfWeekDialog.show() }),
                label = "Days of Week"
            ) {
                Text(
                    text = selectedDaysOfWeek.value.map { it.toReadableStringShort() }
                        .joinToString(", "),
                    style = selectTextStyle,
                    modifier = Modifier
                        .padding(start = MainScreenHorizontalPaddingValue)
                )
            }


            RowItem(
                modifier = Modifier.clickable(onClick = {
                    startTimePickerDialog.show()
                }),
                label = "Time of Day"
            ) {
                Text(
                    text = startTime.value.format(
                        DateTimeFormatterBuilder().appendPattern("HH:mm").toFormatter()
                    ),
                    style = selectTextStyle,
                    modifier = Modifier
                        .padding(start = MainScreenHorizontalPaddingValue)
                )
            }
            Divider()
            RowItem(
                modifier = Modifier.clickable(onClick = { medicationTypeDialog.show() }),
                label = "Medication Type"
            ) {
                Text(
                    text = medicationType.value.toReadableString(),
                    style = selectTextStyle,
                    modifier = Modifier
                        .padding(start = MainScreenHorizontalPaddingValue)
                )
            }


            RowItem(label = "Dosage (${medicationType.value.getUnitString()})") {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp),
                    colors = textFieldColors,
                    value = dosageInput.value,
                    singleLine = true,
                    onValueChange = { dosageInput.value = it },
                    placeholder = {
                        Text(
                            "Enter value",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                        )
                    },
                    textStyle = TextStyle.Default.copy(textAlign = TextAlign.End),
                )

            }

        }
    }



    MedicationDaysOfWeekDialog(
        dialogState = DaysOfWeekDialog,
        onSelected = { selectedDaysOfWeek.value = it })

    MedicationTypeDialog(
        dialogState = medicationTypeDialog,
        onSelected = { medicationType.value = it })

    TimePickerDialog(
        dialogState = startTimePickerDialog,
        onDateSelected = { startTime.value = it },
        initialTime = startTime.value
    )
//
//    DatePickerDialog(
//        dialogState = startDateDialog,
//        onDateSelected = { startDate.value = it },
//    )

}

@Composable
private fun RowItem(modifier: Modifier = Modifier, label: String, content: @Composable () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = MainScreenHorizontalPaddingValue),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp))
        content()
    }
}

@Preview
@Composable
private fun AddActivityScreenPreview() {
    AddActivityScreen(viewModel = hiltViewModel<ActivityViewModel>(), navigateTo = {}, onBack = {})
}
