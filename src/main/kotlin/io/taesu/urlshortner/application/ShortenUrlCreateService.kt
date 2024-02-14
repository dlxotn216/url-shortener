package io.taesu.urlshortner.application

import io.taesu.urlshortner.domain.ShortenUrlEntityRepository
import io.taesu.urlshortner.infra.HashGenerator
import io.taesu.urlshortner.interfaces.ShortenUrlCreateRequest
import org.springframework.stereotype.Service

@Service
class ShortenUrlCreateService {
    fun create(request: ShortenUrlCreateRequest): String {
        TODO()
    }
}
