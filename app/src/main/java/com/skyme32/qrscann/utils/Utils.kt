package com.skyme32.qrscann.utils

import android.provider.ContactsContract
import com.google.mlkit.vision.barcode.common.Barcode

fun validateUrl(url: String): String {
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
        return "http://$url";
    }
    return url;
}

fun typeContact(phone: Barcode.Phone?): Int {

    return when (phone?.type) {
        0 -> {
            ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM
        }
        1 -> {
            ContactsContract.CommonDataKinds.Phone.TYPE_WORK
        }
        3 -> {
            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
        }
        4 -> {
            ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME
        }
        else -> {
            ContactsContract.CommonDataKinds.Phone.TYPE_OTHER
        }
    }
}

fun contractIntentInsert(index: Int): String {
    ContactsContract.Intents.Insert.PHONE
    return when (index) {
        0 -> {
            ContactsContract.Intents.Insert.PHONE
        }
        1 -> {
            ContactsContract.Intents.Insert.SECONDARY_PHONE
        }
        else -> {
            ContactsContract.Intents.Insert.TERTIARY_PHONE
        }
    }

}




