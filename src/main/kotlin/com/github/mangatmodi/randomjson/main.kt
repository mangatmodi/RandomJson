package com.github.mangatmodi.randomjson

import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import com.github.mangatmodi.randomjson.service.impl.RandomBooleanUniformImpl
import com.github.mangatmodi.randomjson.service.impl.RandomDoubleThreadLocalImpl
import com.github.mangatmodi.randomjson.service.impl.RandomIntThreadLocalImpl
import com.github.mangatmodi.randomjson.service.impl.RandomStringCharArrayImpl
import com.github.mangatmodi.randomjson.service.impl.SampleJsonCreater
import picocli.CommandLine

@CommandLine.Command
class Runner {
    @CommandLine.Option(names = ["f", "--file"], description = ["File with json structure"])
    var fileName: String? = null

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val jsonCreater = SampleJsonCreater(
                """{"key1":{"key2":3}}""",
                RandomJsonConfig(
                    RandomDoubleThreadLocalImpl(),
                    RandomIntThreadLocalImpl(),
                    RandomStringCharArrayImpl("eusbwopw".toCharArray(), 5),
                    RandomBooleanUniformImpl(),
                    RandomStringCharArrayImpl("abcdefg".toCharArray(), 5)
                )
            )

            for (i in 0..10) {
                println(jsonCreater.create())
            }
        }
    }
}
