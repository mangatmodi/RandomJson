package com.github.mangatmodi.randomjson

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import com.github.mangatmodi.randomjson.service.RandomDouble
import com.github.mangatmodi.randomjson.service.RandomInt
import com.github.mangatmodi.randomjson.service.RandomJsonCreater
import com.github.mangatmodi.randomjson.service.RandomString
import com.github.mangatmodi.randomjson.service.impl.DatatypeSelectorNaiveImpl
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec
import java.util.UUID

data class Fixture(
    val double: Double,
    val string: String,
    val int: Int,
    val key: String,
    val size: Int
) {
    fun creater() = RandomJsonCreater(
        RandomJsonConfig(size),
        object : RandomDouble {
            override fun next() = double
        },
        object : RandomInt {
            override fun next() = int
        },
        object : RandomString {
            override fun next() = string
        },
        object : RandomString {
            override fun next() = UUID.randomUUID().toString()
        },
        DatatypeSelectorNaiveImpl()
    )
}

class RandomJsonCreaterTest : ShouldSpec() {
    init {
        "Random Json Create" {
            should("Create a valid json with given random functions and size") {
                with(Fixture(1.0, "value", 2, "key", 2000)) {
                    val jsonString = this.creater().create()
                    println(jsonString)
                    val json = ObjectMapper().readTree(jsonString)
                    json.size() shouldBe 2000

                    json.asIterable().forEach {
                        when {
                            it.isDouble -> it.asDouble() shouldBe 1.0
                            it.isTextual -> it.asText() shouldBe "value"
                            it.isInt -> it.asInt() shouldBe 2
                            else -> assert(false) {
                                "Unexpected data type for value:$it in json created: $jsonString"
                            }
                        }
                    }
                }
            }
        }
    }
}
