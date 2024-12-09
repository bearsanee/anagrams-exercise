package io.beyonnex.exercise.anagrams.cli

import io.beyonnex.exercise.anagrams.feature.FeatureFacade
import io.beyonnex.exercise.anagrams.repository.AnagramRepository

class AnagramsCli(private val anagramRepository: AnagramRepository) {

    fun main() {
        val featureFacade = FeatureFacade(anagramRepository)
        val cliCommand = CliCommand(featureFacade)

        splashScreen()
        while(true) {
            val command = readln()
            val exitCommand = cliCommand.execute(command)
            if(exitCommand) return
        }
    }

    private fun splashScreen() {
        print("""
            *** Welcome to Anagrams CLI *************************************************************************************************
            You can run two commands:
               f1("text1","text2") :: true/false         | check if text1 is an anagram of text2 based on english wikipedia definition
               f2("text")          :: [listen, silent]   | returns an array of previously checked anagrams using f1
            to exit the program just write:
               quit
            *****************************************************************************************************************************
            
        """.trimIndent())
    }
}