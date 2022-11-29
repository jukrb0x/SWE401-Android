package com.brokenbrains.fitness.ui.screens


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.R
import com.brokenbrains.fitness.ui.components.TestHello
import com.brokenbrains.fitness.ui.theme.FitnessTheme


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProfileScreen() {
    val image = painterResource(id = R.drawable.ic_account)
    FitnessTheme() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp, start = 30.dp, end = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image, contentDescription = "User Account Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        BorderStroke(5.dp, Color.Blue),
                        CircleShape
                    )
            )

            //User name
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "User Name")
                Text(
                    text = "ID: xxxxxxxx", modifier = Modifier.wrapContentSize(),
                    fontSize = 13.sp,
                    color = Color.LightGray
                )
            }

            //User Info
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                //D.O.B Info
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFD8CACA))
                        .height(50.dp)
                ) {
                    Row() {
                        AssistChip(
                            onClick = { false },
                            label = { Text("Assist Chip") },
                            modifier = Modifier.padding(5.dp),
                        )

                        val textState = remember { mutableStateOf(TextFieldValue()) }
                        TextField(
                            readOnly = true,
                            value = textState.value,
                            onValueChange = { textState.value = it },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .padding(5.dp, 10.dp)
                                .weight(1f)
                                .fillMaxWidth()
                                .background(Color(0xFFD8CACA)),
                            singleLine = true,
                        )
                    }
                }
            }

            /**
            Row(
            modifier = Modifier.padding(bottom = 20.dp),
            ) {
            Surface(
            modifier = Modifier.padding(end = 10.dp),
            border = BorderStroke(2.dp, Color.Gray),
            contentColor = Color.Blue,
            shape = CircleShape,
            ) {
            Text(
            "D.O.B",
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold
            )
            }

            Card(
            modifier = Modifier
            .fillMaxWidth()
            .clickable { false },
            colors = CardDefaults.cardColors(Color.LightGray),
            //elevation = CardDefaults.cardElevation(10.dp)
            ) {
            Text("sadsadasdadas", modifier = Modifier.padding(10.dp))
            }
            }
             */

            Button(onClick = { /* Do something! */ }) { Text("Button") }




            TestHello(name = "Profile Screen")

        }
    }
}