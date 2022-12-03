package com.brokenbrains.fitness.ui.screens

import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.R


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RegisterScreen() {
    //TODO: Add background image or theme
    Box(){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val image = painterResource(id = R.drawable.ic_login)
            Image(
                painter = image,
                contentDescription = "Login",
                modifier = Modifier
                    .padding(top = 40.dp, bottom = 10.dp)
                    .size(100.dp)
                    .border(5.dp, Color.Black, CircleShape)
            )

            //Sign Up Text
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFAAA5A5)
            )

            //First Name TextFiled
            var text_firstname by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = text_firstname,
                onValueChange = { text_firstname = it },
                label = { Text("First Name") },
                modifier = Modifier
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(20.dp)
            )

            //Last Name TextFiled
            var text_secondname by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = text_secondname,
                onValueChange = { text_secondname = it },
                label = { Text("First Name") },
                modifier = Modifier
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(20.dp)
            )

            //Email TextFiled
            var text_email by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = text_email,
                onValueChange = { text_email = it },
                label = { Text("First Name") },
                modifier = Modifier
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(20.dp)
            )

            //Password TextFiled
            var text_password by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = text_password,
                onValueChange = { text_password = it },
                label = { Text("First Name") },
                modifier = Modifier
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(20.dp)
            )

            //Sign Up Button
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(180.dp, 50.dp)
                    .clip(RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(40.dp)
            ) {
                Text(text = "Sign Up", fontWeight = FontWeight.Bold)
            }
        }
    }
}