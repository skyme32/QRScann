package com.skyme32.qrscann.ui.component

import android.content.Context
import android.util.Log
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.camera.getBitmap
import com.skyme32.qrscann.strategy.BarcodeDefinition
import com.skyme32.qrscann.strategy.BarcodeResolver
import com.skyme32.qrscann.ui.intent.shareIntent


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
        val barcodeResolver = BarcodeResolver()
        val barcodeDefinition = barcodeResolver.getBy(barcode?.valueType)

        Column {

            headerCarView(barcodeDefinition, context, barcode)

            Spacer(modifier = Modifier.height(4.dp))

            HelpText(barcode)

            Spacer(modifier = Modifier.height(8.dp))

            actionBottom(barcodeDefinition, context, barcode)
        }
    }
}

@Composable
private fun headerCarView(
    barcodeDefinition: BarcodeDefinition?,
    context: Context,
    barcode: Barcode?
) {
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
                painter = painterResource(barcodeDefinition!!.getImages()),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(Modifier.fillMaxWidth()) {
            // Encabezado
            Text(text = barcodeDefinition!!.getText(context), style = MaterialTheme.typography.h6)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                RowText(
                    text = barcode?.displayValue.toString(),
                    color = Color.Gray,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}

@Composable
private fun actionBottom(
    barcodeDefinition: BarcodeDefinition?,
    context: Context,
    barcode: Barcode?
) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {

        Box(
            Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        ) {

            // Botones
            Row(modifier = Modifier.align(Alignment.CenterStart)) {
                if (barcodeDefinition!!.isIntent()) {
                    TextButton(onClick = {
                        try {
                            context.startActivity(
                                barcodeDefinition.getIntent(
                                    context,
                                    barcode!!
                                )
                            )
                        } catch (e: Exception) {
                            Log.i("Error", e.printStackTrace().toString())
                        }

                    }) {
                        Text(text = barcodeDefinition.getTextButton(context))
                    }
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

@Composable
private fun HelpText(barcode: Barcode?) {

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            bitmap = getBitmap(barcode?.rawValue.toString()).asImageBitmap(),
            contentDescription = "some useful description",
        )
    }
/*
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
 */
}