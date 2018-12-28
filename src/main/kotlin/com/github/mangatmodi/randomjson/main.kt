package com.github.mangatmodi.randomjson

import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import com.github.mangatmodi.randomjson.service.RandomJsonCreater
import com.github.mangatmodi.randomjson.service.impl.*

fun main(args: Array<String>) {
    val jsonCreater = RandomJsonCreater(
        RandomJsonConfig(5),
        RandomDoubleThreadLocalImpl(),
        RandomIntThreadLocalImpl(),
        RandomStringUUIDImpl(),
        RandomKeyUUIDImpl(),
        DatatypeSelectorNaiveImpl()
    )

    for (i in 0..10) {
        println(jsonCreater.create())
    }
}
