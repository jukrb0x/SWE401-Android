package com.brokenbrains.fitness.ui.screens.browse.activity

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
import com.brokenbrains.fitness.ActivityRoutes
import com.brokenbrains.fitness.data.model.activity.ActivityModel
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.model.activity.ActivityViewModel
import com.brokenbrains.fitness.data.model.activity.toReadableString
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.screens.browse.activity.components.ActivitiesDialog
import com.brokenbrains.fitness.ui.screens.browse.activity.components.DatePickerDialog
import com.brokenbrains.fitness.ui.screens.browse.activity.components.TimePickerDialog
import com.brokenbrains.fitness.ui.screens.browse.components.BrowsePage
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatterBuilder

private var selectTextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)

@Composable
fun AddActivityScreen(
    viewModel: ActivityViewModel,
    navigateTo: (route: String) -> Unit, onBack: () -> Unit
) {
    // screen states
    val title = rememberSaveable { mutableStateOf("") }
    val startDate = rememberSaveable { mutableStateOf(LocalDate.now()) }
    val startTime = rememberSaveable { mutableStateOf(LocalTime.now()) }
    val endTime = rememberSaveable { mutableStateOf(LocalTime.now().plusMinutes(30)) }
    val selectedActivity =
        rememberSaveable { mutableStateOf(ActivityType.WALKING) }
    val startDateTime = rememberSaveable { mutableStateOf(startDate.value.atTime(startTime.value)) }
    val endDateTime = rememberSaveable { mutableStateOf(startDate.value.atTime(endTime.value)) }


    val showToast = remember { mutableStateOf(false) }

    // ViewModel
    fun handleAddActivity(): Boolean {
        startDateTime.value = startDate.value.atTime(startTime.value)
        endDateTime.value = startDate.value.atTime(endTime.value)
        if (startDateTime.value.isAfter(endDateTime.value)) {
            showToast.value = true
            return false;
        }
        viewModel.addNewActivity(
            ActivityModel(
                title = if (title.value.isNotEmpty()) title.value else selectedActivity.value.toReadableString(),
                startAt = startDateTime.value.toEpochSecond(ZoneOffset.UTC),
                endAt = endDateTime.value.toEpochSecond(ZoneOffset.UTC),
                activityType = selectedActivity.value
            )
        )
        return true;
    }
    if (showToast.value) {
        Toast.makeText(
            LocalContext.current,
            "Start time must be before end time",
            Toast.LENGTH_SHORT
        ).show()
        showToast.value = false
    }



    BrowsePage(
        title = ActivityRoutes.AddActivity.title, navigateTo = navigateTo, onBack = onBack,
        onAdd = {
            // TODO handle Add viewmodel
            if (handleAddActivity()) onBack()
        }
    ) {

        val activitiesDialog = rememberMaterialDialogState()
        val startDateDialog = rememberMaterialDialogState()
        val startTimePickerDialog = rememberMaterialDialogState()
        val endTimePickerDialog = rememberMaterialDialogState()

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
            RowItem(label = "Title") {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp),
                    colors = textFieldColors,
                    value = title.value,
                    singleLine = true,
                    onValueChange = { title.value = it },
                    placeholder = {
                        Text(
                            "Some Activity",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                        )
                    },
                    textStyle = TextStyle.Default.copy(textAlign = TextAlign.End),
                )
            }
            Divider()

            RowItem(modifier = Modifier.clickable(
                onClick = { activitiesDialog.show() }
            ), label = "Activity") {
                Text(
                    text = selectedActivity.value.toReadableString(),
                    style = selectTextStyle,
                    modifier = Modifier
                        .padding(start = MainScreenHorizontalPaddingValue)
                )

            }
            ActivitiesDialog(dialogState = activitiesDialog, onActivitySelected = {
                selectedActivity.value = it
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
                label = "Start Time"
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
            RowItem(
                modifier = Modifier.clickable(onClick = {
                    endTimePickerDialog.show()
                }),
                label = "End Time"
            ) {
                Text(
                    text = endTime.value.format(
                        DateTimeFormatterBuilder().appendPattern("HH:mm").toFormatter()
                    ),
                    style = selectTextStyle,
                    modifier = Modifier
                        .padding(start = MainScreenHorizontalPaddingValue)
                )
            }


        }

        // ----------
        // Dialogs
        // ----------

        TimePickerDialog(
            dialogState = startTimePickerDialog,
            onDateSelected = { startTime.value = it },
            initialTime = startTime.value
        )

        TimePickerDialog(
            dialogState = endTimePickerDialog,
            onDateSelected = { endTime.value = it },
            initialTime = endTime.value
        )


        DatePickerDialog(
            dialogState = startDateDialog,
            onDateSelected = { startDate.value = it }
        )

    }
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