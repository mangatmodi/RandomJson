RandomJson [![CodeFactor](https://www.codefactor.io/repository/github/mangatmodi/randomjson/badge)](https://www.codefactor.io/repository/github/mangatmodi/randomjson) [![Build Status](https://travis-ci.com/mangatmodi/RandomJson.svg?branch=master)](https://travis-ci.com/mangatmodi/RandomJson)

Provides library to create a random json. Provides two implementation of json creation

**[SampleJsonCreator](#SampleJsonCreator)**: Creates JSON string from a sample string.

**[SimpleJsonCreator](#SimpleJsonCreator)**: Creates JSON string by taking number of required keys.

Some useful features
1. The random value generations could be customised by giving your own [implementation](#Overloaded-random-generator).
2. The default given implementation is _thread-safe_. That means random strings can be [created](#Parallel-creation-of-random-strings) in different threads  
### Examples
#### SampleJsonCreator
```
            val config =  RandomJsonConfig(
                                             RandomDouble.default(),
                                             RandomInt.default(),
                                             RandomString.charArray("eusbwopw".toCharArray(), 5),
                                             RandomBoolean.default(),
                                             RandomString.charArray("abcdefg".toCharArray(), 5)
                                         )
            val jsonCreater = RandomJsonCreator
            .fromSampleString("""{"key1":{"key2":3}}""",config)
            println(jsonCreater.create())            
    
```
#### SimpleJsonCreator 
```
            val config =  RandomJsonConfig(
                                             RandomDouble.default(),
                                             RandomInt.default(),
                                             RandomString.charArray("eusbwopw".toCharArray(), 5),
                                             RandomBoolean.default(),
                                             RandomString.charArray("abcdefg".toCharArray(), 5)
                                         )
            val jsonCreater = RandomJsonCreator
            .fromNumberOfKeys(10,config, RandomTypeSelector.default())
            println(jsonCreater.create())            
    
```
#### Overloaded random generator

In the example below `DummyDoubleValue` implements `RandomDouble` to give `2.0`
as the double value. So all the JSON strings created by `jsonCreator` below will contain `2.0` as double value
```
           class DummyDoubleValue:RandomDouble{
             override fun next() =  2.0
           }

            val config =  RandomJsonConfig(
                                             DummyDoubleValue(),
                                             RandomInt.default(),
                                             RandomString.charArray("eusbwopw".toCharArray(), 5),
                                             RandomBoolean.default(),
                                             RandomString.charArray("abcdefg".toCharArray(), 5)
                                         )
            val jsonCreater = RandomJsonCreator
            .fromNumberOfKeys(10,config, RandomTypeSelector.default())
            println(jsonCreater.create())            


```

#### Parallel creation of random strings
In the example below, we used kotlin's [coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)
 based `async-await` util to create 10 json strings in parallel.
```
            val config =  RandomJsonConfig(
                                             RandomDouble.default(),
                                             RandomInt.default(),
                                             RandomString.charArray("eusbwopw".toCharArray(), 5),
                                             RandomBoolean.default(),
                                             RandomString.charArray("abcdefg".toCharArray(), 5)
                                         )
            val jsonCreater = RandomJsonCreator
            .fromNumberOfKeys(10,config, RandomTypeSelector.default())
            
            val tasks =  (1..10).map {
                 GlobalScope.async {
                    println(jsonCreater.create())
                }
            }
            tasks.forEach{ it.await()}

```
