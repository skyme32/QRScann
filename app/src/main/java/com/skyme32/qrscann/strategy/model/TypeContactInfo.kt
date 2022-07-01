package com.skyme32.qrscann.strategy.model

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.strategy.BarcodeDefinition
import com.skyme32.qrscann.utils.contractIntentInsert
import com.skyme32.qrscann.utils.typeContact


object TypeContactInfo : BarcodeDefinition {

    override fun appliedTypeBarcode(): Int {
        return Barcode.TYPE_CONTACT_INFO
    }

    override fun getIntent(context: Context, barcode: Barcode): Intent {
        return generateContact(barcode)
    }

    override fun getText(context: Context): String {
        return context.getString(R.string.is_contact)
    }

    override fun getImages(): Int {
        return R.drawable.ic_baseline_account
    }

    override fun isIntent(): Boolean {
        return true
    }

    override fun getTextButton(context: Context): String {
        return context.getString(R.string.button_contact)
    }

    private fun generateContact(barcode: Barcode): Intent {
        val intent = Intent(ContactsContract.Intents.Insert.ACTION)
        intent.type = ContactsContract.RawContacts.CONTENT_TYPE

        barcode.contactInfo?.emails?.forEach { email ->
            intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.address)
                .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, email.type)
        }

        barcode.contactInfo?.phones?.forEachIndexed { index, phone ->
            intent.putExtra(contractIntentInsert(index), phone.number)
                .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, typeContact(phone))
        }

        barcode.contactInfo?.addresses?.forEach { addresses ->
            addresses.addressLines.forEach { addressLines ->
                intent.putExtra(ContactsContract.Intents.Insert.POSTAL, addressLines)
            }
        }

        barcode.contactInfo?.urls?.forEach { urls ->
            intent.putExtra(ContactsContract.Intents.Insert.IM_HANDLE, urls)
        }

        intent.putExtra(ContactsContract.Intents.Insert.NAME, barcode.displayValue)
            .putExtra(
                ContactsContract.Intents.Insert.PHONETIC_NAME,
                barcode.contactInfo?.name?.pronunciation
            )
            .putExtra(
                ContactsContract.Intents.Insert.COMPANY,
                barcode.contactInfo?.organization
            )
            .putExtra(
                ContactsContract.Intents.Insert.JOB_TITLE,
                barcode.contactInfo?.title
            )

        return intent
    }
}