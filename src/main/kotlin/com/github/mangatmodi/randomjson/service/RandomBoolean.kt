package com.github.mangatmodi.randomjson.service

interface RandomBoolean : RandomValue<Boolean> {
    override fun next(): Boolean

    companion object {
        @JvmStatic
        fun naive(): RandomBoolean = RandomBooleanUniformImpl()
    }

    private class RandomBooleanUniformImpl : RandomBoolean {
        override fun next() = Math.random() >= 0.5
    }
}
