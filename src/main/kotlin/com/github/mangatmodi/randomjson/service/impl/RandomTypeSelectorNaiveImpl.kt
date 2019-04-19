package com.github.mangatmodi.randomjson.service.impl

import com.github.mangatmodi.randomjson.entity.BooleanType
import com.github.mangatmodi.randomjson.entity.DoubleType
import com.github.mangatmodi.randomjson.entity.IntType
import com.github.mangatmodi.randomjson.entity.JsonDataType
import com.github.mangatmodi.randomjson.entity.StringType
import com.github.mangatmodi.randomjson.service.RandomTypeSelector
import java.util.concurrent.ThreadLocalRandom

class RandomTypeSelectorNaiveImpl : RandomTypeSelector {
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
