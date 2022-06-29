package com.skyme32.qrscann.strategy.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.strategy.BarcodeDefinition


object TypeEmail : BarcodeDefinition {

    override fun appliedTypeBarcode(): Int {
        return Barcode.TYPE_EMAIL
    }

    override fun getIntent(context: Context, barcode: Barcode): Intent {
        return Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, barcode.email?.address)
            putExtra(Intent.EXTRA_SUBJECT, barcode.email?.subject)
            putExtra(Intent.EXTRA_TEXT, barcode.email?.body)
        }

    }

    override fun getText(context: Context): String {
        return context.getString(R.string.is_email)
    }

    override fun getImages(): Int {
        return R.drawable.ic_baseline_email
    }

    override fun isIntent(): Boolean {
        return true
    }


}