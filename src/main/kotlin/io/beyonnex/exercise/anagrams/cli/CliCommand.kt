package io.beyonnex.exercise.anagrams.cli

import io.beyonnex.exercise.anagrams.feature.FeatureFacade

class CliCommand(private val features: FeatureFacade) {
    private val f1Regex = Regex("f1\\(\"(.*)\",\"(.*)\"\\)")
    private val f2Regex = Regex("f2\\(\"(.*)\"\\)")
    private val quitRegex = Regex("quit")

    fun execute(command: String): Boolean {
        f1Regex.find(command)?.let {
            val result = features.feature1(it.groupValues[1], it.groupValues[2])
            println(result)
            return false
        }

        f2Regex.find(command)?.let {
            val result = features.feature2(it.groupValues[1])
            println(result)
            return false
        }

        quitRegex.find(command)?.let {
            println("Bye bye")
            return true
        }

        println("Command not found")
        return false
    }

}
