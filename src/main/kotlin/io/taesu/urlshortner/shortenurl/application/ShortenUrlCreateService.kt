package io.taesu.urlshortner.shortenurl.application

import io.taesu.urlshortner.shortenurl.domain.ShortenUrlEntity
import io.taesu.urlshortner.shortenurl.domain.ShortenUrlEntityRepository
import io.taesu.urlshortner.hash.usecase.HashGenerateRequest
import io.taesu.urlshortner.hash.usecase.HashGenerator
import io.taesu.urlshortner.shortenurl.interfaces.dtos.ShortenUrlCreateRequest
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
