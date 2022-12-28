package com.brokenbrains.fitness.ui.screens.user

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.AppDestinations
import com.brokenbrains.fitness.data.model.user.UserModel
import com.brokenbrains.fitness.data.viewmodel.AuthViewModel
import com.brokenbrains.fitness.data.viewmodel.UserViewModel
import com.brokenbrains.fitness.network.ResultData
import com.brokenbrains.fitness.ui.components.Avatar
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    viewModel: AuthViewModel,
    navigateTo: (String) -> Unit
) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val signupFlow = viewModel.signupFlow.collectAsState()

    Box() {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /*       val image = painterResource(id = R.drawable.ic_login)
                   Image(
                       painter = image,
                       contentDescription = "Login",
                       modifier = Modifier
                           .padding(top = 40.dp, bottom = 10.dp)
                           .size(100.dp)
                           .border(5.dp, Color.Black, CircleShape)
                   )
       */
            Avatar(avatarSize = 100.dp, modifier = Modifier.padding(top = 40.dp))

            //Sign Up Text
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFAAA5A5)
            )

            Spacer(modifier = Modifier.height(25.dp))

            //First Name TextFiled
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                }
            )

            //Last Name TextFiled
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                }
            )

            //Email TextFiled
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                }
            )

            //Password TextFiled
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
                visualTransformation = PasswordVisualTransformation()
            )

            //Sign Up Button
            Button(
                onClick = {
                    viewModel.signup(name = "${firstName.trim()} ${lastName.trim()}", email = email, password = password)
//                    handleSignup()
//                    navigateTo(AppDestinations.LOGIN_ROUTE)/*TODO*/
                },
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(180.dp, 50.dp)
                    .clip(RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(40.dp)
            ) {
                Text(text = "Sign Up", fontWeight = FontWeight.Bold)
            }
        }

        // Observing the signupFlow
        signupFlow.value?.let {
            if (it is ResultData.Success) {
                navigateTo(AppDestinations.LOGIN_ROUTE)
            }
            if(it is ResultData.Failed){
                Toast.makeText(LocalContext.current, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}