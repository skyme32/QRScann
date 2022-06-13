package com.skyme32.qrscann.ui.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun SimpleAlertDialog(url: String) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = {})
            { Text(text = "OK") }
        },
        title = { Text(text = "Error") },
        text = { Text(text = "Cannot open web page $url") }
    )
}