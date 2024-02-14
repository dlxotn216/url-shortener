package io.taesu.urlshortner.shortenurl.application

import io.taesu.urlshortner.shortenurl.domain.ShortenUrlEntityRepository
import io.taesu.urlshortner.shortenurl.domain.findOrThrow
import io.taesu.urlshortner.shortenurl.infra.ShortenUrlCacheRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by itaesu on 2024/02/14.
 *
 * Resilience4j를 활용하여 적절히 Circuit breaker 적용, PER 알고리즘 적용.
 *
 * https://medium.com/@taesulee93/%EC%9E%A5%EC%95%A0-%EC%A0%84%ED%8C%8C%EB%A5%BC-%EB%B0%A9%EC%A7%80%ED%95%98%EA%B8%B0-%EC%9C%84%ED%95%9C-circuitbreaker-pattern-%EB%8F%84%EC%9E%85-with-resilience4j-dc67ed7569d2
 * https://medium.com/@taesulee93/spring-data-redis-%ED%99%98%EA%B2%BD%EC%97%90%EC%84%9C-per-probabilistic-early-recomputation-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98%EC%9D%84-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%BA%90%EC%8B%9C-%EC%8A%A4%ED%83%AC%ED%94%BC%EB%93%9C-%ED%98%84%EC%83%81-%ED%95%B4%EA%B2%B0-275cac51e29e
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
@Service
@Transactional(readOnly = true)
class ShortenUrlRetrieveService(
    private val shortenUrlCacheRepository: ShortenUrlCacheRepository,
    private val shortenUrlEntityRepository: ShortenUrlEntityRepository,
) {
    fun retrieveOriginalUrl(hash: String): String {
        return shortenUrlCacheRepository.find(hash)?.originalUrl ?: retrieveFromOriginStore(hash)
    }

    fun retrieveFromOriginStore(hash: String): String {
        return shortenUrlEntityRepository.findOrThrow(hash).apply {
            shortenUrlCacheRepository.save(hash, originalUrl)
        }.originalUrl
    }
}
