package com.github.mangatmodi.randomjson.service

/**
 * Generator for random boolean values
 * */
interface RandomBoolean : RandomValue<Boolean> {
    /** return a random boolean value
     * */
    override fun next(): Boolean

    companion object {
        @Deprecated("Not compatible with Java. It will be removed", ReplaceWith("uniform()"))
        @JvmStatic
        fun default(): RandomBoolean = RandomBooleanUniformImpl()

        @JvmStatic
        fun uniform(): RandomBoolean = RandomBooleanUniformImpl()
    }

    private class RandomBooleanUniformImpl : RandomBoolean {
        override fun next() = Math.random() >= 0.5
    }
}
