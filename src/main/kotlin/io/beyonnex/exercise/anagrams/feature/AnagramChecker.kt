package io.beyonnex.exercise.anagrams.feature

import io.beyonnex.exercise.anagrams.repository.AnagramRepository

class AnagramChecker(private val repository: AnagramRepository) {
    private val anagram = Anagram()

    fun areAnagrams(text1: String, text2: String): Boolean {
        if(text1.isEmpty() || text2.isEmpty()) {
            return false
        }
        val areAnagrams = anagram.areAnagrams(text1, text2)
        if (areAnagrams) {
            repository.saveAnagrams(text1, text2)
        }
        return areAnagrams
    }
}