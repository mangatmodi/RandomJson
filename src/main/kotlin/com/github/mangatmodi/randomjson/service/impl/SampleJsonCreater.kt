package com.github.mangatmodi.randomjson.service.impl

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.BooleanNode
import com.fasterxml.jackson.databind.node.DoubleNode
import com.fasterxml.jackson.databind.node.IntNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.NullNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.node.TextNode
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.mangatmodi.randomjson.RandomJsonCreator
import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import org.slf4j.LoggerFactory

internal class SampleJsonCreator(
    private val sampleJson: String,
    private val config: RandomJsonConfig
) : RandomJsonCreator {
    val logger = LoggerFactory.getLogger(this::class.java)
    private val objectMapper: ObjectMapper = ObjectMapper()
        .registerModule(JavaTimeModule())
        .registerModule(KotlinModule())
        .apply {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
            configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            configure(JsonParser.Feature.IGNORE_UNDEFINED, true)
        }

    private val tree = try {
        objectMapper.readTree(sampleJson)
    } catch (e: Throwable) {
        logger.error("Invalid sample json:$sampleJson")
        throw e
    }

    override fun create(): String {
        val node = when {
            tree.isArray -> {
                val json = objectMapper.createArrayNode()
                tree.elements().forEach {
                    json.add(next(it))
                }
                json
            }
            tree.isObject -> {
                val json = objectMapper.createObjectNode()
                tree.fields().forEach {
                    json.set(config.randomKey.next(), next(it.value))
                }
                json
            }
            else -> {
                NullNode.instance
            }
        }
        return objectMapper.writeValueAsString(node)
    }

    private fun next(value: JsonNode): JsonNode {
        return when {
            value.isNull -> NullNode.instance

            value.isArray -> {
                val arr = ArrayNode(JsonNodeFactory.instance, value.size())
                value.forEach {
                    arr.add(next(it))
                }
                arr
            }
            value.isObject -> {
                val obj = ObjectNode(JsonNodeFactory.instance)
                value.fields().forEach {
                    obj.set(config.randomKey.next(), next(it.value))
                }
                obj
            }
            value.isInt || value.isLong || value.isBigInteger -> IntNode.valueOf(config.randomInt.next())
            value.isDouble || value.isBigDecimal -> DoubleNode.valueOf(config.randomDouble.next())
            value.isTextual -> TextNode.valueOf(config.randomString.next())
            value.isBoolean -> BooleanNode.valueOf(config.randomBoolean.next())
            else -> throw UnsupportedOperationException("Datatype of $value not supported")
        }
    }
}
