package com.github.mangatmodi.randomjson.service

import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import com.github.mangatmodi.randomjson.service.impl.SampleJsonCreator
import com.github.mangatmodi.randomjson.service.impl.SimpleJsonCreator

interface RandomJsonCreator {
    fun create(): String

    companion object {
        @JvmStatic
        fun fromSampleString(
            sampleJson: String,
            config: RandomJsonConfig
        ): RandomJsonCreator = SampleJsonCreator(sampleJson, config)

        @JvmStatic
        fun fromNumberOfKeys(
            numberOfFields: Int,
            config: RandomJsonConfig,
            typeSelector: RandomTypeSelector
        ): RandomJsonCreator = SimpleJsonCreator(numberOfFields, config, typeSelector)
    }
}
