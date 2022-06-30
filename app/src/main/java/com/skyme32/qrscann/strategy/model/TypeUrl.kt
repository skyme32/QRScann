package com.skyme32.qrscann.strategy.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.strategy.BarcodeDefinition
import com.skyme32.qrscann.utils.validateUrl

object TypeUrl: BarcodeDefinition {

    override fun appliedTypeBarcode(): Int {
        return Barcode.TYPE_URL
    }

    override fun getIntent(context: Context, barcode: Barcode): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse(validateUrl(barcode.url?.url.toString())))
    }

    override fun getText(context: Context): String {
        return context.getString(R.string.is_url)
    }

    override fun getImages(): Int {
        return R.drawable.ic_baseline_language
    }

    override fun isIntent(): Boolean {
        return true
    }

    override fun getTextButton(context: Context): String {
        return context.getString(R.string.button_url)
    }
}