package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brokenbrains.fitness.ui.theme.TitleBarStyle


/**
 * The header in the main screens
 */
@Composable
fun MainScreenHeader(title: String, modifier: Modifier = Modifier) {
    Column {
        Spacer(modifier = Modifier.height(25.dp))

        Box(
            modifier.height(80.dp)
        ) {
            HeaderRow(modifier = modifier.align(Alignment.Center)) {
                // title of the screen
                Text(
                    title,
                    modifier.align(Alignment.CenterVertically),
                    style = TitleBarStyle,
                    textAlign = TextAlign.Start
                )

                Spacer(
                    modifier = modifier
                        .height(8.dp)
                        .width(8.dp)
                        .weight(1f) // fill the space
                )

                // avatar
                Box(
                    modifier
                        .align(Alignment.CenterVertically)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .size(50.dp)
                ) {
                    Text(
                        "KH",
                        overflow = Ellipsis,
                        modifier = modifier
                            .align(Alignment.Center),
                        style = TextStyle(fontSize = 20.sp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun HeaderRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Row(modifier = modifier.windowInsetsPadding(insets = WindowInsets.systemBars)) {
        content()
    }
}


@Preview(showBackground = true)
@Composable
private fun HeaderRowPreview() {
    MainScreenHeader("Title")
}
