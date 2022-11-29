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
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.R


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    //TODO: Add background image or theme
    Box() {
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

            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFAAA5A5)
            )

            //Email TextFiled
            var text_login_email by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = text_login_email,
                onValueChange = { text_login_email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(20.dp)
            )

            //Password TextField
            var text_login_password by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = text_login_password,
                onValueChange = { text_login_password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(20.dp)
            )

            //Login Button
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(180.dp, 50.dp)
                    .clip(RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(40.dp)
            ) {
                Text(text = "Login", fontWeight = FontWeight.Bold)
            }

            //Sign Up Button
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(180.dp, 50.dp)
                    .clip(RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF2E9AD5))
            ) {
                Text(text = "Sign Up", fontWeight = FontWeight.Bold)
            }

            Text(
                text = "Don't have an account yet?",
                modifier = Modifier.padding(top = 10.dp),
                color = Color.Gray,
                fontSize = 12.sp
            )

            Text(
                text = "Sign up now!",
                modifier = Modifier.padding(top = 5.dp),
                color = Color.Gray,
                fontSize = 12.sp
            )

        }
    }
}