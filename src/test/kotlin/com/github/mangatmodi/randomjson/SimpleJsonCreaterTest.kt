package com.github.mangatmodi.randomjson

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.mangatmodi.randomjson.service.impl.RandomTypeSelectorNaiveImpl
import com.github.mangatmodi.randomjson.service.impl.SimpleJsonCreater
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec

class SimpleJsonCreaterTest : ShouldSpec() {
    init {
        "Random Json Create" {
            should("Create a valid json with given random functions and size") {
                with(Fixture(1.0, "value", 2, "key", 10)) {
                    val jsonString = SimpleJsonCreater(
                        10,
                        this.config(),
                        RandomTypeSelectorNaiveImpl()).create()
                    println(jsonString)
                    val json = ObjectMapper().readTree(jsonString)
                    json.size() shouldBe 10

                    json.asIterable().forEach {
                        when {
                            it.isDouble -> it.asDouble() shouldBe 1.0
                            it.isTextual -> it.asText() shouldBe "value"
                            it.isInt -> it.asInt() shouldBe 2
                            it.isBoolean -> it.asBoolean() shouldBe true
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
