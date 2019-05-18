package com.github.mangatmodi.randomjson.entity

sealed class JsonDataType {
    fun next() {
    }
}

object StringType : JsonDataType()
object DoubleType : JsonDataType()
object IntType : JsonDataType()
object BooleanType : JsonDataType()
