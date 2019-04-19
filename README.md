# RandomJson [![CodeFactor](https://www.codefactor.io/repository/github/mangatmodi/randomjson/badge)](https://www.codefactor.io/repository/github/mangatmodi/randomjson) [![Build Status](https://travis-ci.com/mangatmodi/RandomJson.svg?branch=master)](https://travis-ci.com/mangatmodi/RandomJson)
Provides library to create a random json

Example

```
            val jsonCreater = RandomJsonCreator.fromSampleString(
                """{"key1":{"key2":3}}""",
                RandomJsonConfig(
                    RandomDouble.naive(),
                    RandomInt.naive(),
                    RandomString.charArray("eusbwopw".toCharArray(), 5),
                    RandomBoolean.naive(),
                    RandomString.charArray("abcdefg".toCharArray(), 5)
                )
            )

                println(jsonCreater.create())
            

```
