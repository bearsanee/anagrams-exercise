package io.beyonnex.exercise.anagrams.cli

import io.beyonnex.exercise.anagrams.feature.FeatureFacade
import io.beyonnex.exercise.anagrams.repository.InMemoryAnagramRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CliCommandTest {
    private lateinit var subject: CliCommand

    private val consoleOut = ByteArrayOutputStream()
    private val originalOut: PrintStream = System.out

    @BeforeEach
    fun setUp() {
        subject = CliCommand(FeatureFacade(InMemoryAnagramRepository()))
        System.setOut(PrintStream(consoleOut))
    }

    @AfterEach
    fun restoreStreams() {
        System.setOut(originalOut)
    }

    @Test
    fun execute_no_command() {
        val result = subject.execute("")
        assertThat(consoleOut.toString()).isEqualTo("Command not found\n")
        assertThat(result).isFalse()
    }

    @Test
    fun execute_wrong_command() {
        val result = subject.execute("f2(foo)")
        assertThat(consoleOut.toString()).isEqualTo("Command not found\n")
        assertThat(result).isFalse()
    }

    @Test
    fun execute_f1_command() {
        val result = subject.execute("f1(\"foo\",\"ofo\")")
        assertThat(consoleOut.toString()).isEqualTo("true\n")
        assertThat(result).isFalse()
    }

    @Test
    fun execute_f2_command() {
        val result = subject.execute("f2(\"foo\")")
        assertThat(consoleOut.toString()).isEqualTo("[]\n")
        assertThat(result).isFalse()
    }

    @Test
    fun execute_quit_command() {
        val result = subject.execute("quit")
        assertThat(consoleOut.toString()).isEqualTo("Bye bye\n")
        assertThat(result).isTrue()
    }

    @Test
    fun social_test_f1_f2_commands() {
        subject.execute("f1(\"foo\",\"ofo\")")
        subject.execute("f2(\"foo\")")
        assertThat(consoleOut.toString()).isEqualTo("true\n[ofo]\n")
    }


}