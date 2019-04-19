package com.github.mangatmodi.randomjson.service

import com.github.mangatmodi.randomjson.entity.JsonDataType

interface RandomTypeSelector {
    fun select(): JsonDataType
}
