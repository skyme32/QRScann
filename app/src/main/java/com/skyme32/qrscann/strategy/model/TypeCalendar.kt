package com.skyme32.qrscann.strategy.model

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import com.google.mlkit.vision.barcode.common.Barcode
import com.skyme32.qrscann.R
import com.skyme32.qrscann.strategy.BarcodeDefinition

object TypeCalendar: BarcodeDefinition {

    override fun appliedTypeBarcode(): Int {
        return Barcode.TYPE_CALENDAR_EVENT
    }

    override fun getIntent(context: Context, barcode: Barcode): Intent {

        return Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, barcode.calendarEvent?.summary) // Simple title
            putExtra(CalendarContract.Events.DESCRIPTION, barcode.calendarEvent?.description)
            putExtra(CalendarContract.Events.AVAILABILITY, barcode.calendarEvent?.status)
            putExtra(CalendarContract.Events.EVENT_LOCATION, barcode.calendarEvent?.location)
            putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,barcode.calendarEvent?.start?.rawValue)
        }
    }

    override fun getText(context: Context): String {
        return context.getString(R.string.is_calendar)
    }

    override fun getImages(): Int {
        return R.drawable.ic_baseline_calendar
    }

    override fun isIntent(): Boolean {
        return true
    }

    override fun getTextButton(context: Context): String {
        return context.getString(R.string.button_calendar)
    }
}