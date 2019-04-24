package com.github.mangatmodi.randomjson.service

import com.github.mangatmodi.randomjson.entity.JsonDataType
/**
 * Used by [com.github.mangatmodi.randomjson.service.impl.SimpleJsonCreator]. Selects the JSON type to be used
 * for next field in the random json string
 * */
interface RandomTypeSelector {
    fun select(): JsonDataType
}
