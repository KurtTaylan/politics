package com.insights.politics.application.facade

import com.insights.politics.domain.service.csv.CSVService
import com.insights.politics.domain.service.speaker.SpeakerService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class EvaluationFacadeTest {

    @Mock
    lateinit var speakerService: SpeakerService

    @Mock
    lateinit var csvService: CSVService

    @InjectMocks
    lateinit var evaluationFacade: EvaluationFacade

    @Test
    fun `test evaluateCsvFiles`() {
        val csvRecords1 = listOf(
            listOf("Speaker1", "Topic1", "2013-01-01", "100"),
            listOf("Speaker2", "Topic2", "2013-01-02", "200")
        )
        val csvRecords2 = listOf(
            listOf("Speaker1", "Topic1", "2014-01-01", "150"),
            listOf("Speaker3", "Topic2", "2014-01-02", "250")
        )

        `when`(csvService.readCsvFromUrl("http://example.com/csv1")).thenReturn(csvRecords1)
        `when`(csvService.readCsvFromUrl("http://example.com/csv2")).thenReturn(csvRecords2)
        `when`(
            speakerService.findSpeakerWithMostSpeechesInYear(
                2013,
                csvRecords1 + csvRecords2
            )
        ).thenReturn("Speaker1")
        `when`(
            speakerService.findSpeakerWithMostSpeechesOnTopic(
                "homeland security",
                csvRecords1 + csvRecords2
            )
        ).thenReturn("Speaker2")
        `when`(speakerService.findSpeakerWithLeastWordy(csvRecords1 + csvRecords2)).thenReturn("Speaker1")

        val result = evaluationFacade.evaluateCsvFiles(
            listOf(
                "http://example.com/csv1",
                "http://example.com/csv2"
            ), 2013, "homeland security"
        )

        assert(result.mostSpeeches == "Speaker1")
        assert(result.mostSecurity == "Speaker2")
        assert(result.leastWordy == "Speaker1")
    }
}