package com.brokenbrains.fitness.ui.screens.browse.measurements

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
import com.brokenbrains.fitness.MeasurementsRoutes
import com.brokenbrains.fitness.data.model.activity.ActivityViewModel
import com.brokenbrains.fitness.data.model.measurement.*
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.screens.browse.activity.AddActivityScreen
import com.brokenbrains.fitness.ui.screens.browse.activity.components.DatePickerDialog
import com.brokenbrains.fitness.ui.screens.browse.activity.components.TimePickerDialog
import com.brokenbrains.fitness.ui.screens.browse.components.BrowsePage
import com.brokenbrains.fitness.ui.screens.browse.measurements.components.AddMeasurementDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatterBuilder

private var selectTextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)

@Composable
fun AddMeasurementScreen(
    viewModel: MeasurementViewModel,
    navigateTo: (route: String) -> Unit, onBack: () -> Unit
) {
    // screen states
    val selectedMeasurementType = rememberSaveable { mutableStateOf(MeasurementType.WEIGHT) }
    val inputMeasurement = rememberSaveable { mutableStateOf("") }
    val measurementValue = rememberSaveable { mutableStateOf(0f) }


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
    val startDateTime = rememberSaveable { mutableStateOf(startDate.value.atTime(startTime.value)) }

    val measurementTypeDialog = rememberMaterialDialogState()
    val startDateDialog = rememberMaterialDialogState()
    val startTimePickerDialog = rememberMaterialDialogState()
    val endTimePickerDialog = rememberMaterialDialogState()


    fun handleAddMeasurement(): Boolean {
        // convert input to float, if failed, raise up a toast
        if (startDateTime.value.isAfter(LocalDate.now().atTime(LocalTime.now()))) {
            toast.message.value = "Start date must be before current date"
            toast.isShow.value = true
            return false;
        }

        try {
            measurementValue.value = inputMeasurement.value.toFloat()
        } catch (e: Exception) {
            toast.isShow.value = true
            toast.message.value = "Invalid input"
            return false
        }

        viewModel.addNewMeasurement(
            MeasurementModel(
                startAt = startDateTime.value.toEpochSecond(ZoneOffset.UTC),
                value = measurementValue.value,
                measurementType = selectedMeasurementType.value
            )
        )
        return true;
    }


    BrowsePage(
        title = MeasurementsRoutes.AddMeasurement.title, navigateTo = navigateTo, onBack = onBack,
        onAdd = {
            if (handleAddMeasurement()) {
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
                onClick = { measurementTypeDialog.show() }
            ), label = "Body Measurement") {
                Text(
                    text = selectedMeasurementType.value.toReadableString(),
                    style = selectTextStyle,
                    modifier = Modifier
                        .padding(start = MainScreenHorizontalPaddingValue)
                )

            }
            AddMeasurementDialog(dialogState = measurementTypeDialog, onSelected = {
                selectedMeasurementType.value = it
            })
            Divider()

            RowItem(
                modifier = Modifier.clickable(onClick = { startDateDialog.show() }),
                label = "Date"
            ) {
                Text(
                    text = startDate.value.toString(),
                    style = selectTextStyle,
                    modifier = Modifier
                        .padding(start = MainScreenHorizontalPaddingValue)
                )
            }


            RowItem(
                modifier = Modifier.clickable(onClick = {
                    startTimePickerDialog.show()
                }),
                label = "Record Time"
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
            RowItem(label = "Value (${selectedMeasurementType.value.getUnitString()})") {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp),
                    colors = textFieldColors,
                    value = inputMeasurement.value,
                    singleLine = true,
                    onValueChange = { inputMeasurement.value = it },
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
    TimePickerDialog(
        dialogState = startTimePickerDialog,
        onDateSelected = { startTime.value = it },
        initialTime = startTime.value
    )

    DatePickerDialog(
        dialogState = startDateDialog,
        onDateSelected = { startDate.value = it },
    )

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
