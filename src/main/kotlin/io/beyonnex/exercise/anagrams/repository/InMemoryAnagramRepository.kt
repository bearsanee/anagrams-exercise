package io.beyonnex.exercise.anagrams.repository

import java.util.*

class InMemoryAnagramRepository: AnagramRepository {
    private val map = mutableMapOf<String, MutableSet<String>>()

    override fun saveAnagrams(text1: String, text2: String) {
        if (map.containsKey(text1)) {
            map[text1]?.add(text2)
        } else {
            map[text1] = mutableSetOf(text2)
        }

        if (map.containsKey(text2)) {
            map[text2]?.add(text1)
        } else {
            map[text2] = mutableSetOf(text1)
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