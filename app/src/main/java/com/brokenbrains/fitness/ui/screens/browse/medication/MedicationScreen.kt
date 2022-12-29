@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.brokenbrains.fitness.ui.screens.browse.measurements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brokenbrains.fitness.data.model.medication.MedicationModel
import com.brokenbrains.fitness.data.model.medication.MedicationType
import com.brokenbrains.fitness.data.model.medication.MedicationViewModel
import com.brokenbrains.fitness.data.model.medication.toReadableString
import com.brokenbrains.fitness.data.util.CalendarUtils
import com.brokenbrains.fitness.data.util.CalendarUtils.toReadableString
import com.brokenbrains.fitness.ui.components.MainScreenHorizontalPaddingValue
import com.brokenbrains.fitness.ui.screens.browse.activity.components.ActivityNotFound
import com.brokenbrains.fitness.ui.screens.browse.components.BrowsePage
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Pills
import compose.icons.fontawesomeicons.solid.Syringe
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun MedicationScreen(
    viewModel: MedicationViewModel,
    navigateTo: (route: String) -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val allMedications = uiState.allMedications
    BrowsePage(
        title = "Medication",
        navigateTo = navigateTo,
        onBack = onBack,
//        onAdd = { navigateTo() }
    ) {
        InsideColumn(viewModel = viewModel, allMedications = allMedications)
        Button(onClick = {
            viewModel.addNewMedication(
                MedicationModel(
                    title = "Bohan",
                    medicationType = MedicationType.PILLS,
                    dose = "1",
                    daysOfWeek = "[MONDAY,SUNDAY]",
                    startAt = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
                )
            )
        }) {
            Text(text = "Add")
        }
    }

}

@Composable
private fun InsideColumn(
    viewModel: MedicationViewModel,
    allMedications: List<MedicationModel>
) {
    if (allMedications.isEmpty()) {
        ActivityNotFound(message = "No medication records found")
    }
    val formatter = DateTimeFormatter.ofPattern("hh:mm")
    LazyColumn {
        items(items = allMedications) { item ->

            val tod = CalendarUtils.getDateTimeFromLong(item.startAt!!)!!.format(formatter)
            val dowList = item.daysOfWeek?.let { CalendarUtils.getListFromDayOfWeekString(it) }
            val shorts = dowList?.joinToString {
                it.toReadableString()
            }

            val icon = when (item.medicationType) {
                MedicationType.PILLS -> FontAwesomeIcons.Solid.Pills
                MedicationType.INJECTION -> FontAwesomeIcons.Solid.Syringe
                MedicationType.OTHER -> FontAwesomeIcons.Solid.Pills
                else -> FontAwesomeIcons.Solid.Pills
            }
            Box(modifier = Modifier.clickable(onClick = {/*TODO*/})) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MainScreenHorizontalPaddingValue),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(24.dp)
                        )
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(text = item.title!!, fontWeight = FontWeight.Bold)
                            Text(
                                text = "${item.dose} ${item.medicationType!!.toReadableString()}",
                            )

                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Text(text = "${tod}", fontWeight = FontWeight.Bold)
                        Text(text = "on ${shorts}")
                    }
                }
            }
            Divider()
        }


    }
}
