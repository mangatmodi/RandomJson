package com.github.mangatmodi.randomjson

import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import com.github.mangatmodi.randomjson.service.RandomJsonCreater
import com.github.mangatmodi.randomjson.service.impl.DatatypeSelectorNaiveImpl
import com.github.mangatmodi.randomjson.service.impl.RandomDoubleThreadLocalImpl
import com.github.mangatmodi.randomjson.service.impl.RandomIntThreadLocalImpl
import com.github.mangatmodi.randomjson.service.impl.RandomStringCharArrayImpl

fun main(args: Array<String>) {
    val jsonCreater = RandomJsonCreater(
        RandomJsonConfig(5),
        RandomDoubleThreadLocalImpl(),
        RandomIntThreadLocalImpl(),
        RandomStringCharArrayImpl("eusbwopw".toCharArray(), 5),
        RandomStringCharArrayImpl("abcdefg".toCharArray(), 5),
        DatatypeSelectorNaiveImpl()
    )

    for (i in 0..10) {
        println(jsonCreater.create())
    }
}
