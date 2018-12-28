package com.github.mangatmodi.randomjson.service

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import com.github.mangatmodi.randomjson.entity.BooleanType
import com.github.mangatmodi.randomjson.entity.DoubleType
import com.github.mangatmodi.randomjson.entity.IntType
import com.github.mangatmodi.randomjson.entity.StringType

// TODO: Use reader monad, Boolean
class RandomJsonCreater(
    private val config: RandomJsonConfig,
    private val randomDouble: RandomDouble,
    private val randomInt: RandomInt,
    private val randomString: RandomString,
    private val randomBoolean: RandomBoolean,
    private val randomKey: RandomString,
    private val typeSelector: DatatypeSelector
) {
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

    fun create(): String =
        objectMapper.createObjectNode().apply {
            (1..config.numberOfFields)
                .forEach {
                    val type = typeSelector.select()
                    val key = randomKey.next()
                    when (type) {
                        StringType -> put(key, randomString.next())
                        DoubleType -> put(key, randomDouble.next())
                        IntType -> put(key, randomInt.next())
                        BooleanType -> put(key, randomBoolean.next())
                    }
                }
        }.let { objectMapper.writeValueAsString(it) }
}
