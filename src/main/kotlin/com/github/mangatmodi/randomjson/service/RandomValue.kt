package com.github.mangatmodi.randomjson.service

interface RandomValue<out T> {
    fun next(): T
}
