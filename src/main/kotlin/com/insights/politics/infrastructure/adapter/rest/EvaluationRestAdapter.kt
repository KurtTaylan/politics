package com.insights.politics.infrastructure.adapter.rest

import com.insights.politics.application.facade.EvaluationFacade
import com.insights.politics.domain.model.dto.EvaluationResult
import com.insights.politics.infrastructure.port.EvaluationPort
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequiredArgsConstructor
@RequestMapping("/evaluation")
@RestController
class EvaluationRestAdapter(val evaluationFacade: EvaluationFacade) : EvaluationPort {

    /**
     * This endpoint evaluates CSV files located at the provided URLs.
     * It calculates and returns information about the speakers and their speeches.
     *
     * @param urls List of URLs pointing to CSV files containing speaker information.
     * @return EvaluationResult object containing information about speakers and their speeches.
     */
    @GetMapping
    public override fun evaluateCsvFiles(
        @RequestParam("urls") urls: List<String>,
        @RequestParam(
            "year",
            required = false
        ) year: Int,
        @RequestParam(
            "topic",
            required = false
        ) topic: String
    ): EvaluationResult {
        return evaluationFacade.evaluateCsvFiles(urls, year, topic)
    }
}