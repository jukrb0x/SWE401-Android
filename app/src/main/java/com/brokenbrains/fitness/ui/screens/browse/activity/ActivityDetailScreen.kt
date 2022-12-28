@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.brokenbrains.fitness.ui.screens.browse.activity

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brokenbrains.fitness.data.model.activity.ActivityModel
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.model.activity.ActivityViewModel
import com.brokenbrains.fitness.data.model.activity.toReadableString
import com.brokenbrains.fitness.ui.screens.browse.components.BrowsePage

@Composable
fun ActivityDetailScreen(
    viewModel: ActivityViewModel,
    activityType: ActivityType,
    navigateTo: (route: String) -> Unit,
    onBack: () -> Unit
) {

//    val allActivities by viewModel.getAllActivities.collectAsStateWithLifecycle(
//        initialValue = emptyList(),
//        lifecycle = LocalLifecycleOwner.current.lifecycle
//    )

    val allActivities by viewModel.allActivities.collectAsState()

    LaunchedEffect(key1 = allActivities){
        viewModel.getAllActivities()
    }

//    var allActivities = remember { mutableStateListOf<ActivityModel>() }


//    LaunchedEffect(key1 = viewModel.allActivities) {
//        viewModel.allActivities.collect() {
//            Log.w("ActivityDetailScreen", "allActivities: $it")
//            allActivities = it.toMutableStateList()
//        }
//    }
    BrowsePage(
        title = activityType.toReadableString(),
        navigateTo = navigateTo,
        onBack = onBack
    ) {
        ActivityColumn(activityType = activityType, allActivities = allActivities)

        LazyColumn {
            item {
//                ActivityColumn(activityType = activityType, allActivities = allActivities)
            }

        }
    }
}

@Composable
private fun ActivityColumn(
    activityType: ActivityType,
    allActivities: List<ActivityModel>
) {
    LazyColumn {
        item {
            allActivities.forEach { activity ->
                activity.title?.let { Text(it) }
                Log.w("ActivityDetailScreen", "Activity: $activity")
            }
        }

    }
}