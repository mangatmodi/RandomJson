package com.github.mangatmodi.randomjson

import com.github.mangatmodi.randomjson.RandomJsonCreator.Companion.KeepKeys
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
{
  "id": 23,
  "CGPA": 4.78,
  "name": "Mangat",
  "date": "2019-04-25 10:01:01 [UTC]",
  "courses": [
    {
      "id": 1,
      "name": "Algorithms",
      "score": 3.5
    },
    {
      "id": 2,
      "type": "Functional Programming",
      "score": 4.1
    },
    {
      "id": 3,
      "type": "Linear Alegbra",
      "score": 5
    },
    {
      "id": 4,
      "type": "Data Streaming",
      "score": 5
    }
  ]
}
""".trimIndent()

    val jsonCreater = RandomJsonCreator
        .fromSampleString(input, KeepKeys.YES, config)
    println(jsonCreater.create())
}
