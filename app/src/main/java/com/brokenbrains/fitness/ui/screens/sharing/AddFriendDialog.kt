package com.brokenbrains.fitness.ui.screens.sharing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.ui.components.CustomDialog
import com.brokenbrains.fitness.ui.components.CustomDialogPosition
import com.brokenbrains.fitness.ui.components.NormalFloatingActionButton
import com.brokenbrains.fitness.ui.components.customPosition


@Composable
fun AddFriendDialog(onDismiss: () -> Unit, visibility: Boolean) {
    CustomDialog(
        modifier = Modifier.customPosition(CustomDialogPosition.BOTTOM),
        onDismissRequest = { onDismiss() },
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
                Button(onClick = { onDismiss() }) {
                }
            }

        }
    }
}

/**
 * Compose of Floating Action Button and Health Sharing - Add Friend Screen
 */
@Composable
fun AddFriendFabScreen() {
    var addFriendDialogVisibility by rememberSaveable { mutableStateOf(false) }
    NormalFloatingActionButton(onFabClicked = {
        addFriendDialogVisibility = true
    })
    AddFriendDialog(
        onDismiss = { addFriendDialogVisibility = false },
        visibility = addFriendDialogVisibility
    )
}


// Preview
@Composable
@Preview
fun AddFriendDialogPreview() {
    AddFriendDialog(onDismiss = {}, visibility = true)
}
