package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun getInitials(name: String): String {
    val nameParts = name.split(" ")
    return if (nameParts.size > 1) {
        nameParts[0].first().toString() + nameParts[1].first().toString()
    } else {
        nameParts[0].first().toString()
    }
}

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    avatarSize: Dp = 50.dp,
    nameInitials: String = "", // TODO: pass name and handle the initials inside the function
    backgroundColor: Color = Color.LightGray,
//    image: @Composable (() -> Unit)? = null, TODO
    shape: Shape = CircleShape,
    onClick: () -> Unit = {},
    clickable: Boolean = true,
) {
    Surface(
        modifier = modifier
            .size(avatarSize),
        shape = shape,
        color = backgroundColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = { onClick() }, enabled = clickable),

            contentAlignment = Alignment.Center
        ) {
            var name: String = nameInitials;
            if (nameInitials.length >= 2) {
                name = nameInitials.substring(0, 2).uppercase()
            } else if (nameInitials.length == 1) {
                name = nameInitials.substring(0, 1).uppercase()
            }
            Text(
                text = name,
                fontSize = (0.38f * avatarSize.value).sp,
                overflow = TextOverflow.Clip,
                maxLines = 1,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight(600),
                )
            )
        }
    }
}

@Composable
@Preview
private fun AvatarPreview() {
    Avatar(nameInitials = "ABCD")
}