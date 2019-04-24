package com.github.mangatmodi.randomjson

import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import com.github.mangatmodi.randomjson.service.RandomTypeSelector
import com.github.mangatmodi.randomjson.service.impl.SampleJsonCreator
import com.github.mangatmodi.randomjson.service.impl.SimpleJsonCreator

interface RandomJsonCreator {
    fun create(): String

    companion object {
        @JvmStatic
            /**
             * Creates JSON string by generating fields using generators in [config].
             * JSON string is created by taking the [sampleJson] as example
             *
             *  @property sampleJson is json string, whose structure needs to be used
             */
        fun fromSampleString(
            sampleJson: String,
            config: RandomJsonConfig
        ): RandomJsonCreator = SampleJsonCreator(sampleJson, config)

        @JvmStatic
            /**
             * Creates JSON string by generating fields using generators in [config].
             * Also needs [numberOfFields] and [typeSelector] for nest field as property
             * *Note: Doesn't support JSON arrays or nested JSON objects*
             *  @property numberOfFields is number of fields in the generated json
             *  @property typeSelector specify which field to choose next. Implementation of [RandomTypeSelector]
             */
        fun fromNumberOfKeys(
            numberOfFields: Int,
            config: RandomJsonConfig,
            typeSelector: RandomTypeSelector
        ): RandomJsonCreator = SimpleJsonCreator(numberOfFields, config, typeSelector)
    }
}
