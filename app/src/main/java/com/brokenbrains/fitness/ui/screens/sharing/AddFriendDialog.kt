package com.brokenbrains.fitness.ui.screens.sharing

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.brokenbrains.fitness.ui.components.AppScaffold
import com.brokenbrains.fitness.ui.components.DialogTopBar
import com.brokenbrains.fitness.ui.components.FullScreenDialog
import com.brokenbrains.fitness.ui.components.NormalFloatingActionButton

@Composable
fun AddFriendDialog(onDismiss: () -> Unit, visibility: Boolean) {
    FullScreenDialog(onDismissRequest = { onDismiss() }, visibility = visibility) {
        AppScaffold(
            backgroundColor = MaterialTheme.colors.background,
            topBar = {
                DialogTopBar(onDismiss = {onDismiss()}, title = "Health Sharing")
            },
        ) {

        }





    }
}


@Composable
@Preview
fun AddFriendDialogPreview() {
    AddFriendDialog(onDismiss = {}, visibility = true)
}

@Composable
fun AddFriendFabScreen(){
    var addFriendDialogVisibility by rememberSaveable { mutableStateOf(false) }
    NormalFloatingActionButton(onFabClicked = {
        addFriendDialogVisibility = true
    })
    AddFriendDialog(
        onDismiss = { addFriendDialogVisibility = false },
        visibility = addFriendDialogVisibility
    )

}