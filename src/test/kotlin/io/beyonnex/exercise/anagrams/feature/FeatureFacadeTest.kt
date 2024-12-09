package io.beyonnex.exercise.anagrams.feature

import io.beyonnex.exercise.anagrams.repository.InMemoryAnagramRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FeatureFacadeTest {

    private val repository = InMemoryAnagramRepository()
    private val subject = FeatureFacade(repository)

    @Test
    fun call_feature1() {
        assertThat(subject.feature1("foo", "o f o")).isTrue
    }

    @Test
    fun call_feature2() {
        assertThat(subject.feature2("foo")).isEmpty()
    }

    @Test
    fun social_test_call_feature1_feature2() {
        subject.feature1("abc", "cba")
        subject.feature1("bca", "cba")
        assertThat(subject.feature2("abc")).containsExactly("cba","bca")
    }
}