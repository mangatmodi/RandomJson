package com.github.mangatmodi.randomjson

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.mangatmodi.randomjson.RandomJsonCreator.Companion.KeepKeys
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec

class SampleJsonCreatorTest : ShouldSpec() {
    init {
        "Random Json Create" {
            should("Create a valid json with given random functions and size") {
                with(Fixture(1.0, "value", 2, "key", 10)) {
                    val jsonString = RandomJsonCreator.fromSampleString(
                        """
{
	"id": 23,
	"CGPA": 4.78,
	"name": "Mangat",
	"date": "2019-04-25 10:01:01 [UTC]",
	"courses":
		[
			{ "id": "1", "name": "Algorithms", "score":"3.5" },
			{ "id": "2", "type": "Functional Programming", "score":"4.1" },
			{ "id": "3", "type": "Linear Alegbra", "score":"5.0" },
			{ "id": "4", "type": "Data Streaming", "score":"5.0" }
		]
}
 """.trimIndent(),
                        KeepKeys.YES,
                        this.config()
                    ).create()
                    println(jsonString)
                    val json = ObjectMapper().readTree(jsonString)
                    json.asIterable().forEach {
                        when {
                            it.isDouble -> it.asDouble() shouldBe 1.0
                            it.isTextual -> it.asText() shouldBe "value"
                            it.isInt -> it.asInt() shouldBe 2
                            it.isBoolean -> it.asBoolean() shouldBe true
                        }
                    }
                }
            }
        }
    }
}
