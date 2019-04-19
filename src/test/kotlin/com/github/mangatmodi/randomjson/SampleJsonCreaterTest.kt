package com.github.mangatmodi.randomjson

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.mangatmodi.randomjson.service.impl.SampleJsonCreater
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec

class SampleJsonCreaterTest : ShouldSpec() {
    init {
        "Random Json Create" {
            should("Create a valid json with given random functions and size") {
                with(Fixture(1.0, "value", 2, "key", 10)) {
                    val jsonString = SampleJsonCreater(
                        """
{
	"id": "0001",
	"type": "donut",
	"name": "Cake",
	"ppu": 0.55,
	"batters":
		{
			"batter":
				[
					{ "id": "1001", "type": "Regular" },
					{ "id": "1002", "type": "Chocolate" },
					{ "id": "1003", "type": "Blueberry" },
					{ "id": "1004", "type": "Devil's Food" }
				]
		},
	"topping":
		[
			{ "id": "5001", "type": "None" },
			{ "id": "5002", "type": "Glazed" },
			{ "id": "5005", "type": "Sugar" },
			{ "id": "5007", "type": "Powdered Sugar" },
			{ "id": "5006", "type": "Chocolate with Sprinkles" },
			{ "id": "5003", "type": "Chocolate" },
			{ "id": "5004", "type": "Maple" }
		]
} """.trimIndent(),
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
