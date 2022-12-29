package com.brokenbrains.fitness.ui.screens.browse.activity.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.icons.TablerIcons
import compose.icons.tablericons.MoodCry

@Composable
fun ActivityNotFound(message:String = "No activities found") {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = TablerIcons.MoodCry,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp)
        )
        Text(text = message)

    }
}