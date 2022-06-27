package com.skyme32.qrscann.strategy.model

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.strategy.BarcodeDefinition


object TypeText: BarcodeDefinition {

    override fun appliedTypeBarcode(): Int {
        return Barcode.TYPE_TEXT
    }

    override fun getIntent(context: Context, barcode: Barcode): Intent {

        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("simple text", barcode.displayValue)
        clipboard.setPrimaryClip(clip)

        return Intent(context, null)
    }

    override fun getText(context: Context): String {
        return context.getString(R.string.is_text)
    }

    override fun getImages(): Int {
        return R.drawable.ic_baseline_spellcheck
    }

    override fun isIntent(): Boolean {
        return true
    }


}