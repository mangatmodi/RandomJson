package com.github.mangatmodi.randomjson

import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import com.github.mangatmodi.randomjson.service.RandomBoolean
import com.github.mangatmodi.randomjson.service.RandomDouble
import com.github.mangatmodi.randomjson.service.RandomInt
import com.github.mangatmodi.randomjson.service.RandomString

fun main(args: Array<String>) {
    val config = RandomJsonConfig(
        RandomDouble.threadLocalRandom(),
        RandomInt.threadLocalRandom(),
        RandomString.charArray("eusbwopw".toCharArray(), 4),
        RandomBoolean.uniform(),
        RandomString.charArray("abcdefg".toCharArray(), 3)
    )
    val input = """
[1,2,5.0,"hello"]

    """.trimIndent()

    val jsonCreater = RandomJsonCreator
        .fromSampleString(input, true, config)
    println(jsonCreater.create())
}
