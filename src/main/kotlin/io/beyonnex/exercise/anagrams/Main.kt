package io.beyonnex.exercise.anagrams

import io.beyonnex.exercise.anagrams.cli.AnagramsCli
import io.beyonnex.exercise.anagrams.repository.InMemoryAnagramRepository

fun main(args: Array<String>) {
    AnagramsCli(InMemoryAnagramRepository()).main()
}




