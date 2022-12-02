package com.brokenbrains.fitness.ui.screens.healthplus

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
import com.brokenbrains.fitness.ui.screens.*
import com.brokenbrains.fitness.ui.theme.FitnessTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

data class HealthPlusTab(
    val title: String,
    val component: @Composable () -> Unit
)

val healthPlusTab = listOf(
    HealthPlusTab("Media") { HealthInfoPage() },
    HealthPlusTab("Medical") { MedicalPage() },
    HealthPlusTab("Community") { CommunityPage() },
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HealthPlusScreen(navigateTo: (route: String) -> Unit) {
    MainScreenColumn(horizontalPadding = 0.dp) {
        Box(modifier = Modifier.padding(horizontal = MainScreenHorizontalPaddingValue)) {
            MainScreenHeader(
                title = TabRoutes.HealthPlus.title,
                onAvatarPressed = { navigateTo(UserRoutes.Profile.route) })
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            val pagerState: PagerState = rememberPagerState(initialPage = 0)
            val coroutineScope = rememberCoroutineScope()
            TabRow(
                modifier = Modifier.fillMaxWidth(),
                selectedTabIndex = pagerState.currentPage,
            ) {
                healthPlusTab.forEachIndexed() { index, tab ->
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
            HorizontalPager(count = healthPlusTab.size, state = pagerState) { page ->
                healthPlusTab[page].component()
            }
        }
    }
}

@Composable
fun HealthInfoPage() {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){
        item {
            Spacer(modifier = Modifier.height(3.dp))
        }
        itemsIndexed(HealtInfoItemList) { index, healthItem ->
            Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                HealthInfoItem(healthInfoItemData = healthItem)
            }
        }
        item {
            Spacer(modifier = Modifier.height(3.dp))
        }
    }
}

@Composable
fun MedicalPage() {
    //Lee TODO: Add Medical Page
    //Hospital List
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){

    }

    //Diveder or something else to separate two list

    //Doctor List
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){

    }
}

@Composable
fun CommunityPage() {
    Column {
        Text(
            text = "Community test"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HealthPlusScreenPreview() {
    FitnessTheme() {
        HealthPlusScreen(navigateTo = {})
    }
}