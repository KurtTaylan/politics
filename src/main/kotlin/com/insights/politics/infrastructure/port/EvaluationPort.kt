package com.insights.politics.infrastructure.port

import com.insights.politics.domain.model.dto.EvaluationResult

interface EvaluationPort {

    fun evaluateCsvFiles(
        urls: List<String>,
        year: Int = 2013,
        topic: String = "homeland security"
    ): EvaluationResult
}