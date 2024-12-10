package io.beyonnex.exercise.anagrams.repository

import java.util.*

class InMemoryAnagramRepository: AnagramRepository {
    private val map = mutableMapOf<String, MutableSet<String>>()

    override fun saveAnagrams(text1: String, text2: String) {
        addToMap(text1, text2)
        addToMap(text2, text1)
    }

    private fun addToMap(key: String, value: String) {
        if (map.containsKey(key)) {
            map[key]?.add(value)
        } else {
            map[key] = mutableSetOf(value)
        }
    }

    override fun findAnagrams(text: String): List<String> {
        val result = mutableSetOf<String>()

        val textToSearch: Queue<String> = LinkedList()
        val alreadySearched = HashSet<String>()

        textToSearch.offer(text)
        while (textToSearch.isNotEmpty()) {
            val key = textToSearch.poll()
            map[key]?.let {
                result.addAll(it)
                alreadySearched.add(key)
                textToSearch.addAll(it.filter { k -> !alreadySearched.contains(k) })
            }
        }
        return result.filter { it != text }.toList()
    }
}