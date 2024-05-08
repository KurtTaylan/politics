package com.insights.politics.infrastructure.adapter.rest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "15000")
class EvaluationRestAdapterIT {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `test evaluateCsvFiles endpoint`() {
        val csvUrl1 = ClassPathResource("files/csv1.csv").url
        val csvUrl2 = ClassPathResource("files/csv2.csv").url

        webTestClient.get()
            .uri { uriBuilder ->
                uriBuilder.path("/evaluation")
                    .queryParam("urls", csvUrl1.toString())
                    .queryParam("urls", csvUrl2.toString())
                    .build()
            }
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.mostSpeeches").isEqualTo("John")
            .jsonPath("$.mostSecurity").isEqualTo("John")
            .jsonPath("$.leastWordy").isEqualTo("John")
    }
}