package com.github.mangatmodi.randomjson.service.impl

import com.github.mangatmodi.randomjson.service.RandomString
import java.util.UUID

class RandomStringUUIDImpl : RandomString {
    override fun next() = UUID.randomUUID().toString()
}
