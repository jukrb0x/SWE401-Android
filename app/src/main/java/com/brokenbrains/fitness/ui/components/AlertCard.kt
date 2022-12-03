package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.R

data class AlertCardData(
    val userID: Int,
    val memo: String,
    val medicationType: String,
    val dosage: String,
    val timeSet: String,
)

object AlertCardStyles {
    val TitleStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFD6554C),
        textDecoration = TextDecoration.None
    )

    val itemStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Gray
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertCard(modifier: Modifier = Modifier, alertCardData: AlertCardData) {
    val medicineIC = painterResource(R.drawable.ic_medicine)
    val alarmIC = painterResource(R.drawable.ic_alarm)
    val dosageIC = painterResource(R.drawable.ic_dosage)
    val cancelIC = painterResource(R.drawable.ic_cancel)
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .size(300.dp),
        onClick = { /*TODO*/ },
        elevation = CardDefaults.elevatedCardElevation(8.dp),
    ) {
        Box(modifier = Modifier) {
            Image(
                painter = cancelIC, contentDescription = "cancel", modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(24.dp),
                colorFilter = ColorFilter.tint(Color(0xFF5F5E5D))
            )
            Spacer(modifier = Modifier.height(320.dp))
            Column(
                modifier = modifier
                    .padding(10.dp)
                    .align(Alignment.CenterStart),
            ) {
                Text(
                    text = alertCardData.memo,
                    style = AlertCardStyles.TitleStyle,
                    modifier = modifier
                        .padding(start = 10.dp, bottom = 10.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Spacer(modifier = Modifier.width(140.dp))
                    Image(
                        painter = alarmIC,
                        contentDescription = "Alarm Icon",
                        modifier = modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = alertCardData.timeSet,
                        style = AlertCardStyles.itemStyle,
                        modifier = modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 20.dp)
                    )
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .height(40.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Spacer(modifier = Modifier.width(140.dp))
                    Image(
                        painter = medicineIC,
                        contentDescription = "Medicine Icon",
                        modifier = modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = alertCardData.medicationType,
                        style = AlertCardStyles.itemStyle,
                        modifier = modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 20.dp)
                    )
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .height(40.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Spacer(modifier = Modifier.width(140.dp))
                    Image(
                        painter = dosageIC,
                        contentDescription = "Dosage Icon",
                        modifier = modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = alertCardData.dosage,
                        style = AlertCardStyles.itemStyle,
                        modifier = modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 20.dp)
                    )
                }
                Row(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .wrapContentSize()
                        .padding(top = 10.dp)
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = modifier
                    ) {
                        Text(text = "Skip")
                    }
                    Button(onClick = { /*TODO*/ }, modifier = modifier.padding(start = 10.dp)) {
                        Text(text = "Done")
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AlertCardData(
        userID = 1,
        memo = "Time to take medicine",
        medicationType = "bohan",
        dosage = "1 pill",
        timeSet = "11:00",
    ).let {
        AlertCard(alertCardData = it)
    }
}
