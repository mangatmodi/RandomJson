# RandomJson [![Codacy Badge](https://api.codacy.com/project/badge/Grade/9b63288ec5374103bd2a1156732de970)](https://app.codacy.com/app/mangatmodi/RandomJson?utm_source=github.com&utm_medium=referral&utm_content=mangatmodi/RandomJson&utm_campaign=Badge_Grade_Dashboard) [![Build Status](https://travis-ci.com/mangatmodi/RandomJson.svg?branch=master)](https://travis-ci.com/mangatmodi/RandomJson)
Provides library to create a random json

Example

```
    val jsonCreater = RandomJsonCreater(
        RandomJsonConfig(5),
        RandomDoubleThreadLocalImpl(),
        RandomIntThreadLocalImpl(),
        RandomStringCharArrayImpl("eusbwopw".toCharArray(), 5),
        RandomBooleanUniformImpl(),
        RandomStringCharArrayImpl("abcdefg".toCharArray(), 5),
        DatatypeSelectorNaiveImpl()
    )

        println(jsonCreater.create())

```
