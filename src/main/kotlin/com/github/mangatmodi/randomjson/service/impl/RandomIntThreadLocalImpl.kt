package com.github.mangatmodi.randomjson.service.impl

import com.github.mangatmodi.randomjson.service.RandomInt
import java.util.concurrent.ThreadLocalRandom

class RandomIntThreadLocalImpl : RandomInt {
    override fun next() = ThreadLocalRandom.current().nextInt(1, Int.MAX_VALUE)
}
