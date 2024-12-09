package io.beyonnex.exercise.anagrams.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InMemoryAnagramRepositoryTest {
    private lateinit var subject: InMemoryAnagramRepository

    @BeforeEach
    fun setUp() {
        subject = InMemoryAnagramRepository()
    }

    @Test
    fun save_anagrams() {
        val text1 = "foo"
        val text2 = "o of"
        subject.saveAnagrams(text1, text2)
        assertThat(subject.findAnagrams(text1)).containsExactly(text2)
    }

    @Test
    fun find_anagrams_return_nothing() {
        assertThat(subject.findAnagrams("foo")).isEmpty()
    }

    @Test
    fun find_anagrams_return_anagrams() {
        val text1 = "foo"
        val text2 = "o of"
        val text3 = "ofo"
        val text4 = "f,o,o"
        subject.saveAnagrams(text1, text2)
        subject.saveAnagrams(text2, text3)
        subject.saveAnagrams(text4, text3)

        assertThat(subject.findAnagrams(text1)).containsExactly(text2, text3, text4)
    }
}