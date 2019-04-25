package com.github.mangatmodi.randomjson.service

import com.github.mangatmodi.randomjson.entity.BooleanType
import com.github.mangatmodi.randomjson.entity.DoubleType
import com.github.mangatmodi.randomjson.entity.IntType
import com.github.mangatmodi.randomjson.entity.JsonDataType
import com.github.mangatmodi.randomjson.entity.StringType
import java.util.concurrent.ThreadLocalRandom

/**
 * Used by [com.github.mangatmodi.randomjson.service.impl.SimpleJsonCreator]. Selects the JSON type to be used
 * for next field in the random json string
 * */
interface RandomTypeSelector {
    fun select(): JsonDataType

    companion object {
        @Deprecated("Not compatible with Java. It will be removed", ReplaceWith("uniform()"))
        fun default(): RandomTypeSelector = RandomTypeSelectorNaiveImpl()

        @JvmStatic
            /**
             * Select a json value of type Integer, Double, String, Boolean.
             * Select any of them with equal probability
             * */
        fun uniform(): RandomTypeSelector = RandomTypeSelectorNaiveImpl()
    }

    private class RandomTypeSelectorNaiveImpl : RandomTypeSelector {
        override fun select(): JsonDataType {
            val rand = ThreadLocalRandom
                .current()
                .nextInt(4)
            return when (rand) {
                0 -> IntType
                1 -> DoubleType
                2 -> StringType
                3 -> BooleanType
                else -> StringType
            }
        }
    }
}
