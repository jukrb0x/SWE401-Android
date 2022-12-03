@file:OptIn(ExperimentalMaterial3Api::class)

package com.brokenbrains.fitness.ui.components

//import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brokenbrains.fitness.ui.screens.ProfileScreen
import com.brokenbrains.fitness.ui.theme.TitleBarStyle
import com.brokenbrains.fitness.ui.theme.TitleBarSubtitleStyle

// this will align the main content with the header
val MainScreenHorizontalPaddingValue: Dp = 16.dp

/**
 * The header in the main screens
 * @TODO: currently we fixed header bar, but we should make it scrollable.
 */
@Composable
fun MainScreenHeader(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String = "",
    onAvatarPressed: () -> Unit = {},
) {
    var profileVisibility by remember { mutableStateOf(false) }

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
                Avatar(nameInitials = "KH", onClick = {onAvatarPressed(); profileVisibility = true})
/*
                Box(
                    modifier
                        .align(Alignment.CenterVertically)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .size(50.dp)
                        .clickable {
                            onAvatarPressed()
                            profileVisibility = true // show dialog
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
*/
            }
        }
    }

//    AnimatedVisibility(
//        visible = profileVisibility, enter = slideInVertically() + expandVertically(
//            expandFrom = Alignment.Top
//        ) + fadeIn(initialAlpha = 0.3f),
//        exit = slideOutVertically() + shrinkVertically() + fadeOut()
//
//    ) {
        ProfileScreen(onDismiss = { profileVisibility = false }, visibility=profileVisibility)

//    }
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
