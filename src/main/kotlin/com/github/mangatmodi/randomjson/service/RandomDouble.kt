package com.github.mangatmodi.randomjson.service

import java.util.concurrent.ThreadLocalRandom

interface RandomDouble : RandomValue<Double> {
    override fun next(): Double

    companion object {
        @JvmStatic
        fun naive(): RandomDouble = RandomDoubleThreadLocalImpl()
    }

    private class RandomDoubleThreadLocalImpl : RandomDouble {
        override fun next() = ThreadLocalRandom
            .current()
            .nextDouble(1.0, 1000000.0)
            .let {
                Math.round(it * 100.0).toDouble() / 100.0
            }
    }
}
