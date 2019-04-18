package com.github.mangatmodi.randomjson.config

import com.github.mangatmodi.randomjson.service.RandomBoolean
import com.github.mangatmodi.randomjson.service.RandomDouble
import com.github.mangatmodi.randomjson.service.RandomInt
import com.github.mangatmodi.randomjson.service.RandomString

data class RandomJsonConfig(
    val randomDouble: RandomDouble,
    val randomInt: RandomInt,
    val randomString: RandomString,
    val randomBoolean: RandomBoolean,
    val randomKey: RandomString
)
