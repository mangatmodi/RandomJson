package com.github.mangatmodi.randomjson

import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import com.github.mangatmodi.randomjson.service.RandomBoolean
import com.github.mangatmodi.randomjson.service.RandomDouble
import com.github.mangatmodi.randomjson.service.RandomInt
import com.github.mangatmodi.randomjson.service.RandomJsonCreator
import com.github.mangatmodi.randomjson.service.RandomString
import picocli.CommandLine

@CommandLine.Command
class Runner {
    @CommandLine.Option(names = ["f", "--file"], description = ["File with json structure"])
    var fileName: String? = null
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val jsonCreater = RandomJsonCreator.fromSampleString(
                """{"key1":{"key2":3}}""",
                RandomJsonConfig(
                    RandomDouble.default(),
                    RandomInt.default(),
                    RandomString.charArray("eusbwopw".toCharArray(), 5),
                    RandomBoolean.default(),
                    RandomString.charArray("abcdefg".toCharArray(), 5)
                )
            )

            for (i in 0..10) {
                println(jsonCreater.create())
            }
        }
    }
}
