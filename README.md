# RandomJson [![CodeFactor](https://www.codefactor.io/repository/github/mangatmodi/randomjson/badge)](https://www.codefactor.io/repository/github/mangatmodi/randomjson) [![Build Status](https://travis-ci.com/mangatmodi/RandomJson.svg?branch=master)](https://travis-ci.com/mangatmodi/RandomJson)

Provides library to create a random json. Provides two implementation of json creation

**[SampleJsonCreator](#SampleJsonCreator)**: Creates JSON string from a sample string.

**[SimpleJsonCreator](#SimpleJsonCreator)**: Creates JSON string by taking number of required keys.

Some important considerations:
1. The random value generations could be customised by giving your own [implementation](#Customize-random-value-generators).
2. The default given implementation is _thread-safe_. That means random strings can be [created](#Parallel-creation-of-random-strings) in different threads
3. The library create one string for each call to `create()`. It does not provides any concurrency or streaming. It totally depends on the developer on how one 
wants to use it.    

#### Java Interoperational
The library can be used in Java 10+. See [example](#Java-example).   
### Usage
#### Configuration
First we need to create configuration for the creator. This config specify, the random value generators for each of the 
primitive json type. The library includes some basic generator for each type.
```kotlin
val config =  RandomJsonConfig(
    RandomDouble.threadLocalRandom(),
    RandomInt.threadLocalRandom(),
    RandomString.charArray("eusbwopw".toCharArray(), 5),
    RandomBoolean.uniform(),
    RandomString.charArray("abcdefg".toCharArray(), 5)
    )

```

#### SampleJsonCreator
Creates JSON string similar to `{"key1":{"key2":3}}` in structure but keys and values have random values.
```kotlin
val jsonCreator = RandomJsonCreator
    .fromSampleString("""{"key1":{"key2":3}}""", config, KeepKeys.No)
    println(jsonCreator.create())            
    
```
Above prints
```json
{"ggdb":{"faea":1279812142}}
```

**Keep the same keys as the sample string**
The sample string creator can create json with same keys as the original json.
```kotlin
    val input = """{"key1":{"key2":3}}""".trimIndent()

    val jsonCreator = RandomJsonCreator
        .fromSampleString(input, config, KeepKeys.YES)
    println(jsonCreator.create())

```    
Above prints
```json
{"key1":{"key2":2083614805}}
```

See more [examples](https://github.com/mangatmodi/RandomJson/blob/master/examples/).
#### SimpleJsonCreator 
Creates JSON string similar 2ith 10 keys, the structure of the json is decided by `RandomTypeSelector`,
which specify which which type of json field will be added next. SimpleJsonCreator does not support 
arrays or nested json. 

```kotlin
val jsonCreator = RandomJsonCreator
    .fromNumberOfKeys(10,config, RandomTypeSelector.uniform())
    println(jsonCreator.create())            
    
```
#### Customize random value generators

In the example below `DummyDoubleValue` implements `RandomDouble` to give `2.0`
as the double value. So all the JSON strings created by `jsonCreator` below will contain `2.0` as double value
```kotlin
class DummyDoubleValue:RandomDouble{
    override fun next() =  2.0
}
val config =  RandomJsonConfig(
    DummyDoubleValue(),
    RandomInt.threadLocalRandom(),
    RandomString.charArray("eusbwopw".toCharArray(), 5),
    RandomBoolean.uniform(),
    RandomString.charArray("abcdefg".toCharArray(), 5)
    )


val jsonCreator = RandomJsonCreator
    .fromNumberOfKeys(10,config, RandomTypeSelector.uniform())
    println(jsonCreator.create())            

```

#### Parallel creation of random strings
In the example below, we used kotlin's [coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)
 based `async-await` util to create 10 json strings in parallel.
```kotlin
val jsonCreator = RandomJsonCreator
    .fromNumberOfKeys(10,config, RandomTypeSelector.uniform())
            
val tasks =  (1..10).map {
    async {
          println(jsonCreator.create())
        }
    }
    
tasks.forEach{ it.await()}

```

#### Java example
```java
        RandomJsonConfig config = new RandomJsonConfig(
                RandomDouble.threadLocalRandom(),
                RandomInt.threadLocalRandom(),
                RandomString.charArray("eusbwopw".toCharArray(), 5),
                RandomBoolean.uniform(),
                RandomString.charArray("abcdefg".toCharArray(), 5)
        );

        RandomJsonCreator creator = RandomJsonCreator.fromSampleString(
                "{\"q\":1}",
                config,
                KeepKeys.no()
        );

       System.out.println(creator.create())
```
### Install
The library could be installed from maven central

**Maven**
```maven
<dependency>
    <groupId>com.github.mangatmodi</groupId>
    <artifactId>randomjson</artifactId>
    <version>2.1.2</version>
</dependency>

```

**Gradle**
```gradle
compile group: 'com.github.mangatmodi', name: 'randomjson', version: '2.1.2'
```



