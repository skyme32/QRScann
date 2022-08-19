package com.skyme32.qrscann.ui.intent

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import com.skyme32.qrscann.R

fun copyIntent(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip: ClipData = ClipData.newPlainText("simple text", text)
    clipboard.setPrimaryClip(clip)

    Toast.makeText(context, context.getString(R.string.text_copied), Toast.LENGTH_SHORT).show()
}