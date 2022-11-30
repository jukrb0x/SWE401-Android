@file:OptIn(ExperimentalMaterial3Api::class)

package com.brokenbrains.fitness.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
//import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.brokenbrains.fitness.ui.theme.TitleBarSubtitleStyle

/**
 * The header in the main screens
 * @TODO: currently we fixed header bar, but we should make it scrollable.
 */
@Composable
fun MainScreenHeader(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String = "",
//    onAvatarPressed: () -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.height(25.dp))

        Box(
            modifier.height(80.dp)
        ) {
            HeaderRow(modifier = modifier.align(Alignment.Center)) {
                // title of the screen
                if (subtitle.isNotEmpty()) {
                    Column(modifier = modifier) {
                        Text(
                            title,
                            modifier = modifier.height(30.dp),
                            style = TitleBarStyle,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            subtitle,
                            style = TitleBarSubtitleStyle,
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                            overflow = Ellipsis
                        )
                    }
                } else {
                    Text(
                        title,
                        modifier = modifier.align(Alignment.CenterVertically),
                        style = TitleBarStyle,
                        textAlign = TextAlign.Start
                    )
                }

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
                        .background(Color.LightGray)
                        .size(50.dp)
                        .clickable {
                            Log.d("Header", "Avatar pressed.")
                        }
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

@Composable
private fun ScrolledHeader() {
    // scrollable, smaller height, center title, no avatar, clear divider
    TODO()
}


@Preview(showBackground = true)
@Composable
private fun HeaderRowPreview() {
    MainScreenHeader(title = "Title")
}

@Preview(showBackground = true)
@Composable
private fun HeaderRowPreview2() {
    MainScreenHeader(title = "Title", subtitle = "subtitle")
}
