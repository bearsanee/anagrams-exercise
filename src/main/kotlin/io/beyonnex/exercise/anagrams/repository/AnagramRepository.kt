package io.beyonnex.exercise.anagrams.repository

interface AnagramRepository {
    fun saveAnagrams(text1: String, text2: String)
    fun findAnagrams(text: String): List<String>
}