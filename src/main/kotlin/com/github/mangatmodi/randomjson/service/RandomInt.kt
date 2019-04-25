package com.github.mangatmodi.randomjson.service

import java.util.concurrent.ThreadLocalRandom

/**
 * Generator for random boolean values
 * */
interface RandomInt : RandomValue<Int> {
    override fun next(): Int

    companion object {
        @Deprecated("Not compatible with Java. It will be removed", ReplaceWith("threadLocalRandom()"))
        fun default(): RandomInt = RandomIntThreadLocalImpl()

        /**
         * Uses [ThreadLocalRandom] to generate double value between 1 and [Int.MAX_VALUE]
         * */
        @JvmStatic
        fun threadLocalRandom(): RandomInt = RandomIntThreadLocalImpl()

        private class RandomIntThreadLocalImpl : RandomInt {
            override fun next() = ThreadLocalRandom.current().nextInt(1, Int.MAX_VALUE)
        }
    }
}
