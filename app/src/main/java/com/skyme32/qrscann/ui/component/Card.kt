package com.skyme32.qrscann.ui.component

import android.content.Context
import android.view.ContextThemeWrapper
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.ui.intent.shareIntent
import com.skyme32.qrscann.ui.intent.webViewIntent
import com.skyme32.qrscann.utils.typeBarcode
import com.skyme32.qrscann.utils.validateUrl


@Composable
fun ScanCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
    border: BorderStroke? = null,
    background: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(background),
    shape: Shape = MaterialTheme.shapes.medium,
    barcode: Barcode? = null,
    context: Context
) {
    Card(
        backgroundColor = background,
        contentColor = contentColor,
        shape = shape,
        elevation = elevation,
        border = border,
        modifier = modifier
    ) {
        val (isUrl, urlText, imageId) = typeBarcode(barcode)

        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Miniatura
                Box(
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = CircleShape)
                        .size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(imageId),
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))

                Column(Modifier.fillMaxWidth()) {
                    // Encabezado
                    Text(text = urlText, style = MaterialTheme.typography.h6)
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = barcode?.displayValue.toString(),
                            style = MaterialTheme.typography.subtitle1,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(4.dp))
            HelpText(barcode)
            Spacer(modifier = Modifier.height(8.dp))

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {

                Box(
                    Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth()
                ) {

                    // Botones
                    Row(modifier = Modifier.align(Alignment.CenterStart)) {

                        TextButton(onClick = {
                            if (isUrl) {
                                webViewIntent(context, validateUrl(barcode?.url?.url.toString()))
                            }
                        }) {
                            Text(text = "SHOW")
                        }
                    }

                    // Iconos
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {

                        IconButton(onClick = {
                            barcode?.displayValue?.let {
                                shareIntent(context = context, extraText = it)
                            }
                        }) {
                            Icon(Icons.Default.Share, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HelpText(barcode: Barcode?) {
    Row(
        Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp)
    ) {

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            SelectionContainer(modifier = Modifier.heightIn(0.dp, 200.dp)) {
                Text(
                    text = barcode?.rawValue.toString(),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }
        }
    }
}