package com.github.mangatmodi.randomjson.service

import java.util.concurrent.ThreadLocalRandom

/**
 * Generator for random double values
 * */
interface RandomDouble : RandomValue<Double> {
    /** return a random double value
     * */
    override fun next(): Double

    companion object {
        @Deprecated("Not compatible with Java. It will be removed", ReplaceWith("threadLocalRandom()"))
        fun default(): RandomDouble = RandomDoubleThreadLocalImpl()

        /**
         * Uses [ThreadLocalRandom] to generate double value between 1 and 1_000_000
         * */
        @JvmStatic
        fun threadLocalRandom(): RandomDouble = RandomDoubleThreadLocalImpl()
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
