package com.github.mangatmodi.randomjson.service

import java.util.concurrent.ThreadLocalRandom

interface RandomInt : RandomValue<Int> {
    override fun next(): Int

    companion object {
        @JvmStatic
        fun naive(): RandomInt = RandomIntThreadLocalImpl()

        private class RandomIntThreadLocalImpl : RandomInt {
            override fun next() = ThreadLocalRandom.current().nextInt(1, Int.MAX_VALUE)
        }
    }
}
