package com.github.mangatmodi.randomjson.service.impl

import com.github.mangatmodi.randomjson.service.RandomString
import kotlin.random.Random

class RandomStringCharArrayImpl(
    private val charArray: CharArray,
    private val length: Int
) : RandomString {
    override fun next() = (0..length).map {
        val index = Random.nextInt(0, charArray.size)
        charArray[index]
    }.joinToString("")
}
