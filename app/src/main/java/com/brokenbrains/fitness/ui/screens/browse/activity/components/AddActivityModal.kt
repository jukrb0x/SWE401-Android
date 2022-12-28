package com.brokenbrains.fitness.ui.screens.browse.activity.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.ui.components.BottomModalSheet
import compose.icons.feathericons.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.ui.components.*
import compose.icons.FeatherIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddActivityModal(
    onDismiss: (Boolean) -> Unit,
    visibility: Boolean,
    onActionButtonPressed: () -> Unit,
) {
    BottomModalSheet(
        title = "Share with Someone",
        subtitle = "Your friend will see your fitness activity",
        actionButton = {
            IconButton(onClick = { onActionButtonPressed() }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = FeatherIcons.Settings,
                    tint = Color.Gray,
                    contentDescription = ""
                )
            }
        },
        onDismiss = onDismiss,
        visibility = visibility
    ) {
        var friendId by rememberSaveable { mutableStateOf("") } // todo: retrieve from AppState
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            OutlinedTextField(
                value = friendId.trim(),
                onValueChange = { friendId = it.trim() },
                placeholder = { Text("Enter friend's Fitness ID") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                shape = RoundedCornerShape(13.dp),
                maxLines = 1
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // avatar
                Avatar(
                    modifier = Modifier.padding(10.dp),
                    avatarSize = 50.dp,
                    nameInitials = "KH",
                    onClick = {})
                // my code
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        "My Fitness ID",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            color = Color.Gray
                        )
                    )
                    Text(
                        "123456",
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight(500),
                            color = Color.Black
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // add button
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Start Sharing")
            }
        }

    }

}