package com.brokenbrains.fitness.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
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
import com.brokenbrains.fitness.ui.components.AppScaffold
import com.brokenbrains.fitness.ui.components.Avatar
import com.brokenbrains.fitness.ui.components.DialogTopBar
import com.brokenbrains.fitness.ui.components.FullScreenDialog
import com.brokenbrains.fitness.ui.theme.YaleBlue3


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ProfileScreen(onDismiss: () -> Unit, visibility: Boolean = false) {
    //TODO: Add background image or theme
    //TODO: Try to figure out hardcoded padding of textfield
    val image = painterResource(id = R.drawable.ic_account)
    var text_DOB by rememberSaveable { mutableStateOf("Date of Birthday") }
    var text_sex by rememberSaveable { mutableStateOf("Enter your sex") }
    var text_bt by rememberSaveable { mutableStateOf("Enter your blood type") }
    var text_email by rememberSaveable { mutableStateOf("Enter your email") }
    FullScreenDialog(
        onDismissRequest = onDismiss,
        visibility = visibility
    ) {
        AppScaffold(
            backgroundColor = MaterialTheme.colors.background, // todo: unify theme
            topBar = {
                DialogTopBar(onDismiss = onDismiss, title = "Profile", actions = {
                    IconButton(
                        onClick = { /* TODO */ },
                        enabled = /*resetEnabled*/false
                    ) {
                        val alpha = if (/*resetEnabled*/false) {
                            ContentAlpha.high
                        } else {
                            ContentAlpha.disabled
                        }
                        CompositionLocalProvider(LocalContentAlpha provides alpha) {
                            Text(
                                text = "Edit",
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }
                })
            }) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //User Account Image
                    Avatar(avatarSize = 100.dp, clickable = false)
//                    Image(
//                        painter = image, contentDescription = "User Account Image",
//                        modifier = Modifier
//                            .size(100.dp)
//                            .clip(CircleShape)
//                            .border(
//                                BorderStroke(5.dp, Color(0xFF9055F7)),
//                                CircleShape
//                            )
//                    )

                    //User name & ID
                    Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Ke Hu",
                            fontSize = 20.sp,
                        )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Badge(
                            modifier = Modifier.padding(horizontal = 2.dp),
                            containerColor = YaleBlue3,
                            contentColor = Color.White,
                        ) {
                            Text(
                                "ID",
                                modifier = Modifier.padding(horizontal = 2.dp),
                                fontSize = 13.sp,
                            )
                        }
                        Text(
                            text = "KJJ1233", modifier = Modifier.wrapContentSize(),
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }


                    //User Info
                    Column(
                        modifier = Modifier
                            .padding(13.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        //D.O.B Info
                        ProfileComponent(
                            "D.O.B",
                            text_DOB,
                            onValueChanged = { text_DOB = it },
                            modifier = Modifier
                        )

                        //Sex info
                        ProfileComponent(
                            "Sex",
                            text_sex,
                            onValueChanged = { text_sex = it },
                            modifier = Modifier
                        )

                        //Blood Type
                        ProfileComponent(
                            "Blood Type",
                            text_bt,
                            onValueChanged = { text_bt = it },
                            modifier = Modifier
                        )

                        //Email
                        ProfileComponent(
                            "Email",
                            text_email,
                            onValueChanged = { text_email = it },
                            modifier = Modifier
                        )
                    }

                    //Edit Button
                    Button(
                        onClick = { /* Do something! */ },
                        modifier = Modifier.padding(top = 5.dp)
                    ) { Text("Edit Profile") }
                }
            }

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileComponent(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.height(14.dp))
    //Blood Type
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(Color(0xFFE2E0E0))
            .height(70.dp)
    ) {
        Row(modifier = Modifier.matchParentSize()) {
            TextButton(
                onClick = { false },
                modifier = Modifier
                    .padding(10.dp, 10.dp, end = 2.dp, 10.dp)
                    .align(Alignment.CenterVertically)
                    .size(70.dp, 40.dp),
                enabled = false
            ) {
                Text(
                    label,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = YaleBlue3 /*Color(
                        0xFF746099
                    )*/,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.15.sp,
                    textDecoration = TextDecoration.None
                )
            }
            OutlinedTextField(
                readOnly = true,
                value = value,
                onValueChange = onValueChanged,
                shape = RoundedCornerShape(25.dp),
                singleLine = true,
                modifier = Modifier
                    .padding(5.dp, 10.dp, 10.dp, 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfileScreen(onDismiss = {})
}