package com.github.mangatmodi.randomjson

import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import com.github.mangatmodi.randomjson.service.RandomBoolean
import com.github.mangatmodi.randomjson.service.RandomDouble
import com.github.mangatmodi.randomjson.service.RandomInt
import com.github.mangatmodi.randomjson.service.RandomString
import java.util.UUID

data class Fixture(
    val double: Double,
    val string: String,
    val int: Int,
    val key: String,
    val size: Int
) {
    fun config() =
        RandomJsonConfig(
            object : RandomDouble {
                override fun next() = double
            },
            object : RandomInt {
                override fun next() = int
            },
            object : RandomString {
                override fun next() = string
            },
            object : RandomBoolean {
                override fun next() = true
            },
            object : RandomString {
                override fun next() = UUID.randomUUID().toString()
            }
        )
}
