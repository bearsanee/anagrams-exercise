package io.beyonnex.exercise.anagrams.feature

class Anagram {
    private val isLetterRegex = Regex("[a-z]")

    fun areAnagrams(text1: String, text2: String): Boolean {
        val frequencyMap = HashMap<Char, Int>(26)

        text1.lowercase()
            .forEachIndexed { _, c -> putValidCharInMap(c, frequencyMap, 1) }

        text2.lowercase()
            .forEachIndexed { _, c -> putValidCharInMap(c, frequencyMap, -1) }

        if (frequencyMap.keys.isEmpty()) {
            return false
        }

        return frequencyMap.keys.none { k -> frequencyMap[k] != 0 }
    }

    private fun putValidCharInMap(char: Char, letterMap: java.util.HashMap<Char, Int>, increment: Int) {
        if(isLetterRegex.matches(char.toString())) {
            if (letterMap.containsKey(char)) {
                letterMap[char] = letterMap[char]!! + increment
            } else {
                letterMap[char] = increment
            }
        }
    }
}
