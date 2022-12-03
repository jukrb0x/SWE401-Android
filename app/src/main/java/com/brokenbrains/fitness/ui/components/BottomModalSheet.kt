package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.ui.components.Styles.SubtitleStyle
import com.brokenbrains.fitness.ui.components.modalsheet.ExperimentalSheetApi
import com.brokenbrains.fitness.ui.components.modalsheet.ModalSheet
import com.google.accompanist.flowlayout.FlowColumn


private val TitleStyle = TextStyle(
    fontSize = 22.sp,
    fontWeight = FontWeight(600),
    fontFamily = FontFamily.Default

)

private val SubtitleStyle = TextStyle(
    fontSize = 15.sp,
    fontWeight = FontWeight(400),
    color = Color.Gray,
    fontFamily = FontFamily.Default
)

@OptIn(ExperimentalSheetApi::class)
@Composable
fun BottomModalSheet(
    title: String,
    subtitle: String,
    actionButton: @Composable () -> Unit,
    onDismiss: (Boolean) -> Unit,
    visibility: Boolean,
    cancelable: Boolean = true,
    content: @Composable () -> Unit
) {
    ModalSheet(
        visible = visibility,
        onVisibleChange = { onDismiss(it) },
        shape = RoundedCornerShape(20.dp),
        cancelable = cancelable,
        elevation = 16.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(23.dp),
        ) {
            Row() {
                FlowColumn() {
                    Text(title, style = TitleStyle)
                    Text(subtitle, style = SubtitleStyle)
                }
                Spacer(modifier = Modifier.weight(1f))
                actionButton()
            }
            content()
        }
        Box(modifier = Modifier.defaultMinSize(minHeight = 50.dp)) // for bottom padding
    }
}
