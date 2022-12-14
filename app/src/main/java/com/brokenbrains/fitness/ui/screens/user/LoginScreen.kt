package com.brokenbrains.fitness.ui.screens.user

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.AppDestinations
import com.brokenbrains.fitness.R
import com.brokenbrains.fitness.TabRoutes
import com.brokenbrains.fitness.UserRoutes
import com.brokenbrains.fitness.data.model.auth.AuthViewModel
import com.brokenbrains.fitness.network.ResultData
import com.brokenbrains.fitness.ui.components.FitnessIcon
import com.brokenbrains.fitness.ui.theme.YaleBlue3


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: AuthViewModel, navigateTo: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginFlow = viewModel.loginFlow.collectAsState()

    LaunchedEffect(key1 = viewModel.currentUser){
        if(viewModel.currentUser != null) {
            navigateTo(AppDestinations.LOGIN_ROUTE)
        }
    }

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
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = YaleBlue3
            )

            Text("Login before exploring", color = Color(0xFFAAA5A5), fontSize = 16.sp)

            Spacer(modifier = Modifier.height(30.dp))

            //Email TextFiled
            var email by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                }
            )

            //Password TextField
            var password by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )

            val coroutineScope = rememberCoroutineScope()
            val openDialog = remember { mutableStateOf(false) }
            val msg = remember { mutableStateOf("") }
            val date = remember { mutableStateOf("") }

            // coroutine login
            val handleLogin = {
//                coroutineScope.launch {
//                    val res = viewModel.login(email, password)
//                    if (res != null) {
//                        msg.value = res.firstName!!
//                        date.value = res.lastName!!
//                    } else {
//                        msg.value = "Login failed"
//                        date.value = "Please check your email and password"
//                        openDialog.value = true
//                    }
//                    openDialog.value = true
//                }
            }

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = { openDialog.value = false },
                    title = { Text(msg.value) },
                    text = { Text(date.value) },
                    confirmButton = {
                        TextButton(onClick = { openDialog.value = false }) {
                            Text("OK")
                        }
                    }
                )
            }


            // Login Button
            Button(
                onClick = {
                    viewModel.login(email.trim(), password.trim())
                },
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
        // observing
        loginFlow.value.let {
            if (it is ResultData.Success) {
                navigateTo(AppDestinations.LOGIN_ROUTE)
                viewModel.cleanUp()
            }
            if (it is ResultData.Failed) {
                Toast.makeText(LocalContext.current, it.message, Toast.LENGTH_SHORT).show()
                viewModel.cleanUp()
            }
            if (it is ResultData.Loading) {
                viewModel.cleanUp()
            }
        }
    }


}