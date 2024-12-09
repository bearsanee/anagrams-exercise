package io.beyonnex.exercise.anagrams.cli

import io.beyonnex.exercise.anagrams.repository.InMemoryAnagramRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class AnagramsCliTest {

    private lateinit var subject: AnagramsCli

    private val consoleOut = ByteArrayOutputStream()
    private val originalOut: PrintStream = System.out

    @BeforeEach
    fun setUp() {
        subject = AnagramsCli(InMemoryAnagramRepository())
        System.setOut(PrintStream(consoleOut))
    }

    @AfterEach
    fun restoreStreams() {
        System.setOut(originalOut)
    }

    @Test
    fun test_splash_screen() {
        try {
            subject.main()
        } catch (e: Exception) {
            //catch ReadAfterEOFException due to readln() command unable to read System.in
            //for more information about this choice go to the README file, CLI section
        } finally {
            assertThat(consoleOut.toString()).isEqualTo("""
            *** Welcome to Anagrams CLI *************************************************************************************************
            You can run two commands:
               f1("text1","text2") :: true/false         | check if text1 is an anagram of text2 based on english wikipedia definition
               f2("text")          :: [listen, silent]   | returns an array of previously checked anagrams using f1
            to exit the program just write:
               quit
            *****************************************************************************************************************************
            
        """.trimIndent())
        }
    }
}