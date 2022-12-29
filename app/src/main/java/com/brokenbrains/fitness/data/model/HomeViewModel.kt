package com.brokenbrains.fitness.data.model

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.brokenbrains.fitness.data.model.activity.ActivityViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
//    activityViewModel: ActivityViewModel
) : ViewModel() {
//    val activityViewModel by viewModels();


}