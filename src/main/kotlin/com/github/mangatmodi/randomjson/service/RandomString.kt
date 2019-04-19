package com.github.mangatmodi.randomjson.service

interface RandomString:RandomValue<String> {
    override fun next(): String
}
