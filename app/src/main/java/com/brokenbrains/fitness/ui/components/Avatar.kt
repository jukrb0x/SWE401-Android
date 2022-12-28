package com.brokenbrains.fitness.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
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
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.User

fun getInitials(name: String): String {
    if (name.isEmpty()) return ""
    val nameParts = name.split(" ")
    return if (nameParts.size > 1) {
        nameParts[0].first().toString() + nameParts[1].first().toString()
    } else if (nameParts.size == 1) {
        nameParts[0].first().toString()
    } else {
        ""
    }
}

/**
 * Generate a fixed avatar background color by name.
 * If name is empty, the random color will be provided.
 */
fun generateColorFromName(name: String?): Color {
    var color = Color(
        (0..255).random(),
        (0..255).random(),
        (0..255).random()
    )
    name?.firstOrNull()?.let { firstChar ->
        color = Color(
            firstChar.code * 10,
            name[name[1].code % name.length].code * 20,
            name[name[2].code % name.length].code * 30,
        ).copy(alpha = 0.5f)
    }
    return color
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
            if (name.isNullOrEmpty()) {
                Icon(
                    imageVector = FontAwesomeIcons.Regular.User,
                    contentDescription = "User avatar",
                    modifier = Modifier.size(avatarSize * 0.5f),
                    tint = Color.Gray,
                )
            } else {
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
}

@Composable
@Preview
private fun AvatarPreview() {
    Column() {
        Avatar(nameInitials = "ABCD")
        Avatar(nameInitials = "")
    }

}