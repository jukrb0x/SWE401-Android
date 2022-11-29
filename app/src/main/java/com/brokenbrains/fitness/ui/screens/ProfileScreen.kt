package com.brokenbrains.fitness.ui.screens


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.R
import com.brokenbrains.fitness.ui.theme.FitnessTheme


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProfileScreen() {
    //TODO: Add background image or theme
    //TODO: Try to figure out hardcoded padding of textfield
    val image = painterResource(id = R.drawable.ic_account)
    var text_DOB by rememberSaveable { mutableStateOf("Date of Birthday") }
    var text_sex by rememberSaveable { mutableStateOf("Enter your sex") }
    var text_bt by rememberSaveable { mutableStateOf("Enter your blood type") }
    var text_email by rememberSaveable { mutableStateOf("Enter your email") }

    FitnessTheme() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //User Account Image
            Image(
                painter = image, contentDescription = "User Account Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        BorderStroke(5.dp, Color(0xFF9055F7)),
                        CircleShape
                    )
            )

            //User name & ID
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "User Name")
                Text(
                    text = "ID: xxxxxxxx", modifier = Modifier.wrapContentSize(),
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            //User Info
            Column(
                modifier = Modifier.padding(13.dp)
            ) {
                //D.O.B Info
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFE2E0E0))
                        .height(70.dp)
                ) {
                    Row(modifier = Modifier.matchParentSize()) {
                        TextButton(
                            onClick = { false },
                            modifier = Modifier
                                .padding(10.dp, 10.dp, end = 2.dp, 10.dp)
                                .align(Alignment.CenterVertically)
                                .size(70.dp, 40.dp)
                                .clickable(onClick = { false })
                        ) {
                            Text(
                                "D.O.B",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(
                                    0xFF746099
                                ),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.15.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                        OutlinedTextField(
                            readOnly = true,
                            value = text_DOB,
                            onValueChange = { text_DOB = it },
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            modifier = Modifier
                                .padding(5.dp, 10.dp, 10.dp, 10.dp)
                        )
                    }
                }

                //Sex info
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 14.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFE2E0E0))
                        .height(70.dp)
                ) {
                    Row(modifier = Modifier.matchParentSize()) {
                        TextButton(
                            onClick = { false },
                            modifier = Modifier
                                .padding(10.dp, 10.dp, end = 2.dp, 10.dp)
                                .align(Alignment.CenterVertically)
                                .size(70.dp, 40.dp)
                                .clickable(onClick = { false })
                        ) {
                            Text(
                                "Sex",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(
                                    0xFF746099
                                ),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.15.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                        OutlinedTextField(
                            readOnly = true,
                            value = text_sex,
                            onValueChange = { text_sex = it },
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            modifier = Modifier
                                .padding(5.dp, 10.dp, 10.dp, 10.dp)
                        )
                    }
                }

                //Blood Type
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 14.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFE2E0E0))
                        .height(70.dp)
                ) {
                    Row(modifier = Modifier.matchParentSize()) {
                        TextButton(
                            onClick = { false },
                            modifier = Modifier
                                .padding(10.dp, 10.dp, end = 2.dp, 10.dp)
                                .align(Alignment.CenterVertically)
                                .size(70.dp, 40.dp)
                                .clickable(onClick = { false })
                        ) {
                            Text(
                                "Blood Type",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(
                                    0xFF746099
                                ),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.15.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                        OutlinedTextField(
                            readOnly = true,
                            value = text_bt,
                            onValueChange = { text_bt = it },
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            modifier = Modifier
                                .padding(5.dp, 10.dp, 10.dp, 10.dp)
                        )
                    }
                }

                //Email
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 14.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFE2E0E0))
                        .height(70.dp)
                ) {
                    Row(modifier = Modifier.matchParentSize()) {
                        TextButton(
                            onClick = { false },
                            modifier = Modifier
                                .padding(10.dp, 10.dp, end = 2.dp, 10.dp)
                                .align(Alignment.CenterVertically)
                                .size(70.dp, 40.dp)
                                .clickable(onClick = { false })
                        ) {
                            Text(
                                "Email",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(
                                    0xFF746099
                                ),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.15.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                        OutlinedTextField(
                            readOnly = true,
                            value = text_email,
                            onValueChange = { text_email = it },
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            modifier = Modifier
                                .padding(5.dp, 10.dp, 10.dp, 10.dp)
                        )
                    }
                }
            }

            //Edit Button
            Button(
                onClick = { /* Do something! */ },
                modifier = Modifier.padding(top = 5.dp)
            ) { Text("Edit Profile") }
        }
    }
}