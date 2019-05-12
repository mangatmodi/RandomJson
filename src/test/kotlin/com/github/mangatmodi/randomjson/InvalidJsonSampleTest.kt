package com.github.mangatmodi.randomjson

import io.kotlintest.matchers.shouldThrowAny
import io.kotlintest.specs.ShouldSpec

class InvalidJsonSampleTest : ShouldSpec() {
    init {
        "Random Json Create" {
            should("Create a valid json with given random functions and size") {
                with(Fixture(1.0, "value", 2, "key", 10)) {
                    shouldThrowAny {
                        RandomJsonCreator.fromSampleString(
                            """2""".trimIndent(),
                            this.config()
                        ).create()
                    }
                }
            }
        }
    }
}
