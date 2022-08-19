package com.skyme32.qrscann.strategy.model

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.strategy.BarcodeDefinition
import com.skyme32.qrscann.ui.intent.copyIntent


object TypeText: BarcodeDefinition {

    override fun appliedTypeBarcode(): Int {
        return Barcode.TYPE_TEXT
    }

    override fun getIntent(context: Context, barcode: Barcode): Intent {

        copyIntent(context = context, text = barcode.displayValue!!)

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

    override fun getTextButton(context: Context): String {
        return context.getString(R.string.button_text)
    }
}