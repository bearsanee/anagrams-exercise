package io.beyonnex.exercise.anagrams.feature

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class AnagramTest {

    private val subject = Anagram()

    @ParameterizedTest(name = "`{0}` is anagram of `{1}`")
    @CsvFileSource(resources = ["/anagrams.csv"], delimiterString = "=", quoteCharacter = '"')
    fun are_anagrams(text1: String, text2: String) {
        assertThat(subject.areAnagrams(text1, text2)).isTrue()
    }

    @ParameterizedTest(name = "`{0}` is NOT anagram of `{1}`")
    @CsvFileSource(resources = ["/not_anagrams.csv"], delimiterString = "=", quoteCharacter = '"')
    fun are_not_anagrams(text1: String, text2: String) {
        assertThat(subject.areAnagrams(text1, text2)).isFalse()
    }

    @Test
    fun are_anagrams_with_all_letters() {
        val text1 = "abcdefghijklmnopqrstuvwxyz"
        val text2 = "ZYXWVUTSRQPONMLKJIHGFEDCBA"
        assertThat(subject.areAnagrams(text1, text2)).isTrue()
    }

    @Test
    fun are_anagrams_full_of_special_characters() {
        val text1 = "a°é+ù'%&%b\"`!c£?dè^]e∞§}"
        val text2 = "E'_,D<;C.,:|Bùòìà@A"
        assertThat(subject.areAnagrams(text1, text2)).isTrue()
    }

    @Test
    fun are_not_anagrams_without_letters() {
        val text1 = "???"
        val text2 = "__"
        assertThat(subject.areAnagrams(text1, text2)).isFalse()
    }
}