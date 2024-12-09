package io.beyonnex.exercise.anagrams.feature

import io.beyonnex.exercise.anagrams.repository.AnagramRepository

class FeatureFacade(private val repository: AnagramRepository) {
    private val anagramChecker = AnagramChecker(repository)

    fun feature1(text1: String, text2: String): Boolean {
        return anagramChecker.areAnagrams(text1, text2)
    }

    fun feature2(text: String): List<String> {
        return repository.findAnagrams(text)
    }
}