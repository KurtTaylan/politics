package com.insights.politics.domain.service.speaker

interface SpeakerService {
    fun findSpeakerWithMostSpeechesInYear(year: Int, csvRecords: List<List<String>>): String?
    fun findSpeakerWithMostSpeechesOnTopic(topic: String, csvRecords: List<List<String>>): String?
    fun findSpeakerWithLeastWordy(csvRecords: List<List<String>>): String?
}