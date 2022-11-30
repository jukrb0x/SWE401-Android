package com.brokenbrains.fitness.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.TabRoutes
import com.brokenbrains.fitness.UserRoutes
import com.brokenbrains.fitness.ui.components.MainScreenColumn
import com.brokenbrains.fitness.ui.components.MainScreenHeader
import com.brokenbrains.fitness.ui.screens.sharing.PersonCard
import com.brokenbrains.fitness.ui.screens.sharing.PersonShareData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


data class Tab(
    val title: String,
    val component: @Composable () -> Unit
)

val tabs = listOf(
    Tab("Friends") { SharingWithYou() },
    Tab("Sharing With") { YouAreSharingWith() }
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SharingScreen(navigateTo: (route: String) -> Unit) {
    MainScreenColumn(horizontalPadding = 0.dp) {
        Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            MainScreenHeader(title = TabRoutes.Sharing.title, onAvatarPressed = { navigateTo(UserRoutes.Profile.route) })
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            val pagerState: PagerState = rememberPagerState(initialPage = 0)
            val coroutineScope = rememberCoroutineScope()
            TabRow(
                modifier = Modifier.fillMaxWidth(),
                selectedTabIndex = pagerState.currentPage,
            ) {
                tabs.forEachIndexed() { index, tab ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(tab.title) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalPager(count = tabs.size, state = pagerState) { page ->
                tabs[page].component()
            }
        }
    }
}

@Composable
fun SharingWithYou() {
    LazyColumn {
        itemsIndexed(personShareDataList) { index, item -> // TODO: index
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                PersonCard(personShareData = item)
            }
            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}


@Composable
fun YouAreSharingWith() {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            text = "You are sharing with no one."
        )
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .weight(1f)
        )
    }


}

@Composable
@Preview(showBackground = true)
fun SharingScreenPreview() {
    SharingScreen(navigateTo = {})
}

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
