package com.brokenbrains.fitness.ui.screens.healthplus

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

data class MedicaCardData(
    val id: String,
    val title: String,
    val type: String,
    val description: String,
    val timeSet: String,
)

object MedicalItemStyles {
    val TitleStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
    )

    val DescriptionStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Gray
    )

    val timeSetStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF3D96C0)
    )

    val typeStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Gray
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalCard() {
    //TODO()
}