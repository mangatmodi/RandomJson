package com.github.mangatmodi.randomjson.service.impl

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import com.github.mangatmodi.randomjson.service.RandomJsonCreator
import com.github.mangatmodi.randomjson.service.RandomTypeSelector
import org.slf4j.LoggerFactory

/**
 * Creates JSON string by generating fields using generators in [config].
 * JSON string is created by taking the [sampleJson] as example
 *
 *  @property sampleJson is json string, whose structure needs to be used
 *  @property typeSelector [RandomTypeSelector] specify which field to choose next
 */
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
        val json = objectMapper.createObjectNode()
        tree.fields().forEach {
            json.put(it.value)
        }
        return objectMapper.writeValueAsString(json)
    }

    private fun ObjectNode.put(value: JsonNode) {
        val key = config.randomKey.next()
        when {
            value.isNull -> {
            }
            value.isArray -> value.forEach { put(it) }
            value.isObject -> {
                val json = this.putObject(key)
                value.fields().forEach {
                    json.put(it.value)
                }
            }
            value.isInt || value.isLong || value.isBigInteger -> put(key, config.randomInt.next())
            value.isDouble || value.isBigDecimal -> put(key, config.randomDouble.next())
            value.isTextual -> put(key, config.randomString.next())
            value.isBoolean -> put(key, config.randomBoolean.next())
            else -> throw UnsupportedOperationException("Datatype of $value not supported")
        }
    }
}
