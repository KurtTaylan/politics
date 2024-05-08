package com.insights.politics.domain.service.speaker.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class SpeakerServiceImplTest {

    @InjectMocks
    lateinit var speakerService: SpeakerServiceImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test findSpeakerWithMostSpeechesInYear`() {
        val csvRecords = listOf(
            listOf("Speaker1", "Topic1", "2013-01-01", "100"),
            listOf("Speaker1", "Topic2", "2013-01-02", "200"),
            listOf("Speaker2", "Topic1", "2013-01-01", "150")
        )

        assertEquals("Speaker1", speakerService.findSpeakerWithMostSpeechesInYear(2013, csvRecords))
    }

    @Test
    fun `test findSpeakerWithMostSpeechesOnTopic`() {
        val csvRecords = listOf(
            listOf("Speaker1", "Topic1", "2013-01-01", "100"),
            listOf("Speaker1", "Topic2", "2013-01-02", "200"),
            listOf("Speaker2", "Topic1", "2013-01-01", "150")
        )

        assertEquals(
            "Speaker1",
            speakerService.findSpeakerWithMostSpeechesOnTopic("Topic1", csvRecords)
        )
    }

    @Test
    fun `test findSpeakerWithLeastWordy`() {
        val csvRecords = listOf(
            listOf("Speaker1", "Topic1", "2013-01-01", "100"),
            listOf("Speaker2", "Topic2", "2013-01-02", "200"),
            listOf("Speaker3", "Topic3", "2013-01-03", "50")
        )

        assertEquals("Speaker3", speakerService.findSpeakerWithLeastWordy(csvRecords))
    }
}