package com.github.mangatmodi.randomjson.service

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

/**
 * Generator for random string values
 * */
interface RandomString : RandomValue<String> {
    /** return a random string value
     * */
    override fun next(): String

    companion object {
        /**
         * Uses [UUID.randomUUID()] to generate random UUID string values
         * */

        @JvmStatic
        fun uuid(): RandomString = RandomStringUUIDImpl()

        /**
         * Get random characters from [charArray] combine them
         * to generate strings of length [length]
         * */
        @JvmStatic
        fun charArray(charArray: CharArray, length: Int): RandomString = RandomStringCharArrayImpl(charArray, length)
    }

    class RandomStringUUIDImpl : RandomString {
        override fun next() = UUID.randomUUID().toString()
    }

    class RandomStringCharArrayImpl(
        private val charArray: CharArray,
        private val length: Int
    ) : RandomString {
        override fun next() = (0..length).map {
            val index = Random.nextInt(0, charArray.size)
            charArray[index]
        }.joinToString("")
    }

    class RandomStringDateImpl(private val format: DateTimeFormatter) : RandomString {
        override fun next(): String =
            ZonedDateTime
                .now()
                .plusDays(ThreadLocalRandom.current().nextLong(0, 10_000))
                .format(format)
    }
}
