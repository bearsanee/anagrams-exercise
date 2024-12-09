package io.beyonnex.exercise.anagrams.feature

import io.beyonnex.exercise.anagrams.repository.InMemoryAnagramRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class AnagramCheckerTest {

    private lateinit var repository: InMemoryAnagramRepository
    private lateinit var subject: AnagramChecker

    @BeforeEach
    fun setUp() {
        repository = InMemoryAnagramRepository()
        subject = AnagramChecker(repository)
    }

    @Test
    fun are_anagrams() {
        val text1 = "foo"
        val text2 = "o of"
        assertThat(subject.areAnagrams(text1, text2)).isTrue()
        assertThat(repository.findAnagrams(text1)).containsExactly(text2)
    }

    @Test
    fun are_not_anagrams() {
        val text1 = "foo"
        val text2 = "or of"
        assertThat(subject.areAnagrams(text1, text2)).isFalse()
        assertThat(repository.findAnagrams(text1)).isEmpty()
    }

    @Test
    fun empty_strings_are_not_anagrams() {
        val text1 = ""
        val text2 = ""
        assertThat(subject.areAnagrams(text1, text2)).isFalse()
        assertThat(repository.findAnagrams(text1)).isEmpty()
    }
}