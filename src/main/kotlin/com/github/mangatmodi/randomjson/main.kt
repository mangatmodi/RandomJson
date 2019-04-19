package com.github.mangatmodi.randomjson

import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import com.github.mangatmodi.randomjson.service.RandomBoolean
import com.github.mangatmodi.randomjson.service.RandomDouble
import com.github.mangatmodi.randomjson.service.RandomInt
import com.github.mangatmodi.randomjson.service.RandomJsonCreator
import com.github.mangatmodi.randomjson.service.RandomString
import com.github.mangatmodi.randomjson.service.impl.RandomTypeSelectorNaiveImpl
import picocli.CommandLine

@CommandLine.Command
class Runner {
    @CommandLine.Option(names = ["f", "--file"], description = ["File with json structure"])
    var fileName: String? = null

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val jsonCreater = RandomJsonCreator.fromNumberOfKeys(
                5,
                RandomJsonConfig(
                    RandomDouble.naive(),
                    RandomInt.naive(),
                    RandomString.charArray("eusbwopw".toCharArray(), 5),
                    RandomBoolean.naive(),
                    RandomString.charArray("abcdefg".toCharArray(), 5)
                ),
                RandomTypeSelectorNaiveImpl()
            )

            for (i in 0..10) {
                println(jsonCreater.create())
            }
        }
    }
}
