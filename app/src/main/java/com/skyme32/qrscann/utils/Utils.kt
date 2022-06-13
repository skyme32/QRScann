package com.skyme32.qrscann.utils

import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R

fun typeBarcode(barcode: Barcode?): Triple<Boolean, String, Int> {
    var urlText = ""
    var isUrl = false
    var imageId = R.drawable.ic_launcher_foreground

    when (barcode?.valueType) {
        Barcode.TYPE_URL -> {
            urlText = "This is a URL"
            isUrl = true
            imageId = R.drawable.ic_baseline_language
        }
        Barcode.TYPE_GEO -> {
            urlText = "This is a GEOLOCALITATION"
            imageId = R.drawable.ic_baseline_location
        }
        Barcode.TYPE_EMAIL -> {
            urlText = "This is a EMAIL"
            imageId = R.drawable.ic_baseline_email
        }
        Barcode.TYPE_SMS -> {
            urlText = "This is a SMS"
            imageId = R.drawable.ic_baseline_sms
        }
        Barcode.TYPE_CALENDAR_EVENT -> {
            urlText = "This is a CALENDAR EVENT"
            imageId = R.drawable.ic_baseline_calendar
        }
        Barcode.TYPE_CONTACT_INFO -> {
            urlText = "This is a CONTACT INFO"
            imageId = R.drawable.ic_baseline_account
        }
        Barcode.TYPE_PHONE -> {
            urlText = "This is a PHONE"
            imageId = R.drawable.ic_baseline_phone
        }
        Barcode.TYPE_WIFI -> {
            urlText = "This is a WIFI"
            imageId = R.drawable.ic_baseline_wifi
        }
        Barcode.TYPE_TEXT -> {
            urlText = "This is a TEXT"
            imageId = R.drawable.ic_baseline_spellcheck
        }
        else -> {
            urlText = "This is a UNASSIGNED"
        }
    }

    return Triple(isUrl, urlText, imageId)
}

fun validateUrl(url: String): String {
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
        return "http://$url";
    }
    return url;
}


