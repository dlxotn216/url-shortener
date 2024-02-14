package io.taesu.urlshortner.application

import io.taesu.urlshortner.domain.ShortenUrlEntity
import io.taesu.urlshortner.domain.ShortenUrlEntityRepository
import io.taesu.urlshortner.infra.HashGenerateRequest
import io.taesu.urlshortner.infra.HashGenerator
import io.taesu.urlshortner.interfaces.dtos.ShortenUrlCreateRequest
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.stereotype.Service

@Service
class ShortenUrlCreateService(
    private val hashGenerator: HashGenerator,
    private val shortenUrlEntityRepository: ShortenUrlEntityRepository,
) {
    fun create(request: ShortenUrlCreateRequest): String {
        return hashGenerator.generate(HashGenerateRequest.empty()).apply {
            shortenUrlEntityRepository.save(
                ShortenUrlEntity(
                    originalUrl = request.originalUrl,
                    hash = this
                )
            )
        }
    }
}

@ConfigurationProperties(prefix = "config.app")
class AppHostConfig @ConstructorBinding constructor(
    val host: String,
)
