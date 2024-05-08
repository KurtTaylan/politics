package com.insights.politics.domain.service.csv.impl

import com.insights.politics.domain.service.csv.CSVService
import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import org.apache.commons.csv.CSVFormat
import org.springframework.stereotype.Service
import java.io.InputStreamReader
import java.net.URI

@RequiredArgsConstructor
@Service
class CSVServiceImpl : CSVService {

    private val logger = KotlinLogging.logger {}

    public override fun readCsvFromUrl(urlString: String): List<List<String>> {
        val csvRecords = mutableListOf<List<String>>()
        logger.info { "Reading CSV from URL: $urlString" }

        val url = URI.create(urlString).toURL()
        val reader = InputStreamReader(url.openStream(), "UTF-8")
        val csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)
        for (record in csvParser) {
            val speaker = record.get("Speaker")
            val topic = record.get("Topic")
            val date = record.get("Date")
            val words = record.get("Words")
            csvRecords.add(listOf(speaker, topic, date, words))
        }

        logger.info { "Produces CSV Records from URL: $csvRecords" }

        return csvRecords
    }
}