package com.github.mangatmodi.randomjson.service.impl

import com.github.mangatmodi.randomjson.service.RandomBoolean

class RandomBooleanUniformImpl : RandomBoolean {
    override fun next() = Math.random() >= 0.5
}
