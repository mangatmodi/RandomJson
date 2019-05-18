package com.github.mangatmodi.randomjson.service.string

import com.github.mangatmodi.randomjson.service.RandomString
import java.time.format.DateTimeFormatter
import java.util.UUID

sealed class StringFormat {
    abstract fun detect(s: String): RandomString?
}

object UUIDFormat : StringFormat() {
    override fun detect(s: String): RandomString? =
        try {
            java.util.UUID.fromString(s)
            RandomString.RandomStringUUIDImpl()
        } catch (e: Throwable) {
            null
        }
}

object DateFormat : StringFormat() {
    val possibleFormats = listOf(
        DateTimeFormatter.BASIC_ISO_DATE,
        DateTimeFormatter.ISO_DATE_TIME,
        DateTimeFormatter.ISO_DATE,
        DateTimeFormatter.ISO_OFFSET_DATE,
        DateTimeFormatter.ISO_OFFSET_DATE_TIME
    )

    override fun detect(s: String): RandomString? =
        possibleFormats.map {
            try {
                it.parse(s)
                it
            } catch (e: Exception) {
                null
            }
        }.filterNotNull()
            .map { RandomString.RandomStringDateImpl(it) }
            .firstOrNull()
}

object Email : StringFormat()
object PhoneNumber : StringFormat()
