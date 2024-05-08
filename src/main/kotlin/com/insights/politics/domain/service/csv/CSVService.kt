package com.insights.politics.domain.service.csv

interface CSVService {
    fun readCsvFromUrl(urlString: String): List<List<String>>
}