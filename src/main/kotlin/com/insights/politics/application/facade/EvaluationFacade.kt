package com.insights.politics.application.facade

import com.insights.politics.domain.model.dto.EvaluationResult
import com.insights.politics.domain.service.csv.CSVService
import com.insights.politics.domain.service.speaker.SpeakerService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@RequiredArgsConstructor
@Component
class EvaluationFacade(
    val csvService: CSVService,
    val speakerService: SpeakerService
) {

    fun evaluateCsvFiles(urls: List<String>, year: Int, topic: String): EvaluationResult {
        val csvRecords = mutableListOf<List<String>>()
        readCSVRecordsFromEachURL(urls, csvRecords)
        return calculateSpeakerInformation(year, csvRecords, topic)
    }

    private fun readCSVRecordsFromEachURL(
        urls: List<String>,
        csvRecords: MutableList<List<String>>
    ) {
        for (urlString in urls) {
            csvRecords.addAll(csvService.readCsvFromUrl(urlString))
        }
    }

    private fun calculateSpeakerInformation(
        year: Int,
        csvRecords: List<List<String>> = mutableListOf<List<String>>(),
        topic: String
    ): EvaluationResult {
        val mostSpeeches = speakerService.findSpeakerWithMostSpeechesInYear(year, csvRecords)
        val mostSecurity = speakerService.findSpeakerWithMostSpeechesOnTopic(topic, csvRecords)
        val leastWordy = speakerService.findSpeakerWithLeastWordy(csvRecords)

        return EvaluationResult(mostSpeeches, mostSecurity, leastWordy)
    }
}