package com.brokenbrains.fitness.ui.screens.sharing

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.ui.components.*


@Composable
fun AddFriendDialog(onDismiss: (Boolean) -> Unit, visibility: Boolean) {
    CustomDialog(
        modifier = Modifier.customPosition(CustomDialogPosition.BOTTOM),
        onDismissRequest = { onDismiss(!visibility) },
        visibility = visibility
    ) {
        ElevatedCard(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.elevatedCardElevation(2.dp)
        ) {
            Column() {
                Text("ok")
                Button(onClick = { onDismiss(!visibility) }) {
                }
            }

        }
    }
}

/**
 * Compose of Floating Action Button & Health Sharing - Add Friend Screen
 */
@Composable
fun AddFriendFabScreen() {
    var dialogState by rememberSaveable { mutableStateOf(FabState.Collapsed) }
    NormalFloatingActionButton(fabState = dialogState, onFabStateChange = {
        dialogState = it
    })
    AddFriendDialog(
        onDismiss = { dialogState = FabState.Collapsed },
        visibility = dialogState.isExpanded
    )
}


// Preview
@Composable
@Preview(showBackground = true)
fun AddFriendDialogPreview() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AddFriendDialog(onDismiss = {}, visibility = true)
    }
}
