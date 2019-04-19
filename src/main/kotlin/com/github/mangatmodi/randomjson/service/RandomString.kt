package com.github.mangatmodi.randomjson.service

import java.util.UUID
import kotlin.random.Random

interface RandomString : RandomValue<String> {
    override fun next(): String

    companion object {
        @JvmStatic
        fun uuid(): RandomString = RandomStringUUIDImpl()

        @JvmStatic
        fun charArray(charArray: CharArray, length: Int): RandomString = RandomStringCharArrayImpl(charArray, length)
    }

    private class RandomStringUUIDImpl : RandomString {
        override fun next() = UUID.randomUUID().toString()
    }

    private class RandomStringCharArrayImpl(
        private val charArray: CharArray,
        private val length: Int
    ) : RandomString {
        override fun next() = (0..length).map {
            val index = Random.nextInt(0, charArray.size)
            charArray[index]
        }.joinToString("")
    }
}
