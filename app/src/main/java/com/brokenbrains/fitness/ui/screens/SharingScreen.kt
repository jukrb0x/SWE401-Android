package com.brokenbrains.fitness.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.TabRoutes
import com.brokenbrains.fitness.ui.components.MainScreenColumn
import com.brokenbrains.fitness.ui.components.MainScreenHeader
import com.brokenbrains.fitness.ui.screens.sharing.PersonShareData
import com.brokenbrains.fitness.ui.screens.sharing.PersonCard

// person sharing with you list
val personShareDataList: List<PersonShareData> = listOf(
    PersonShareData(
        id = "1",
        name = "Sal Amnoodles",
        avatar = "",
        latestUpdate = "No updates",
        updateTime = "08:20"
    ),
    PersonShareData(
        id = "2",
        name = "Ke Hu",
        avatar = "",
        latestUpdate = "i eat a lot of food",
        updateTime = "28 Nov"
    ),
    PersonShareData(
        id = "1",
        name = "Hai Dilao",
        avatar = "",
        alert = "1 Alert",
        latestUpdate = "No updates",
        updateTime = "12:00"
    ),
    PersonShareData(
        id = "1",
        name = "Donal Trump",
        avatar = "",
        latestUpdate = "No updates",
        updateTime = "8 Aug"
    ),
    PersonShareData(
        id = "1",
        name = "Holy Moly",
        avatar = "",
        latestUpdate = "No updates",
        updateTime = "01:12"
    ),
    PersonShareData(
        id = "1",
        name = "Jabriel",
        avatar = "",
        latestUpdate = "No updates",
        updateTime = "19:00"
    ),
    PersonShareData(
        id = "1",
        name = "Denver Lee",
        avatar = "",
        latestUpdate = "No updates",
        updateTime = "23 Oct"
    ),
)

@Composable
@Preview(showBackground = true)
fun SharingScreen() {
    MainScreenColumn {
        MainScreenHeader(title = TabRoutes.Sharing.title)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(personShareDataList) { index, item -> // TODO: index
                PersonCard(personShareData = item)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

