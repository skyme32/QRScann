package com.skyme32.qrscann.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun RowImage(resource: Int, description: String) {
    Image(
        painter = painterResource(id = resource),
        contentDescription = description,
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colors.primary)
            .size(56.dp))
}

@Composable
fun RowText(
    text: String,
    color: Color,
    style: TextStyle,
    maxLines: Int,
    overflow: TextOverflow
) {
    SelectionContainer {
        Text(
            text = text,
            color = color,
            style = style,
            maxLines = maxLines,
            overflow = overflow
        )
    }
}



