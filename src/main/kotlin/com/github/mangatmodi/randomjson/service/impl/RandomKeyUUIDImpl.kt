package com.github.mangatmodi.randomjson.service.impl

import com.github.mangatmodi.randomjson.service.RandomKey
import java.util.*

class RandomKeyUUIDImpl : RandomKey {
    override fun next() = UUID.randomUUID().toString()
}
