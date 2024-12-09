# Anagrams Exercise
This project is about the Anagrams exercise for Backend developers.

The CLI exposes these commands
```
f1("text1","text2") :: true/false        | check if text1 is an anagram of text2 based on english wikipedia definition
f2("text")          :: [listen, silent]  | returns an array of previously checked anagrams using f1
quit                                     | close the program 
```
If you run a not recognized command the CLI will respond `Command not found`    
If you use the `Canc` button or the `Backspace` button, while you are writing the command, those will be recognized as characters so you will have a `Command not found`

## How to run the project
This is a Kotlin project built on top of Java 21.    
Before starting, check that your `JAVA_HOME` is pointing to a valid Java 21 installation on your local machine

In the main directory of the project use the command `./gradlew build` to build the project and run all the tests   
use the command `./gradlew run` to run the CLI and start inserting the commands   
(if you are on Windows machine use `gradlew.bat`)

-- -
# Technical decisions
I decided to code this project in Kotlin despite being a little "rusty" with it. Forgive me if I made some mistakes, 
I need some time to reacquire the full confidence with Kotlin after two and a half years working mainly on Java

I decided to use gradle instead of maven for the same reason, probably the configurations are not the best, so forgive me if I made some mistakes

I tried to cure readability, extensibility and performance; even if the CLI is a little bit limiting, specially about performance and usability.

## CLI and In-memory repository
The decision to use CLI and In-memory repository was to keep frameworks and libraries out of this exercise, 
so I could work with full unit test code in Outside In TDD without the need of mocks.
This could let me apply some "old fashion" manual dependency injection without black magic.

The CLI is separated from the domain code through a Facade that hides all the implementation details.    
The repository is decoupled from the rest of the code through an interface.

The project can be easily extended with REST API and a Database without touching the domain code in the `feature` package.
The REST API will reuse the `FeatureFacade` while there will be a new implementation for the `AnagramRepository` interface.

### CLI
The CLI is not 100% covered by unit test because I decided to rely on the `readln()` command.
Due to some limitations in the overriding of the ```System.`in` ``` I wasn't able to fully unit test it.   
I did several attempts: overriding directly ```System.`in` ``` with a `ByteArrayInputStream` or using `PipedInputStream` and `PipedOutputStream` connected but without results.
The line missing, the `cliCommand.execute(command)` has been tested manually, playing some tests with the CLI itself.

### Facade
The `FeatureFacade` is hiding the implementation details of the domain, represented by the `feature` package. 
The decision to don't create a specific class for feature2 was taken to avoid a class with a single function with a single line inside, so to avoid an empty proxy class.
When the single call in the Facade will not be enough a class for feature2 must be created. 

### Anagram Algorithm
I decided to use a Regex to identify the 26 alphabet letters without too many issues with special characters, 
since they are not considered in the examples reported in the wikipedia page. The same for the case of the letters.

I decided to use a pre-allocated HashMap (reporting the maximum size during the initialization) to avoid the cost of resize.   
The HashMap is reporting the frequency for the single character found. 
The first text increments the frequency while the second text decrements it.
If at the end of the process all the HashMap keys contains 0 the two texts are anagrams.    
The combination containing only empty text or special characters with spaces are excluded because they don't contain letters.
All the letters with accents have been considered as special characters.

### In memory repository
For the in memory repository I decide to use a simple `HashMap<String, List<String>>` to store the anagrams.
To be able to retrieve with `f2` also che right text of the `f1` call I needed to store the text twice, using each text as key.
This decision complicates a little bit the find but maintains a low memory usage and a fast search.
In the `findAnagrams` algorithm I had to use an `alreadySearched: HashSet<String>` to avoid infinite loop and being able to discard already searched keys.
The choice about the `HashSet` was made to keep search cost constant.

# What is missing to have it production proof?
This version uses in-memory repository so shouldn't be considered production proof.
A persisted version of the `AnagramRepository` must be implemented.

The CLI is very basic. It doesn't have recall for the previous commands, and it doesn't let the user cancel characters. To be really production proof it must be extended to be more usable.

All the observability, with logging and metrics, is missing. It must be present to go to production.


