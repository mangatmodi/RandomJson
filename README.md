# RandomJson [![CodeFactor](https://www.codefactor.io/repository/github/mangatmodi/randomjson/badge)](https://www.codefactor.io/repository/github/mangatmodi/randomjson) [![Build Status](https://travis-ci.com/mangatmodi/RandomJson.svg?branch=master)](https://travis-ci.com/mangatmodi/RandomJson)
Provides library to create a random json

Example

```
    val jsonCreator = RandomJsonCreator(
        RandomJsonConfig(5),
        RandomDoubleThreadLocalImpl(),
        RandomIntThreadLocalImpl(),
        RandomStringCharArrayImpl("eusbwopw".toCharArray(), 5),
        RandomBooleanUniformImpl(),
        RandomStringCharArrayImpl("abcdefg".toCharArray(), 5),
        DatatypeSelectorNaiveImpl()
    )

        println(jsonCreator.create())

```
