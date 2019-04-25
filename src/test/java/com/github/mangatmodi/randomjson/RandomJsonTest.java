package com.github.mangatmodi.randomjson;

import com.github.mangatmodi.randomjson.config.RandomJsonConfig;
import com.github.mangatmodi.randomjson.service.RandomBoolean;
import com.github.mangatmodi.randomjson.service.RandomDouble;
import com.github.mangatmodi.randomjson.service.RandomInt;
import com.github.mangatmodi.randomjson.service.RandomString;
import org.junit.Test;

public class RandomJsonTest {
    @Test
    public void testJson() {

        RandomJsonConfig config = new RandomJsonConfig(
                RandomDouble.threadLocalRandom(),
                RandomInt.threadLocalRandom(),
                RandomString.charArray("eusbwopw".toCharArray(), 5),
                RandomBoolean.uniform(),
                RandomString.charArray("abcdefg".toCharArray(), 5)
        );

        RandomJsonCreator creator = RandomJsonCreator.fromSampleString(
                "{\"q\":1}",
                config
        );
        System.out.println(creator.create());
    }
}
