package com.github.mangatmodi.randomjson.service

import com.github.mangatmodi.randomjson.entity.JsonDataType

interface DatatypeSelector {
    fun select(): JsonDataType
}
