package com.skyme32.qrscann.ui.intent

import android.content.Context
import android.content.Intent
import android.net.Uri


fun webViewIntent(context: Context, uri: String) {
    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    context.startActivity(webIntent)
}