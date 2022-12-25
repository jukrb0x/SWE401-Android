package com.brokenbrains.fitness.ui.screens.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.AppDestinations
import com.brokenbrains.fitness.UserRoutes
import com.brokenbrains.fitness.ui.components.FitnessIcon
import com.brokenbrains.fitness.ui.theme.YaleBlue3


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navigateTo: (String) -> Unit) {
    //TODO: Add background image or theme
    Box(Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
/*

            val image = painterResource(id = R.drawable.ic_login)
            Image(
                painter = image,
                contentDescription = "Login",
                modifier = Modifier
                    .padding(top = 40.dp, bottom = 10.dp)
                    .size(100.dp)
                    .border(5.dp, Color.Black, CircleShape)
            )

*/
//            Avatar(avatarSize = 100.dp, modifier = Modifier.padding(top = 40.dp))

            FitnessIcon(
                iconSize = 100.dp,
                modifier = Modifier.padding(top = 40.dp),
                backgroundColor = YaleBlue3
            )


            Text(
                text = "Fitness",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = YaleBlue3
            )

            Text("Login before exploring", color = Color(0xFFAAA5A5), fontSize = 16.sp)

            Spacer(modifier = Modifier.height(30.dp))

            //Email TextFiled
            var text_login_email by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = text_login_email,
                onValueChange = { text_login_email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(20.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                }
            )

            //Password TextField
            var text_login_password by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = text_login_password,
                onValueChange = { text_login_password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(20.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null
                    )
                },
                visualTransformation = PasswordVisualTransformation()
            )

            // Login Button
            Button(
                onClick = { navigateTo(AppDestinations.LOGIN_ROUTE) },
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
                onClick = { navigateTo(UserRoutes.Register.route) },
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(180.dp, 50.dp)
                    .clip(RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF2E9AD5))
            ) {
                Text(text = "Sign Up", fontWeight = FontWeight.Bold)
            }

        }
    }
}