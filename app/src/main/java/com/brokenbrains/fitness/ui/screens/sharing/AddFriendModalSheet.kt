package com.brokenbrains.fitness.ui.screens.sharing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.ui.components.*
import compose.icons.FeatherIcons
import compose.icons.feathericons.Settings
import compose.icons.feathericons.X

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareWithSomeoneModal(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharingSettingsModal(
    onDismiss: (Boolean) -> Unit,
    visibility: Boolean,
    onActionButtonPressed: () -> Unit
) {
    BottomModalSheet(
        title = "Sharing Settings",
        subtitle = "Manage your sharing settings",
        actionButton = {
            IconButton(onClick = { onActionButtonPressed() }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = FeatherIcons.X,
                    tint = Color.Gray,
                    contentDescription = ""
                )
            }
        },
        onDismiss = onDismiss,
        visibility = visibility
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("You are sharing your fitness activity with 2 friends")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = true, onCheckedChange = {})
            // TODO

//            Spacer(modifier = Modifier.weight(1f))

        }

    }

}


/**
 * Compose of Floating Action Button & Health Sharing - Add Friend Screen
 */
@Composable
fun AddFriendFabScreen(navigateTo: (route: String) -> Unit) {
    var shareWithSomeoneVis by rememberSaveable { mutableStateOf(FabState.Collapsed) }
    var sharingSettingsVis by rememberSaveable { mutableStateOf(FabState.Collapsed) }
    NormalFloatingActionButton(fabState = shareWithSomeoneVis, onFabStateChange = {
        shareWithSomeoneVis = it
    })

    fun toggleSharingSettings() {
        sharingSettingsVis =
            if (sharingSettingsVis == FabState.Collapsed) FabState.Expanded else FabState.Collapsed
        shareWithSomeoneVis =
            if (shareWithSomeoneVis == FabState.Expanded) FabState.Collapsed else shareWithSomeoneVis
    }

    ShareWithSomeoneModal(
        onDismiss = { shareWithSomeoneVis = FabState.Collapsed },
        visibility = shareWithSomeoneVis.isExpanded,
        onActionButtonPressed = { toggleSharingSettings() }
    )

    SharingSettingsModal(
        onDismiss = { sharingSettingsVis = FabState.Collapsed },
        visibility = sharingSettingsVis.isExpanded,
        onActionButtonPressed = { toggleSharingSettings() }
    )
}

