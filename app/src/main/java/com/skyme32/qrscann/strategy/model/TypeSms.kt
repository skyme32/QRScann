package com.skyme32.qrscann.strategy.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.strategy.BarcodeDefinition

object TypeSms: BarcodeDefinition {

    override fun appliedTypeBarcode(): Int {
        return Barcode.TYPE_SMS
    }

    override fun getIntent(context: Context, barcode: Barcode): Intent {

        return Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:${barcode.sms?.phoneNumber}")
            putExtra("sms_body", barcode.sms?.message)
        }
    }

    override fun getText(context: Context): String {
        return context.getString(R.string.is_sms)
    }

    override fun getImages(): Int {
        return R.drawable.ic_baseline_sms
    }

    override fun isIntent(): Boolean {
        return true
    }

    override fun getTextButton(context: Context): String {
        return context.getString(R.string.button_sms)
    }
}