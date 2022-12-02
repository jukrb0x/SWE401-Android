package com.brokenbrains.fitness.ui.screens.healthplus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FeatherIcons
import compose.icons.FontAwesomeIcons
import compose.icons.feathericons.Star
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Github

data class DoctorCardData(
    val id: String,
    val name: String,
    val type: String,
    val description: String,
    val score: String,
)

data class HospitalCardData(
    val id: String,
    val name: String,
    val address: String,
    val description: String,
    val score: String,
)


object MedicalItemStyles {
    val nameStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
    )

    val descriptionStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Gray
    )

    val addressStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF308A9B)
    )

    val scoreStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF000000)
    )

    val typeStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF308A9B)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorCard(modifier: Modifier = Modifier, doctorCardData: DoctorCardData) {
    val doctorIC = painterResource(com.brokenbrains.fitness.R.drawable.ic_doctor)
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        //.clip(RoundedCornerShape(20.dp))
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = { /*TODO*/ },
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFEEECEC)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Spacer(modifier = Modifier.width(15.dp))
            //Lee TODO: change image to doctor's avatar
            Image(
                painter = doctorIC,
                contentDescription = "Doctor",
                colorFilter = ColorFilter.tint(Color(0xFF3D96C0)),
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.CenterVertically)
                    .padding(end = 10.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
            ) {
                Row(
                    modifier = Modifier
                ) {
                    Text(
                        text = doctorCardData.name,
                        style = MedicalItemStyles.nameStyle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Icon(
                        Icons.Filled.Star, contentDescription = "star",
                        tint = Color(0xFFD8D623),
                        modifier = Modifier
                            .size(25.dp)
                            .padding(end = 5.dp, top = 3.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = doctorCardData.score,
                        style = MedicalItemStyles.scoreStyle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(top = 3.dp)
                    )
                }
                Text(
                    text = doctorCardData.type,
                    style = MedicalItemStyles.typeStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                )
                Text(
                    text = doctorCardData.description,
                    style = MedicalItemStyles.descriptionStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalCard(modifier: Modifier = Modifier, hospitalCardData: HospitalCardData) {
    val hospitalIC = painterResource(com.brokenbrains.fitness.R.drawable.ic_hospital)
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        //.clip(RoundedCornerShape(20.dp))
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = { /*TODO*/ },
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFEEECEC)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Spacer(modifier = Modifier.width(15.dp))
            Image(
                painter = hospitalIC,
                contentDescription = "Doctor",
                colorFilter = ColorFilter.tint(Color(0xFFF44336)),
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.CenterVertically)
                    .padding(end = 10.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
            ) {
                Row(
                    modifier = Modifier
                ) {
                    Text(
                        text = hospitalCardData.name,
                        style = MedicalItemStyles.nameStyle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Icon(
                        Icons.Filled.Star, contentDescription = "star",
                        tint = Color(0xFFD8D623),
                        modifier = Modifier
                            .size(25.dp)
                            .padding(end = 5.dp, top = 3.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = hospitalCardData.score,
                        style = MedicalItemStyles.scoreStyle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(top = 3.dp)
                    )
                }
                Row() {
                    Icon(
                        Icons.Filled.LocationOn, contentDescription = "location",
                        tint = Color(0xFF3D96C0),
                        modifier = Modifier
                            .size(22.dp)
                            .padding(end = 3.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = hospitalCardData.address,
                        style = MedicalItemStyles.addressStyle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(top = 1.dp)
                    )
                }
                Text(
                    text = hospitalCardData.description,
                    style = MedicalItemStyles.descriptionStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MedicalCardPreview1() {
    DoctorCardData(
        id = "1",
        name = "Dr. John Doe",
        type = "Cardiologist",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        score = "4.5"
    ).let { DoctorCard(doctorCardData = it) }
}

@Preview(showBackground = true)
@Composable
fun MedicalCardPreview2() {
    HospitalCardData(
        id = "1",
        name = "Hospital 1",
        address = "adsgaasgagadsgasdgadsgasdga",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        score = "4.5"
    ).let { HospitalCard(hospitalCardData = it) }
}

val DoctorCardList: List<DoctorCardData> = listOf(
    DoctorCardData(
        id = "1",
        name = "Dr. Jb",
        type = "Nerologist",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        score = "4.9"
    ),
    DoctorCardData(
        id = "2",
        name = "Dr. KH",
        type = "Cardiologist",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        score = "4.8"
    ), DoctorCardData(
        id = "3",
        name = "Dr. Jenny",
        type = "Dentist",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        score = "0"
    ), DoctorCardData(
        id = "4",
        name = "Dr. John Doe",
        type = "Cardiologist",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        score = "4.5"
    )
)

val HospitalCardList: List<HospitalCardData> = listOf(
    HospitalCardData(
        id = "1",
        name = "Quanjude Hospital",
        address = "adsgaasgagadsgasdgadsgasdga",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        score = "4.9"
    ),HospitalCardData(
        id = "2",
        name = "Quanjude Hospital",
        address = "adsgaasgagadsgasdgadsgasdga",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        score = "4.8"
    ),HospitalCardData(
        id = "3",
        name = "Quanjude Hospital",
        address = "adsgaasgagadsgasdgadsgasdga",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        score = "4.7"
    ),HospitalCardData(
        id = "4",
        name = "Quanjude Hospital",
        address = "adsgaasgagadsgasdgadsgasdga",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        score = "4.6"
    )
)

