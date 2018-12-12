package com.github.mangatmodi.randomjson.entity

sealed class JsonDataType
object StringType : JsonDataType()
object DoubleType : JsonDataType()
object IntType : JsonDataType()
