package com.insights.politics.domain.service.speaker.impl

import com.insights.politics.domain.service.speaker.SpeakerService
import org.springframework.stereotype.Service

@Service
class SpeakerServiceImpl : SpeakerService {

    /**
     * Iterates over the CSV records, counts the number of speeches by each speaker in the given year,
     * and returns the speaker with the most speeches in that year.
     */
    public override fun findSpeakerWithMostSpeechesInYear(
        year: Int, csvRecords: List<List<String>>
    ): String? {
        val speechesBySpeaker = mutableMapOf<String, Int>()

        for (record in csvRecords) {
            val date = record[2]
            if (date.startsWith(year.toString())) {
                val speaker = record[0]
                speechesBySpeaker[speaker] = speechesBySpeaker.getOrDefault(speaker, 0) + 1
            }
        }

        return speechesBySpeaker.maxByOrNull { it.value }?.key
    }

    /**
     * Iterates over the CSV records, counts the number of speeches by each speaker on the given topic,
     * and returns the speaker with the most speeches on that topic.
     */
    public override fun findSpeakerWithMostSpeechesOnTopic(
        topic: String, csvRecords: List<List<String>>
    ): String? {
        val speechesBySpeaker = mutableMapOf<String, Int>()

        for (record in csvRecords) {
            val recordTopic = record[1]
            if (recordTopic.equals(topic, ignoreCase = true)) {
                val speaker = record[0]
                speechesBySpeaker[speaker] = speechesBySpeaker.getOrDefault(speaker, 0) + 1
            }
        }

        return speechesBySpeaker.maxByOrNull { it.value }?.key
    }

    /**
     * Iterates over the CSV records, keeps track of the speaker who spoke the fewest words overall,
     * and returns the speaker who spoke the fewest words.
     */
    public override fun findSpeakerWithLeastWordy(csvRecords: List<List<String>>): String? {
        var leastWordySpeaker: String? = null
        var minWords = Int.MAX_VALUE

        for (record in csvRecords) {
            val words = record[3].toIntOrNull()
            if (words != null && words < minWords) {
                minWords = words
                leastWordySpeaker = record[0]
            }
        }

        return leastWordySpeaker
    }
}