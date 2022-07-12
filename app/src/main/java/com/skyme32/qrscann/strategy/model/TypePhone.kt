package com.skyme32.qrscann.strategy.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.strategy.BarcodeDefinition

object TypePhone: BarcodeDefinition {

    override fun appliedTypeBarcode(): Int {
        return Barcode.TYPE_PHONE
    }

    override fun getIntent(context: Context, barcode: Barcode): Intent {

        return Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:${barcode.phone?.number}")
        }
    }

    override fun getText(context: Context): String {
        return context.getString(R.string.is_phone)
    }

    override fun getImages(): Int {
        return R.drawable.ic_baseline_phone
    }

    override fun isIntent(): Boolean {
        return true
    }

    override fun getTextButton(context: Context): String {
        return context.getString(R.string.button_phone)
    }
}