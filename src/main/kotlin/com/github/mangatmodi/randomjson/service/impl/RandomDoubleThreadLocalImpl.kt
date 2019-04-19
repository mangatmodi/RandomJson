package com.github.mangatmodi.randomjson.service.impl

import com.github.mangatmodi.randomjson.service.RandomDouble
import java.util.concurrent.ThreadLocalRandom

class RandomDoubleThreadLocalImpl : RandomDouble {
    override fun next() = ThreadLocalRandom
        .current()
        .nextDouble(1.0, 1000000.0)
        .let {
            Math.round(it * 100.0).toDouble() / 100.0
        }
}
