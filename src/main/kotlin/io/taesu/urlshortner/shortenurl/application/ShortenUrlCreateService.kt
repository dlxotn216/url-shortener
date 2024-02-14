package io.taesu.urlshortner.shortenurl.application

import io.taesu.urlshortner.hash.usecase.HashGenerateRequest
import io.taesu.urlshortner.hash.usecase.HashGenerator
import io.taesu.urlshortner.shortenurl.domain.ShortenUrlCreatedEvent
import io.taesu.urlshortner.shortenurl.domain.ShortenUrlEntity
import io.taesu.urlshortner.shortenurl.domain.ShortenUrlEntityRepository
import io.taesu.urlshortner.shortenurl.interfaces.dtos.ShortenUrlCreateRequest
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShortenUrlCreateService(
    private val hashGenerator: HashGenerator,
    private val shortenUrlEntityRepository: ShortenUrlEntityRepository,
    private val publisher: ApplicationEventPublisher,
) {
    @Transactional
    fun create(request: ShortenUrlCreateRequest): String {
        return hashGenerator.generate(HashGenerateRequest.empty()).apply {
            shortenUrlEntityRepository.save(ShortenUrlEntity(originalUrl = request.originalUrl, hash = this))
            publisher.publishEvent(ShortenUrlCreatedEvent(this, request.originalUrl))
        }
    }
}

@ConfigurationProperties(prefix = "config.app")
class AppHostConfig @ConstructorBinding constructor(
    val host: String,
)
